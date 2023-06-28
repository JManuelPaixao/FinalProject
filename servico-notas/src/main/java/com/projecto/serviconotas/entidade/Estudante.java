package com.projecto.serviconotas.entidade;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "estudante")
public class Estudante {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Nome nao pode estar em branco!")
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "numero", nullable = false, unique = true)
    private Long numero;

    @Past(message = "Data de nascimento nao pode ser no futuro!")
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @JsonIgnore
    @OneToMany(mappedBy = "estudante", cascade = CascadeType.ALL)
    private List<Nota> notas;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "disciplina_estudante",
    joinColumns = @JoinColumn(name = "estudante_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "disciplina_id", referencedColumnName = "id"))
    private Set<Disciplina> disciplinas;


    public Estudante(String nome, Long numero, LocalDate dataNascimento) {
        this.nome = nome;
        this.numero = numero;
        this.dataNascimento = dataNascimento;
    }

    public Estudante() {
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getNumero() {
        return this.numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Nota> getNotas() {
        return this.notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public Set<Disciplina> getDisciplinas() {
        return this.disciplinas;
    }

    public void setDisciplinas(Set<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

}
