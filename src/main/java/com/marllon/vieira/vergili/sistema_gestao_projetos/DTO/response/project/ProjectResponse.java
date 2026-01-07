package com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.response.project;
import com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.response.user.UserResponse;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.ProjectStatus;



import java.time.LocalDateTime;
import java.util.Set;

public record ProjectResponse(


        Long id,


String name,


String description,



ProjectStatus status,

        LocalDateTime startDate,


LocalDateTime endDate,


LocalDateTime createdAt,


LocalDateTime updatedAt,

        UserResponse createdBy,

        Set<UserResponse> members,

        Long totalTasks,
        Long completedTasks,
        Double progressPercentage


) {
}
