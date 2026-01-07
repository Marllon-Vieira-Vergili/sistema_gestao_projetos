package com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.request.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.TaskPriority;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.time.LocalDateTime;

public record TaskRequest(

        @NotBlank(message = "Por favor, preencha o campo do título da tarefa")
        @Size(min = 3, max = 50, message = "O campo deve ter no mínimo 3 caracteres e no máximo 50 caracteres")
        String title,

        @Size(max = 2000, message = "A descrição da tarefa não pode passar de 2000 caracteres")
        String description,

        @NotNull(message = "Por favor, selecione um Status para a tarefa")
        TaskStatus status,

        @NotNull(message = "Defina a prioridade da tarefa")
        TaskPriority priority,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dueDate,

        @NotNull(message = "ID do projeto é obrigatório")
        Long projectId,


        Long assignedToId

) {
}
