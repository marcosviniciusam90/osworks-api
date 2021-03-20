package com.algaworks.osworks.domain.exception;

public class EntidadeNaoEncontraException extends NegocioException{

    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontraException(String message) {
        super(message);
    }
}
