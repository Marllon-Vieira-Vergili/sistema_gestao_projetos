package com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.response.user;

import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.UserRole;

import java.time.LocalDateTime;

public record UserResponse(

        Long id,
        String email,
        String fullName,
        UserRole role,
        String avatarUrl,
        boolean active,
        LocalDateTime createdAt
) {
}
