package com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.response.authentication;

import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.UserRole;

public record AuthResponse(
        String token,
        String type,
        Long id,
        String email,
        String fullName,
        UserRole role) {

    //Construtor para definir o tipo como "Bearer" automaticamente
    public AuthResponse(String token, Long id, String email, String fullName, UserRole role){
        this(token, "Bearer", id, email, fullName,role);
    }
}
