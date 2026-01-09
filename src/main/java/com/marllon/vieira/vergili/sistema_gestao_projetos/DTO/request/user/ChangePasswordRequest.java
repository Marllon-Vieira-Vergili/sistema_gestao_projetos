package com.marllon.vieira.vergili.sistema_gestao_projetos.DTO.request.user;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequest(

        @NotBlank(message = "Por favor, digite sua senha atual")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
                message = "Sua senha atual não está correta. Ela contém pelo menos uma letra maiúscula e uma minúscula, " +
                        "um número, e um caractere especial."
        )
        @Size(min = 6, message = "A senha deve conter pelo menos 6 caracteres")
        String currentPassword,


        @NotBlank(message = "Por favor, Digite uma nova senha")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
                message = "Sua nova senha não está correta. Ela deve conter, pelo menos uma letra maiúscula e uma minúscula, " +
                        "um número, e um caractere especial."
        )
        @Size(min = 6, message = "A nova senha deve conter pelo menos 6 caracteres")
        String newPassword,



        @NotBlank(message = "Por favor, confirme re-digitando sua nova senha")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
                message = "Sua confirmação da nova senha não está conzidente com a nova senha informada. " +
                        "Ela deve conter, pelo menos uma letra maiúscula e uma minúscula, " +
                        "um número, e um caractere especial."
        )
        @Size(min = 6, message = "A nova senha deve conter pelo menos 6 caracteres")
        String confirmPassword
) {
}
