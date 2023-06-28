package com.projecto.serviconotas.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projecto.serviconotas.entidade.Nota;
import com.projecto.serviconotas.service.NotaService;

@RestController
public class NotaController {
    
    @Autowired
    NotaService notaService; 

    @GetMapping("/nota/estudante/{estudanteId}/disciplina/{disciplinaId}")
    public ResponseEntity<Nota> getNota(@PathVariable Long estudanteId, @PathVariable Long disciplinaId) {
        Nota nota = notaService.getNota(estudanteId, disciplinaId);
        return new ResponseEntity<>(nota, HttpStatus.OK);
    }

    @GetMapping("/nota/disciplina/{disciplinaId}")
    public ResponseEntity<List<Nota>> getDisciplinaNotas(@PathVariable Long disciplinaId) {
        List<Nota> notas = notaService.getDisciplinaNotas(disciplinaId);
        return new ResponseEntity<>(notas, HttpStatus.OK);
    }

    @GetMapping("/nota/estudante/{estudanteId}")
    public ResponseEntity<List<Nota>> getEstudanteNotas(@PathVariable Long estudanteId) {
        List<Nota> notas = notaService.getEstudanteNotas(estudanteId);
        return new ResponseEntity<>(notas, HttpStatus.OK);
    }

    @GetMapping("/nota/todos")
    public ResponseEntity<List<Nota>> getNotas() {
        List<Nota> notas = notaService.getNotas();
        return new ResponseEntity<>(notas, HttpStatus.OK);
    }

    @PostMapping("/nota/estudante/{estudanteId}/disciplina/{disciplinaId}")
    public ResponseEntity<Nota> postNota(@Valid @RequestBody Nota nota, @PathVariable Long estudanteId, @PathVariable Long disciplinaId) {
        return new ResponseEntity<>(notaService.postNota(nota, estudanteId, disciplinaId), HttpStatus.CREATED);
    }

    @PutMapping("/nota/estudante/{estudanteId}/disciplina/{disciplinaId}")
    public ResponseEntity<Nota> putNota(@Valid @RequestBody Nota nota, @PathVariable Long estudanteId, @PathVariable Long disciplinaId) {
        return new ResponseEntity<>(notaService.putNota(nota.getValor(), estudanteId, disciplinaId), HttpStatus.OK);
    }

    @DeleteMapping("/nota/estudante/{estudanteId}/disciplina/{disciplinaId}")
    public ResponseEntity<HttpStatus> deleteNota(@PathVariable Long estudanteId, @PathVariable Long disciplinaId) {
        notaService.deleteNota(estudanteId, disciplinaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
