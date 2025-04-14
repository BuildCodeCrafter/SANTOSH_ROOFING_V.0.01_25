package com.santosh.models;

import com.santosh.models.dtos.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String username;
    private Boolean isTwoFactorAuthenticated;
    private String jwtToken;
    private RoleDto role;
    private String imageUrl;
}
