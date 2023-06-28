package com.projecto.serviconotas.exception;

public class EstudanteNotInscritoException extends RuntimeException {
    
    public EstudanteNotInscritoException(Long estudanteId, Long disciplinaId) {
        super("O estudante com id: " + estudanteId 
            + ", nao esta inscrito na disciplina com id: " + disciplinaId);
    }
}
