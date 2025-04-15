package com.santosh.controllers;


import com.santosh.models.AppUser;
import com.santosh.models.Permissions;
import com.santosh.models.Privilege;
import com.santosh.models.Role;
import com.santosh.models.custom_exception.UserAlreadyPresentException;
import com.santosh.repositories.AppUserRepository;
import com.santosh.repositories.PermissionRepository;
import com.santosh.repositories.RoleRepository;
import com.santosh.services.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/service")
@CrossOrigin("*")
public class JwtAuthenticationController {

    private final AppUserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RoleService roleService;
    private final S3Client s3Client;


    @PostConstruct
    public void createAdmin() throws UserAlreadyPresentException, IOException {
        Optional<AppUser> optionalUser = userRepository.getMyUserByUsername("santosh@roofing.com");
        if (optionalUser.isEmpty()) {

            Optional<Role> optionalRole = roleRepository.findByRoleName("SUPER_ADMIN");

            Role savedRole = roleRepository.findByRoleName("SUPER_ADMIN")
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setRoleName("SUPER_ADMIN");
                        role.setRoleDescription("This is super admin role");
                        return roleRepository.save(role);
                    });

            if (permissionRepository.getPermissionsByRole(savedRole).isEmpty()) {
                Privilege privilege = new Privilege();
                privilege.setWritePermission("WRITE");
                privilege.setReadPermission("READ");
                privilege.setDeletePermission("DELETE");
                privilege.setUpdatePermission("UPDATE");

                Permissions permissions = new Permissions();
                permissions.setUserPermission("ALL_PERMISSIONS");
                permissions.setRole(savedRole);
                permissions.setPrivilege(privilege);
                roleService.createPermissions(List.of(permissions));
            }

            AppUser user = new AppUser();
            user.setFirstName("Santosh");
            user.setLastName("Roofing");
            user.setUsername("santosh@roofing.com");
            user.setContact("1234567890");
            user.setAddress("KOLHAPUR");
            user.setRole(savedRole);
            user.setPassword(new BCryptPasswordEncoder().encode("Santosh@2025"));

            String imagePath = "/static/superadmin2.jpg";
            ClassPathResource imgFile = new ClassPathResource(imagePath);
            byte[] imageBytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

            String imageName = "super_admin.jpg";

            String bucketName = "santosh-roofing-resource-bucket";
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(imageName)
                    .contentType("image/jpeg")
                    .build();

            s3Client.putObject(putObjectRequest, software.amazon.awssdk.core.sync.RequestBody.fromBytes(imageBytes));

            String imageUrl = String.format("https://%s.s3.amazonaws.com/%s", bucketName, imageName);
            user.setIsMultiFactor(false);
            user.setImageUrl(imageUrl);
            user.setEnabled(true);
            userRepository.save(user);
        }
    }
}
