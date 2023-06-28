package com.projecto.serviconotas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.projecto.serviconotas.exception.DisciplinaNotFoundException;
import com.projecto.serviconotas.exception.EstudanteNotFoundException;
import com.projecto.serviconotas.exception.EstudanteNotInscritoException;
import com.projecto.serviconotas.exception.NotaNotFoundException;
import com.projecto.serviconotas.exception.RespostaErro;

@ControllerAdvice
public class AplicacaoExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler({DisciplinaNotFoundException.class, NotaNotFoundException.class, EstudanteNotFoundException.class, EstudanteNotInscritoException.class})
    public ResponseEntity<Object> handleRecursoNotFoundException(RuntimeException exception) {
        RespostaErro erro = new RespostaErro(Arrays.asList(exception.getMessage()));
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> erros = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((erro) -> erros.add(erro.getDefaultMessage()));
        return new ResponseEntity<>(new RespostaErro(erros), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(EmptyResultDataAccessException ex) {
        RespostaErro erro = new RespostaErro(Arrays.asList("Não é possivel apagar um elemento que não existe."));  
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        RespostaErro erro = new RespostaErro(Arrays.asList("Violacao de Integridade de Dados: nao e possivel efectuar o pedido."));  
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

}
