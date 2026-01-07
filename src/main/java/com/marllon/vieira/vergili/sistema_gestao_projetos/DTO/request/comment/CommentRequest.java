package com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.request.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CommentRequest(

        @NotBlank(message = "Por favor, insira um comentário")
        @Size(min = 3, max = 2000, message = "Comentário deve possuir pelo menos 3 caracteres e no máximo 2000 caracteres")
        String content,

        @NotNull
        Long taskId
) {
}
