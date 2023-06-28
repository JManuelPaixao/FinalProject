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
import com.projecto.serviconotas.service.EstudanteService;

@RestController
public class EstudanteController {

    @Autowired
    EstudanteService estudanteService;
    
    @GetMapping("/estudante/{id}")
    public ResponseEntity<Estudante> getEstudante(@PathVariable Long id) {
        Estudante estudante = estudanteService.getEstudante(id);
        return new ResponseEntity<>(estudante, HttpStatus.OK);
    }

    @GetMapping("/estudante/todos")
    public ResponseEntity<List<Estudante>> getEstudantes() {
        List<Estudante> estudantes = estudanteService.getEstudantes();
        return new ResponseEntity<>(estudantes, HttpStatus.OK);
    }

    @GetMapping("/estudante/{id}/disciplinas")
    public ResponseEntity<Set<Disciplina>> getDisciplinasInscritas(@PathVariable Long id) {
        Set<Disciplina> disciplinas = estudanteService.getDisciplinasInscritas(id);
        return new ResponseEntity<>(disciplinas, HttpStatus.OK);
    }

    @PostMapping("/estudante")
    public ResponseEntity<Estudante> postEstudante(@Valid @RequestBody Estudante estudante) {
        return new ResponseEntity<Estudante>(estudanteService.postEstudante(estudante), HttpStatus.CREATED);
    }

    @DeleteMapping("/estudante/{id}")
    public ResponseEntity<HttpStatus> deleteEstudante(@PathVariable Long id) {
        estudanteService.deleteEstudante(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
