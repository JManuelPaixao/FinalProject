package com.projecto.serviconotas.exception;

public class NotaNotFoundException extends RuntimeException {
    
    public NotaNotFoundException(Long estudanteId, Long disciplinaId) {
        super("A nota com id de disciplina: " + disciplinaId 
            + " e id de estudante: " + estudanteId + ", nao existe na base de dados!");
    }
}
