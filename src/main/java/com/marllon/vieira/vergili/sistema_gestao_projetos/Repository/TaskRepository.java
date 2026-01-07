package com.marllon.vieira.vergili.sistema_gestao_projetos.Repository;

import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.ProjectStatus;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.TaskStatus;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/***
 * Crie TaskRepository com métodos: findByProjectId,
 * findByAssignedToId, findByStatus, findByProjectIdAndStatus,
 * countByProjectId, countByProjectIdAndStatus para dashboards,
 * e findByDueDateBefore para buscar tarefas atrasadas.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findByProjectId(Long projectId);

    Task findByAssignedToId(Long id);

    Task findByStatus(TaskStatus status);

    Task findByProjectIdAndStatus(Long projectId, ProjectStatus projectStatus);
}
