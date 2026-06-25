package com.br.leone.exception;

public class DadosJaCadastradosException extends RuntimeException {
    public DadosJaCadastradosException() {
        super("Dados invalidos ou já cadastrados");
    }
}
