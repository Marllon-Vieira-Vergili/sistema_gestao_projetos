package com.marllon.vieira.vergili.sistema_gestao_projetos.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.TaskPriority;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @Column(nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Campo Título não pode ficar em branco")
    @Size(min = 3, max = 50, message = "O campo deve ter no mínimo 3 caracteres e no máximo 50 caracteres")
    private String title;

    @Column
    @Size(max = 2000, message = "O campo descrição da tarefa deve possuir no máximo 2000 caracteres")
    private  String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo Status da Tarefa não pode ficar em branco")
    private TaskStatus status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo Prioridade da Tarefa não pode ficar em branco")
    private TaskPriority priority;


    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @NotNull(message = "Projeto é obrigatório")
    private Project project;

    @Column(name = "due_date")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @CreatedDate
    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "assigned_to_id")
    private User assignedTo;

    @Column(name = "attachments_url")
    @ElementCollection
    @CollectionTable(name = "tasks_attachment", joinColumns = @JoinColumn(name = "task_id"))
    private Set<String> attachments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "created_by_id", nullable = false)
    @NotNull(message = "A tarefa deve possuir um usuário associado")
    private User createdBy;

    @Column(updatable = false, nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(updatable = true)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @CreatedDate
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "task", orphanRemoval = true,
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Comment> comments;



    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){

        this.updatedAt = LocalDateTime.now();
    }
}


