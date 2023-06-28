package com.projecto.serviconotas.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projecto.serviconotas.entidade.Disciplina;
import com.projecto.serviconotas.entidade.Estudante;
import com.projecto.serviconotas.exception.DisciplinaNotFoundException;
import com.projecto.serviconotas.repository.DisciplinaRepository;
import com.projecto.serviconotas.repository.EstudanteRepository;

@Service
public class DisciplinaService {
    
    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    EstudanteRepository estudanteRepository;

    public Disciplina getDisciplina(Long id) {
        Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
        Disciplina disciplinaDesembrulhada = desembrulhaDisciplina(disciplina, id);
        return disciplinaDesembrulhada;
    }

    public List<Disciplina> getDisciplinas() {
        return (List<Disciplina>) disciplinaRepository.findAll();
    }

    public Set<Estudante> getEstudantesInscritos(Long id) {
        Disciplina disciplina = getDisciplina(id);
        return disciplina.getEstudantes();
    }

    public Disciplina postDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public void deleteDisciplina(Long id) {
        disciplinaRepository.deleteById(id);
    }

    public Disciplina inscreverEstudanteEmDisciplina(Long estudanteId, Long disciplinaId) {
        Disciplina disciplina = getDisciplina(disciplinaId);
        Optional<Estudante> estudante = estudanteRepository.findById(estudanteId);
        Estudante estudanteDesembrulhado = EstudanteService.desembrulhaEstudante(estudante, estudanteId);
        disciplina.getEstudantes().add(estudanteDesembrulhado);
        return disciplinaRepository.save(disciplina);
    }


    static Disciplina desembrulhaDisciplina(Optional<Disciplina> entidade, Long id) {
        if (entidade.isPresent()) {
            return entidade.get();
        } else {
            throw new DisciplinaNotFoundException(id);
        }
    }
}
