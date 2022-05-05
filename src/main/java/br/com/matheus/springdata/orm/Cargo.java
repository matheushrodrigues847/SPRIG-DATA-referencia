package br.com.matheus.springdata.orm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cargos")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeDoCargo;

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios = new ArrayList<>();

    public Cargo() {
    }

    public Integer getId() {
        return id;
    }

    public String getNomeDoCargo() {
        return nomeDoCargo;
    }

    public void setNomeDoCargo(String nomeDoCargo) {
        this.nomeDoCargo = nomeDoCargo;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", nomeDoCargo='" + nomeDoCargo + '\'' +
                '}';
    }
}
