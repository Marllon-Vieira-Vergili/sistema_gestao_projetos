package com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.request.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.ProjectStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Set;

public record ProjectRequest(

        @NotBlank(message = "Digite o nome do Projeto")
        @Size(min = 3, max = 100, message = "O nome do projeto deve possuir no mínimo 3 caracteres e máximo 100")
        String name,

        @Size(max = 1000)
        String description,

        @NotNull(message = "É necessário definir o status do projeto")
        ProjectStatus status,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime startDate,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime endDate,

        @NotNull(message = "É necessário selecionar pelo menos um membro")
        @Size(min = 1, message = "Selecione pelo menos um membro")
        Set<@NotNull  Long> memberIds
) {

    //Data de fim não pode ser antes da data de inicio
    public ProjectRequest{
        if(startDate != null && endDate != null && endDate.isBefore(startDate)){
            throw new IllegalArgumentException("Data de término não pode ser anterior a data de início");
        }
    }
}
