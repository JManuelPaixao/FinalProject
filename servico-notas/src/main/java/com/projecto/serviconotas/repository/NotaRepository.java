package com.projecto.serviconotas.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.projecto.serviconotas.entidade.Nota;

public interface NotaRepository extends CrudRepository<Nota, Long> {
    Optional<Nota> findByEstudanteIdAndDisciplinaId(Long estudanteId, Long disciplinaId);
    @Transactional
    void deleteByEstudanteIdAndDisciplinaId(Long estudanteId, Long disciplinaId);
    List<Nota> findByDisciplinaId(Long disciplinaId);
    List<Nota> findByEstudanteId(Long estudanteId);
}
