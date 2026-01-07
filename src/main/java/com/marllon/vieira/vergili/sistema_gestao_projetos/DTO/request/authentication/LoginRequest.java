package com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.request.authentication;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "Campo email deve ser preenchido")
        @Email(message = "Por favor, digite um email válido")
        String email,


        @NotBlank(message = "Por favor, insira sua senha")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
                message = "Sua senha está errada. Lembre-se: Ela contém pelo menos  uma letra maiúscula, um número " +
                        " e um caractere especial")
        @Size(min = 6, message = "Lembre-se: Sua senha possui no mínimo 6 caracteres")
        String password) {



}
