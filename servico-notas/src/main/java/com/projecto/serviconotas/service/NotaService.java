package com.projecto.serviconotas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projecto.serviconotas.entidade.Disciplina;
import com.projecto.serviconotas.entidade.Estudante;
import com.projecto.serviconotas.entidade.Nota;
import com.projecto.serviconotas.exception.EstudanteNotInscritoException;
import com.projecto.serviconotas.exception.NotaNotFoundException;
import com.projecto.serviconotas.repository.DisciplinaRepository;
import com.projecto.serviconotas.repository.EstudanteRepository;
import com.projecto.serviconotas.repository.NotaRepository;

@Service
public class NotaService {
    
    @Autowired
    NotaRepository notaRepository;

    @Autowired
    EstudanteRepository estudanteRepository;

    @Autowired
    DisciplinaRepository disciplinaRepository;

    public Nota getNota(Long estudanteId, Long disciplinaId) {
        Optional<Nota> nota = notaRepository.findByEstudanteIdAndDisciplinaId(estudanteId, disciplinaId);
        return desembrulhaNota(nota, estudanteId, disciplinaId);
    }

    public List<Nota> getDisciplinaNotas(Long disciplinaId) {
        return notaRepository.findByDisciplinaId(disciplinaId);
    }

    public List<Nota> getEstudanteNotas(Long estudanteId) {
        return notaRepository.findByEstudanteId(estudanteId);
    }

    public List<Nota> getNotas() {
        return (List<Nota>) notaRepository.findAll();
    }

    public Nota postNota(Nota nota, Long estudanteId, Long disciplinaId) {
        Estudante estudante = EstudanteService.desembrulhaEstudante(estudanteRepository.findById(estudanteId), estudanteId);
        Disciplina disciplina = DisciplinaService.desembrulhaDisciplina(disciplinaRepository.findById(disciplinaId), disciplinaId);
        if (!estudante.getDisciplinas().contains(disciplina)) {
            throw new EstudanteNotInscritoException(estudanteId, disciplinaId);
        }
        nota.setEstudante(estudante);
        nota.setDisciplina(disciplina);
        return notaRepository.save(nota);
    }

    public Nota putNota(Long valor, Long estudanteId, Long disciplinaId) {
        Optional<Nota> nota = notaRepository.findByEstudanteIdAndDisciplinaId(estudanteId, disciplinaId);
        Nota notaDesembrulhada = desembrulhaNota(nota, estudanteId, disciplinaId);
        notaDesembrulhada.setValor(valor);
        return notaRepository.save(notaDesembrulhada);
    }

    public void deleteNota(Long estudanteId, Long disciplinaId) {
        notaRepository.deleteByEstudanteIdAndDisciplinaId(estudanteId, disciplinaId);
    }


    static Nota desembrulhaNota(Optional<Nota> entidade, Long estudanteId, Long disciplinaId) {
        if (entidade.isPresent()) {
            return entidade.get();
        } else {
            throw new NotaNotFoundException(estudanteId, disciplinaId);
        }
    }
}
