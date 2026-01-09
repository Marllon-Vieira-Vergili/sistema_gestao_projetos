package com.marllon.vieira.vergili.sistema_gestao_projetos.Repository;

import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.ProjectStatus;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Project;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Crie ProjectRepository com métodos: findByCreatedById,
 * findByMembersContaining para buscar projetos de um usuário específico,
 * findByStatus para filtrar por status,
 * e countByStatus para estatísticas. **/

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    //Buscar Projeto criado com id
    Project findByCreatedById(Long projectId);

    //Buscar lista de projetos de um usuário específico
    @Query("SELECT p FROM Project p JOIN p.users u WHERE u.id = :userId")
    List<Project> findByMembersContaining(@Param("userId") Long userId);

    //Buscar lista de projetos filtrando por status
    List<Project> findByStatus(ProjectStatus status);

    //retornar cálculo inteiro de quantos projetos tem nesse status
    Long countByStatus(ProjectStatus status);
}

