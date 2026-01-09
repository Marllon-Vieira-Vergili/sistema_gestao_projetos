package com.marllon.vieira.vergili.sistema_gestao_projetos.Repository;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;



/**Crie CommentRepository com métodos:
 * findByTaskId ordenado por createdAt,
 * e countByTaskId.**/
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {



    @Query("SELECT c FROM Comment c WHERE c.task.id =:taskId")
    List<Comment> findByTaskId(@Param("taskId") Long taskId);

    Integer countByTaskId(Long taskId);



}
