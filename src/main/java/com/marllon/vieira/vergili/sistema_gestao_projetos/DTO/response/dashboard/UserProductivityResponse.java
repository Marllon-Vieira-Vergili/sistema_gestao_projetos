package com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.response.dashboard;

public record UserProductivityResponse(

        Long userId,
        String userName,
        Integer assignedTasks,
        Integer completedTasks,
        Double completionRate
) {
}
