package com.br.leone.dto;

import com.br.leone.enums.Role;
import com.br.leone.enums.TipoConta;

import java.time.LocalDateTime;

public record UserResponseDTO(

        Long id,
        String name,
        String email,
        String telefone,
        String cpf,
        String cnpj,
        TipoConta tipoConta,
        Role role,
        LocalDateTime dataCadastro


) {
}
