package com.marllon.vieira.vergili.sistema_gestao_projetos.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Table(name = "comments")
public class Comment implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private Long id;


    @NotBlank(message = "Conteúdo do comentário é obrigatório")
    @Column(nullable = false)
    @Size(min = 3, max = 1000, message = "O comentário deve possuir no mínimo 3 caracteres, e máximo de 1000 Caracteres")
    private String content;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    @NotNull(message = "Tarefa é obrigatória")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "Autor é obrigatório")
    private User author;

    @Column(nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @CreatedDate
    private LocalDateTime createdAt;


    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
    }
}
