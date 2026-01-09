package com.marllon.vieira.vergili.sistema_gestao_projetos.Repository;

import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.ProjectStatus;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.TaskStatus;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


/***
 * Crie TaskRepository com métodos: findByProjectId,
 * findByAssignedToId, findByStatus, findByProjectIdAndStatus,
 * countByProjectId, countByProjectIdAndStatus para dashboards,
 * e findByDueDateBefore para buscar tarefas atrasadas.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


    //Retornar lista de tarefas pela id do projeto
    List<Task> findByProjectId(Long projectId);

    //Retornar lista de tarefas encontrado au usuario associado
    List<Task> findByAssignedToId(Long id);

    //Retornar lista de tarefas localizado pelo status da mesma
    List<Task> findByStatus(TaskStatus status);

    //Retornar lista de tarefas localizado pela id do projeto e status da tarefa
    List<Task> findByProjectIdAndStatus(Long projectId, TaskStatus status);

    //Contar tarefas de um projeto
    long countByProjectId(Long projectId);

    //Contar tarefas de um projeto com status específico
    long countByProjectIdAndStatus(Long projectId, TaskStatus status);

    //Buscar tarefas atrasadas

    List<Task> findByDueDateBefore(LocalDateTime dateNow);



}
