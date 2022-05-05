package br.com.matheus.springdata.service;

import br.com.matheus.springdata.orm.Funcionario;
import br.com.matheus.springdata.repository.FuncionarioRepository;
import br.com.matheus.springdata.specification.SpecificationFuncionario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDinamico {
    private final FuncionarioRepository funcionarioRepository;
    private final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository){
        this.funcionarioRepository = funcionarioRepository;
    }

    public void iniciar(Scanner scanner){
        System.out.println("Informe o nome: ");
        String nome = scanner.nextLine();

        if(nome.equalsIgnoreCase("NULL")){
            nome = null;
        }

        System.out.println("Informe o cpf: ");
        String cpf = scanner.nextLine();

        if(cpf.equalsIgnoreCase("NULL")){
            cpf = null;
        }

        System.out.println("Informe o data: ");
        String data = scanner.nextLine();
        LocalDate dataContratacao;

        if(data.equalsIgnoreCase("")){
            dataContratacao = null;
        }else{
            dataContratacao = LocalDate.parse(data, dateTimeFormatter);
        }


        System.out.println("data contratacao "+dataContratacao);
        List<Funcionario> funcionarios = funcionarioRepository.
                findAll(Specification.
                        where(
                                SpecificationFuncionario.nome(nome)
                                        .or(SpecificationFuncionario.cpf(cpf))
                                        .or(SpecificationFuncionario.dataContratacao(dataContratacao))
                        ));

        funcionarios.forEach(f -> System.out.println(f));
    }

}
