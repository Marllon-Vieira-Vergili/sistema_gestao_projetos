package com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.request.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.TaskPriority;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.TaskStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TaskUpdateRequest(

        @NotNull(message = "Por favor, selecione um novo status para a tarefa")
        TaskStatus updatedStatus,

        @NotNull(message = "Por favor, selecione uma nova prioridade para a tarefa")
        TaskPriority updatedPriority,


        Long assignedToId,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dueDate
) {
}
