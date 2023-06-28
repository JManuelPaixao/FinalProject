package com.projecto.serviconotas.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projecto.serviconotas.entidade.Disciplina;
import com.projecto.serviconotas.entidade.Estudante;
import com.projecto.serviconotas.exception.EstudanteNotFoundException;
import com.projecto.serviconotas.repository.EstudanteRepository;

@Service
public class EstudanteService {
    
    @Autowired
    EstudanteRepository estudanteRepository;

    public Estudante getEstudante(Long id) {
        Optional<Estudante> estudante = estudanteRepository.findById(id);
        Estudante estudanteDesembrulhado = desembrulhaEstudante(estudante, id);
        return estudanteDesembrulhado;
    }

    public List<Estudante> getEstudantes() {
        return (List<Estudante>) estudanteRepository.findAll();
    }

    public Set<Disciplina> getDisciplinasInscritas(Long id) {
        Estudante estudante = getEstudante(id);
        return estudante.getDisciplinas();
    }

    public Estudante postEstudante(Estudante estudante) {
        return estudanteRepository.save(estudante);
    }

    public void deleteEstudante(Long id) {
        estudanteRepository.deleteById(id);
    }


    static Estudante desembrulhaEstudante(Optional<Estudante> entidade, Long id) {
        if (entidade.isPresent()) {
            return entidade.get();
        } else {
            throw new EstudanteNotFoundException(id);
        }
    }
}
