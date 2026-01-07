package com.marllon.vieira.vergili.sistema_gestao_projetos.Repository;

import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.UserRole;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *Crie UserRepository estendendo JpaRepository com métodos customizados:
 * findByEmail retornando Optional, existsByEmail retornando boolean,
 * e findByRoleAndActiveTrue para buscar usuários ativos por role.
 * **/

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<String> findByEmail(String email);

    public boolean existsByEmail(String email);

    public List<User> findByRoleAndActiveTrue(UserRole role);

}
