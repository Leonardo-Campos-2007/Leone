package com.br.leone.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(Long id) {

        super("Usuario não encontrado com id " + id);
    }
}
