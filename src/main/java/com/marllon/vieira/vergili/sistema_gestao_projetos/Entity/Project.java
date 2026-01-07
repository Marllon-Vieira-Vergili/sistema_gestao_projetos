package com.marllon.vieira.vergili.sistema_gestao_projetos.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.ProjectStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**Entidade Projeto
 *
 * Responsável
 *
 *
 * RELACIONAMENTOS:
 *
 *
 * */

@Entity
@Table(name = "projects")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Project {


    @Id
    @Setter(AccessLevel.NONE)
    @Column(nullable = false, unique = true, insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;


    @Column(nullable = false)
    @NotBlank(message = "Campo nome não pode ficar em branco")
    @Size(min = 3, message = "Nome deve possuir mínimo de 3 caracteres", max = 100)

    private String name;

    @Column(name = "description")
    @Size(max = 1000, message = "A descrição não deve passar de 1000 caracteres")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "O status do projeto não pode ficar vazio")
    private ProjectStatus status;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @NotNull
    @CreatedDate
    private LocalDateTime startDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @NotNull
    @CreatedDate
    private LocalDateTime endDate;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @CreatedDate
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @CreatedDate
    private LocalDateTime updatedAt;


    /**RELACIONAMENTOS**/
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "project_members", joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();


    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;


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
