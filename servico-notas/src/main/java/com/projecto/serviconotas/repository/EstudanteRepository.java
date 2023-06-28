package com.projecto.serviconotas.repository;

import org.springframework.data.repository.CrudRepository;

import com.projecto.serviconotas.entidade.Estudante;

public interface EstudanteRepository extends CrudRepository<Estudante, Long> {
    
}
