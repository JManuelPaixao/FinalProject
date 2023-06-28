package com.projecto.serviconotas.web;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projecto.serviconotas.entidade.Disciplina;
import com.projecto.serviconotas.entidade.Estudante;
import com.projecto.serviconotas.service.DisciplinaService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class DisciplinaController {
    
    @Autowired
    DisciplinaService disciplinaService;

    @GetMapping("/disciplina/{id}")
    public ResponseEntity<Disciplina> getDisciplina(@PathVariable Long id) {
        Disciplina disciplina = disciplinaService.getDisciplina(id);
        return new ResponseEntity<>(disciplina, HttpStatus.OK);
    }

    @GetMapping("/disciplina/todos")
    public ResponseEntity<List<Disciplina>> getDisciplinas() {
        List<Disciplina> disciplinas = disciplinaService.getDisciplinas();
        return new ResponseEntity<>(disciplinas, HttpStatus.OK);
    }

    @GetMapping("/disciplina/{id}/estudantes")
    public ResponseEntity<Set<Estudante>> getEstudantesInscritos(@PathVariable Long id) {
        Set<Estudante> estudantes = disciplinaService.getEstudantesInscritos(id);
        return new ResponseEntity<>(estudantes, HttpStatus.OK);
    }

    @PostMapping("/disciplina")
    public ResponseEntity<Disciplina> postDisciplina(@Valid @RequestBody Disciplina disciplina) {
        return new ResponseEntity<>(disciplinaService.postDisciplina(disciplina), HttpStatus.CREATED);
    }

    @DeleteMapping("/disciplina/{id}")
    public ResponseEntity<HttpStatus> deleteDisciplina(@PathVariable Long id) {
        disciplinaService.deleteDisciplina(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{disciplinaId}/estudante/{estudanteId}")
    public ResponseEntity<Disciplina> inscreverEstudanteEmDisciplina(@PathVariable Long disciplinaId, @PathVariable Long estudanteId) {
        return new ResponseEntity<>(disciplinaService.inscreverEstudanteEmDisciplina(estudanteId, disciplinaId), HttpStatus.OK);
    }

}
