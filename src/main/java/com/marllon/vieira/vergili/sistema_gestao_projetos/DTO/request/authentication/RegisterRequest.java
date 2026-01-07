package com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.request.authentication;



import jakarta.validation.constraints.*;

public record RegisterRequest(


        @NotBlank(message = "Por favor, digite um nome válido")
        @Size(min = 3, max = 100, message = "Cadastro de nome deve possuir no mínimo 3 caracteres")
        @Pattern(
                regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ ]+$",
                message = "O nome deve conter apenas letras e espaços"
        )
        String fullName,

        @NotBlank(message = "Para realizar um cadastro, deve-se registrar em um endereço de Email")
        @Email(message = "Por favor, registre um endereço de Email válido")
        String email,

        @NotBlank(message = "Por favor, digite uma nova senha.")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
                message = "Sua senha deve incluir uma letra maiúscula, uma minúscula, um número e um caractere especial"
        )
        @Min(value = 6, message = "Sua senha deve conter pelo menos 6 caracteres")
        String password) {
}
