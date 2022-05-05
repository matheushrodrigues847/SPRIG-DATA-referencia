package br.com.matheus.springdata.orm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "unidades")
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private String endereco;

    @ManyToMany(mappedBy = "unidades", fetch = FetchType.EAGER)
    private List<Funcionario> funcionarios = new ArrayList<>();

    public Unidade() {
    }

    public Unidade(String descricao, String endereco) {
        this.descricao = descricao;
        this.endereco = endereco;
    }

    public Integer getId() {
        return id;
    }



    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return "Unidade{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
