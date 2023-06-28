package com.projecto.serviconotas.exception;

public class EstudanteNotFoundException extends RuntimeException {
    
    public EstudanteNotFoundException(Long id) {
        super("O estudante com id: " + id + ", nao existe na base de dados!");
    }
}
