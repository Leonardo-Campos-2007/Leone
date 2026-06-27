package com.br.leone.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

    @Email(message = "Email invalido")
    @NotBlank(message = "Email é obrigatorio")
    String email,

    @NotBlank(message = "Senha é obrigatoria")
    String senha

) {

}
