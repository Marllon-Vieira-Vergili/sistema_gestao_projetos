package com.marllon.vieira.vergili.sistema_gestao_projetos.Services.Auth;


import com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.request.authentication.LoginRequest;
import com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.request.authentication.RegisterRequest;
import com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.response.authentication.AuthResponse;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.User;

/**
 * AuthService
 * Crie a interface AuthService com os métodos:
 * register recebendo RegisterRequest e retornando AuthResponse,
 * login recebendo LoginRequest e retornando AuthResponse,
 * e refreshToken recebendo o token atual e retornando um novo.
 */

public interface AuthService {

    /**
     * Método Registar requisição, passando como
     * @Param RegisterRequest e retornando
     * @return AuthResponse como resposta
     * */
   AuthResponse register(RegisterRequest register);


   /**
    * Método login para acessar o sistema, passando como
    * @Param LoginRequest como valor de entrada de dados
    * @return  AuthResponse como resposta*/
   AuthResponse login(LoginRequest login);

    /**
     * Método para atualizar o token passando como
     * @Param uma string com o valor atual do token
     * @return  AuthResponse como resposta com o token atualizado*/
   AuthResponse refreshToken(String actualToken);
}
