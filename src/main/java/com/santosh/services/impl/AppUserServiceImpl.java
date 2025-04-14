package com.santosh.services.impl;

import com.santosh.models.AppUser;
import com.santosh.repositories.AppUserRepository;
import com.santosh.services.AppUserServices;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserServices {

    private final AppUserRepository userRepository;

    @Override
    public AppUser saveUser(AppUser appUser) {
        return userRepository.save(appUser);
    }
}
