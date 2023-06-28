package com.projecto.serviconotas.repository;

import org.springframework.data.repository.CrudRepository;

import com.projecto.serviconotas.entidade.Disciplina;

public interface DisciplinaRepository extends CrudRepository<Disciplina, Long> {
    
}