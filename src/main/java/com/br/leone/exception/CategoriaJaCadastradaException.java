package com.br.leone.exception;

public class CategoriaJaCadastradaException extends RuntimeException {
    public CategoriaJaCadastradaException() {
        super("Já existe uma categoria com esse nome nesse nível hierárquico.");
    }
}
