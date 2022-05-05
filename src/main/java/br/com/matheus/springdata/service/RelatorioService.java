package br.com.matheus.springdata.service;

import br.com.matheus.springdata.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Service
public class RelatorioService {
    private final FuncionarioRepository funcionarioRepository;
    private Scanner scanner = new Scanner(System.in);

    public RelatorioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void iniciar(Scanner scanner){
//        buscaPorDataContratacao(scanner);
        buscaPorIdNomeCpf();
    }

    private void buscaPorDataContratacao(Scanner scanner){
        System.out.println("Informe a data");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(scanner.nextLine(), dateTimeFormatter);

        funcionarioRepository.findByDataDeContratacaoMaior(data).forEach(f -> System.out.println(f.getNome()));
    }

    private void buscaPorIdNomeCpf(){
        funcionarioRepository.findIdNomeCpf().forEach(f -> System.out.println("id: " + f.getId() +"| nome: "+ f.getNome()+ " | cpf: "+f.getCpf()));
    }

}
