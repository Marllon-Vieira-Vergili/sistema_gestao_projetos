package com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.response.dashboard;

public record DashboardStatsResponse(

        Integer totalProjects,
        Integer activeProjetcs,
        Integer totalTasks,
        Integer completedTasks,
        Integer pendingTasks,
        Integer tasksInProgress,
        Integer tasksInReview
) {
}
