package com.marllon.vieira.vergili.sistema_gestao_projetos.Entity;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**Entidade Usuário
 *
 * Responsável pela captura do Email, Senha, nome Completo,
 * Regra de acesso, URL do seu avatar, se está ativo ou não,
 *Quando foi criado o usuário, e quando foi a data da última atualização
 *
 *
 * RELACIONAMENTOS:
 *
 *  - Muitos usuários relacionando com muitos projetos.
 *  - Um usuário relacionando a uma Tarefa dentro dos projetos.
 * */

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter

public class User {


    @Id
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;


    @NotBlank(message = "O campo email é necessário preencher")
    @Email(message = "Retorne um formato de email válido")
    @Column(name = "email", unique = true, nullable = false)
    private String email;


    @NotBlank(message = "O campo senha não pode ficar vazio")
    @Column(name = "password", nullable = false)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
            message = "A senha deve ter pelo menos 6 caracteres, incluindo uma letra maiúscula, uma minúscula, um número e um caractere especial"
    )

    @Min(value = 6, message = "A senha deve conter pelo menos 6 caracteres")
    private String password;

    @NotBlank(message = "Nome completo não pode ficar vazio")
    @Column(nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole Role;

    @Column(name = "avatarURL", length = 255)
    private String avatarUrl;


    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean active;

    @NotBlank
    @Column(nullable = false, updatable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = true)
    @NotBlank
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @CreatedDate
    private LocalDateTime updatedAt;



    /**Relacionamento de Muitos Uusários para muitos projetos*/
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "project_members", joinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> project;

    /**Relacionamento de um usuário para muitas tarefas*/
    @OneToMany(mappedBy = "assignedTo")
    private List<Task> task = new ArrayList<>();


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
