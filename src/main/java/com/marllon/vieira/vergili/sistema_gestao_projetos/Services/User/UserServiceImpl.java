package com.marllon.vieira.vergili.sistema_gestao_projetos.Services.User;


import com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.request.user.ChangePasswordRequest;
import com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.request.user.UpdateUserRequest;
import com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.response.user.UserResponse;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.UserRole;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.User;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Exceptions.NotFoundException;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Repository.UserRepository;
import com.marllon.vieira.vergili.sistema_gestao_projetos.config.PasswordConfig;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**** A implementação UserServiceImpl deve validar se o usuário existe
 * antes de qualquer operação, verificar a senha atual ao trocar senha,
 * e usar o serviço de upload de arquivo ao salvar avatar.**/

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordConfig passwordConfig;


    @Override
    public UserResponse findById(Long userId) {

        Optional<User> optUser = userRepository.findById(userId);

        if(optUser.isPresent()){

            User founded = optUser.get();

            return new UserResponse(founded.getId(),
                    founded.getEmail(),
                    founded.getFullName(),
                    founded.getRole(),
                    founded.getAvatarUrl(),
                    founded.isActive(),
                    founded.getCreatedAt());
        }

        return null;
    }

    @Override
    public Page<UserResponse> findAll(Pageable pageable) {

        Page<User> users = userRepository.findAll(pageable);


        return users.map(user ->
                new UserResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getFullName(),
                        user.getRole(),
                        user.getAvatarUrl(),
                        user.isActive(),
                        user.getCreatedAt()
                ));
    }

    @Override
    public UserResponse updateUser(Long id, UpdateUserRequest user) {

        //Localizar User
        User usuario = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Usuário não encontrado"));

        //Alterar os campos do usuário
        usuario.setFullName(user.fullName());
        usuario.setAvatarUrl(user.avatarUrl());
        usuario.setActive(user.active());
        usuario.setUpdatedAt(LocalDateTime.now());

        //Salvar atualizações
        userRepository.save(usuario);

        return new UserResponse(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getFullName(),
                usuario.getRole(),
                usuario.getAvatarUrl(),
                usuario.isActive(),
                usuario.getCreatedAt());
    }

    @Override
    public void uploadAvatar(Long id, String url) {

        //Localizar User
        User usuario = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Usuário não encontrado"));

        //Obter avatar
        try{
            usuario.setAvatarUrl(url);
        } catch (RuntimeException e) {
            throw new Error("Não foi possível alterar a foto do perfil" + e);
        }

        userRepository.save(usuario);

    }

    @Override
    public void changePassword(Long id, ChangePasswordRequest newPassword) {

        //Localizar User
        User usuario = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Usuário não encontrado"));

        String senhaAtual = usuario.getPassword();

        if(passwordConfig.passwordEncoder().matches
                (senhaAtual, newPassword.currentPassword())){

            //Alterar a senha
            usuario.setPassword(passwordConfig.passwordEncoder()
                    .encode(newPassword.confirmPassword()));

            //Salvar nova senha
            userRepository.save(usuario);
        }else{
            throw new RuntimeException("Não foi possível atualizar a sua senha");
        }

    }

    /*Não apagar usuário no banco, senão tudo que está vinculado
    * a ele será apagado.
    *
    * Apenas, deixá-lo inativo
    * */
    @Override
    public void deleteUser(Long id) {

        //Localizar User
        User usuario = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Usuário não encontrado"));

        usuario.setActive(false);
        usuario.setUpdatedAt(LocalDateTime.now());

        userRepository.save(usuario);
    }

    @Override
    public List<UserResponse> findByRole(UserRole role) {

        //Localizar User
        List<User> usuario = userRepository.findByRoleAndActiveTrue(role, true);

        if(!usuario.isEmpty() && usuario.getLast().isActive()){

            return usuario.stream()
                    .map(user -> new UserResponse
                            (user.getId(),
                                    user.getEmail(),
                                    user.getFullName(),
                                    user.getRole(),
                                    user.getAvatarUrl(),
                                    user.isActive(),
                                    user.getCreatedAt())).toList();
        }
        return null;
    }
}
