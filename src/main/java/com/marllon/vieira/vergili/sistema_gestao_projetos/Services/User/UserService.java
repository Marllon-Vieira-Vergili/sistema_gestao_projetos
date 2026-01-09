package com.marllon.vieira.vergili.sistema_gestao_projetos.Services.User;

import com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.request.user.ChangePasswordRequest;
import com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.request.user.UpdateUserRequest;
import com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.response.user.UserResponse;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.UserRole;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Crie a interface UserService com métodos: findById,
 * findAll com paginação, update, changePassword, uploadAvatar,
 * delete (soft delete apenas mudando active para false), e findByRole.
 * A implementação UserServiceImpl deve validar se o usuário existe
 * antes de qualquer operação, verificar a senha atual ao trocar senha,
 * e usar o serviço de upload de arquivo ao salvar avatar.**/

public interface UserService {


    UserResponse findById(Long userId);

    Page<UserResponse> findAll(Pageable pageable);

    UserResponse updateUser(Long id, UpdateUserRequest user);

    void changePassword(Long id, ChangePasswordRequest newPassword);

    void uploadAvatar(Long id, String url);


    void deleteUser(Long id);

    List<UserResponse> findByRole(UserRole role);

}
