package com.marllon.vieira.vergili.sistema_gestao_projetos.Repository;

import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**Crie CommentRepository com métodos:
 * findByTaskId ordenado por createdAt,
 * e countByTaskId.**/
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
