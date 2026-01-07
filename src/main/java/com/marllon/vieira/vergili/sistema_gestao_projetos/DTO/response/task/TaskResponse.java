package com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.response.task;
import com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.response.user.UserResponse;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.TaskPriority;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.TaskStatus;
import java.time.LocalDateTime;
import java.util.Set;

public record TaskResponse(

        Long id,

        String title,


        String description,


        TaskStatus status,


        TaskPriority priority,


        LocalDateTime dueDate,


        Long projectId,

        String projectName,

        String assignedTo,

        UserResponse createdBy,

        Set<String> attachments,

        Integer commentCount,

        LocalDateTime auditoryData
) {
}
