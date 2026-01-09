package com.marllon.vieira.vergili.sistema_gestao_projetos.Services.Auth;
import com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.request.authentication.LoginRequest;
import com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.request.authentication.RegisterRequest;
import com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.response.authentication.AuthResponse;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.Enums.UserRole;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Entity.User;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Exceptions.EmailFoundException;
import com.marllon.vieira.vergili.sistema_gestao_projetos.Repository.UserRepository;
import com.marllon.vieira.vergili.sistema_gestao_projetos.config.JwtTokenCreator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
* Na implementação AuthServiceImpl, o register deve verificar se o email
 * já existe, criptografar a senha com BCrypt, definir role como
 * DEVELOPER se não vier, salvar o usuário e gerar o token JWT.
 *
 * O login deve buscar por email,
 * validar a senha com BCrypt, e gerar o token se válido.
 * */

@Service
@Transactional
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  PasswordEncoder encoder;


    @Autowired
    private JwtTokenCreator token;


    @Override
    public AuthResponse register(RegisterRequest register) {

        //Validação se o email já existe
        boolean existEmail = userRepository.existsByEmail(register.email());

        if (existEmail){
            throw new EmailFoundException("Este Email já está cadastrado!");
        }

        //Codificar senha com bcrypt
        String encodedPassword = encoder.encode(register.password());

        //Instanciar novo objeto usuário
        User newUser = new User();

        //Definir sempre por padrão "ROLE como "Developer"
        if (newUser.getRole() == null){
            newUser.setRole(UserRole.DEVELOPER);
        }

        newUser.setFullName(register.fullName());
        newUser.setActive(true);
        newUser.setAvatarUrl(null);
        newUser.setCreatedAt(LocalDateTime.now());

        //Salvando o usuário registrado no banco
        userRepository.save(newUser);

        //Gerando o token do usuário
        String userToken = token.createToken(newUser);


        return new AuthResponse(
                userToken,
                newUser.getId(),
                newUser.getEmail(),
                newUser.getFullName(),
                newUser.getRole());
    }

    @Override
    public AuthResponse login(LoginRequest login) {

        //Recebendo o email atual
        User email = userRepository.findByEmail(login.email())
                .orElseThrow(()-> new RuntimeException("Credenciais inválidas"));

        //Validar a senha
        if(!encoder.matches(login.password(), email.getPassword())){
            throw new RuntimeException("Credenciais inválidas!");
        }

        String userToken = token.createToken(email);

        return new AuthResponse(
                userToken,
                email.getId(),
                email.getEmail(),
                email.getFullName(),
                email.getRole()
        );


    }

    @Override
    public AuthResponse refreshToken(String actualToken) {

       if(!token.validateToken(actualToken)){
           throw new RuntimeException("Token inválido!");
       }

       //Extrair informações do token atual
        String email = token.getEmailFromToken(actualToken);

       //Encontrar o objeto usuário pelo email
       User user = userRepository.findByEmail(email)
               .orElseThrow(() ->
                       new EmailFoundException("Email não localizado!"));

       //Criar novo token a partir desse
        String newToken = token.refreshToken(actualToken);



        return new AuthResponse(
                actualToken,
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getRole()
        );
    }
}
