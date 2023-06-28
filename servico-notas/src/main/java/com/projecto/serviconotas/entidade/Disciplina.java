package com.projecto.serviconotas.entidade;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "disciplina")
public class Disciplina {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Nome nao pode estar em branco!")
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "codigo", nullable = false, unique = true)
    private Long codigo;

    @Column(name = "ects", nullable = false)
    private Long ects;

    @JsonIgnore
    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL)
    private List<Nota> notas;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "disciplina_estudante",
    joinColumns = @JoinColumn(name = "disciplina_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "estudante_id", referencedColumnName = "id"))
    private Set<Estudante> estudantes;


    public Disciplina(String nome, Long codigo, Long ects) {
        this.nome = nome;
        this.codigo = codigo;
        this.ects = ects;
    }

    public Disciplina() {
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

    public Long getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getEcts() {
        return this.ects;
    }

    public void setEcts(Long ects) {
        this.ects = ects;
    }

    public List<Nota> getNotas() {
        return this.notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public Set<Estudante> getEstudantes() {
        return this.estudantes;
    }

    public void setEstudantes(Set<Estudante> estudantes) {
        this.estudantes = estudantes;
    }

}
