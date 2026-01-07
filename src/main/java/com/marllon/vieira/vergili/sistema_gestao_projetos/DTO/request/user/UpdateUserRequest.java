package com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.request.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public record UpdateUserRequest(

        @Size(min = 3, max = 100, message = "O nome completo deve possuir no mínimo 3 caracteres " +
                "e no máximo 100 caracteres")
        @Pattern(
                regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ ]+$",
                message = "O nome deve conter apenas letras e espaços"
        )
        @NotBlank(message = "Campo nome não pode ficar vazio")
        String fullName,

        @Size(max = 255)
        String avatarUrl,

        @NotNull
        boolean active
) {
}
