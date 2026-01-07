package com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.response.comment;

import com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.response.user.UserResponse;
import java.time.LocalDateTime;

public record CommentResponse(

        Long id,
        String content,
        UserResponse author,
        Long taskId,
        LocalDateTime createdAt
) {
}
