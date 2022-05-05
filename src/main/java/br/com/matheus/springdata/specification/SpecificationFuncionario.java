package br.com.matheus.springdata.specification;

import br.com.matheus.springdata.orm.Funcionario;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class SpecificationFuncionario {
    //o specification substitui o criteria
    public static Specification<Funcionario> nome(String nome){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("nome"), "%"+ nome +"%"));
    }

    public static Specification<Funcionario> cpf(String cpf){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("cpf"), cpf));
    }

    public static Specification<Funcionario> dataContratacao(LocalDate data){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("dataContratacao"), data));
    }


}
