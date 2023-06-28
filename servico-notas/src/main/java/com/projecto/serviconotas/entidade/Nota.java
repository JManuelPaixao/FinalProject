package com.projecto.serviconotas.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "nota",
    uniqueConstraints = {
    @UniqueConstraint(columnNames = {"estudante_id", "disciplina_id"})
    })
public class Nota {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Min(value = 0)
    @Max(value = 20)
    @Column(name = "valor", nullable = false)
    private Long valor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "estudante_id", referencedColumnName = "id")
    private Estudante estudante;

    @ManyToOne(optional = false)
    @JoinColumn(name = "disciplina_id", referencedColumnName = "id")
    private Disciplina disciplina;


    public Nota(Long valor, Estudante estudante, Disciplina disciplina) {
        this.valor = valor;
        this.estudante = estudante;
        this.disciplina = disciplina;
    }

    public Nota() {
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getValor() {
        return this.valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public Estudante getEstudante() {
        return this.estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public Disciplina getDisciplina() {
        return this.disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

}
