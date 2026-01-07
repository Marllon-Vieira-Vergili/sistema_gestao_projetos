package com.marllon.vieira.vergili.sistema_gestao_projetos.Repository;

import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.ProjectStatus;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Project;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Crie ProjectRepository com métodos: findByCreatedById,
 * findByMembersContaining para buscar projetos de um usuário específico,
 * findByStatus para filtrar por status,
 * e countByStatus para estatísticas. **/

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    public Project findByCreatedById(Long id);

    public List<Project> findByMembersContaining(Long userId);

    public List<Project> findByStatus(ProjectStatus status);

    public Integer countByStatus(ProjectStatus status);
}
