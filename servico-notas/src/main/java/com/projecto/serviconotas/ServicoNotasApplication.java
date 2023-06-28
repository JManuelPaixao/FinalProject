package com.projecto.serviconotas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projecto.serviconotas.entidade.Disciplina;
import com.projecto.serviconotas.entidade.Estudante;
import com.projecto.serviconotas.repository.DisciplinaRepository;
import com.projecto.serviconotas.repository.EstudanteRepository;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class ServicoNotasApplication implements CommandLineRunner {

	@Autowired
	EstudanteRepository estudanteRepository;
	@Autowired
	DisciplinaRepository disciplinaRepository;

	public static void main(String[] args) {
		SpringApplication.run(ServicoNotasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Estudante[] estudantes = new Estudante[] {
			new Estudante("Jorge Paixao", Long.valueOf(1900981), LocalDate.parse(("1983-10-26"))),
			new Estudante("Joaquim Pato", Long.valueOf(1789000), LocalDate.parse(("1970-11-13"))),
			new Estudante("Afonso Henriques", Long.valueOf(1100123), LocalDate.parse(("1960-01-01")))
		};

		for (int i = 0; i < estudantes.length; i++) {
			estudanteRepository.save(estudantes[i]);
		}

		Disciplina[] disciplinas = new Disciplina[] {
			new Disciplina("Matematica Elementar", Long.valueOf(12992), Long.valueOf(6)),
			new Disciplina("Sociologia", Long.valueOf(14391), Long.valueOf(6)),
			new Disciplina("Computacao", Long.valueOf(15212), Long.valueOf(4)),
			new Disciplina("Etica e Moral", Long.valueOf(10196), Long.valueOf(2)),
			new Disciplina("Samba e Salsa", Long.valueOf(20930), Long.valueOf(6)),
		};

		for (int i = 0; i < disciplinas.length; i++) {
			disciplinaRepository.save(disciplinas[i]);
		}
	}


}
