package com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.response.dashboard;

public record ProjectProgressResponse(

        Long projectId,
        String projectName,
        Integer totalTasks,
        Integer completedTasks,
        Double progressPercentage
) {
}
