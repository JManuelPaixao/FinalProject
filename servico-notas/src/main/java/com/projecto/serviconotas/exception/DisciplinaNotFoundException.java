package com.projecto.serviconotas.exception;

public class DisciplinaNotFoundException extends RuntimeException {
    
    public DisciplinaNotFoundException(Long id) {
        super("A disciplina com id: " + id + ", nao existe na base de dados!");
    }
}
