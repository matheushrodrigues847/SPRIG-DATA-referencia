package br.com.matheus.springdata.service;

import br.com.matheus.springdata.orm.Unidade;
import br.com.matheus.springdata.repository.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudUnidadeService {
    private final UnidadeRepository unidadeRepository;
    private Scanner scanner = new Scanner(System.in);

    public CrudUnidadeService(UnidadeRepository unidadeRepository){
        this.unidadeRepository = unidadeRepository;
    }

    public void iniciar(Scanner scanner){
        System.out.println("Informe a sua opcao: ");
        System.out.println("1- cadastrar um novo unidade");
        System.out.println("2- editar um unidade");
        System.out.println("3- visualizar os unidades");
        System.out.println("4- deletar um unidade");

        int opcao = Integer.parseInt(scanner.nextLine());

        switch (opcao){
            case 1:{
                salvar();
                break;
            }
            case 2:{
                atualizar();
                break;
            }
            case 3:{
                visualizar();
                break;
            }
            case 4:{
                delete();
            }

        }
    }

    private void salvar(){
        System.out.println("Informe o nome do cargo: ");
        String novoUnidade = scanner.nextLine();

        Unidade cargo = new Unidade();
        cargo.setDescricao(novoUnidade);

        unidadeRepository.save(cargo);
    }

    private void atualizar(){
        visualizar();

        System.out.println("Informe o numero do id: ");
        int id = Integer.parseInt(scanner.nextLine());

        Optional<Unidade> cargo = unidadeRepository.findById(id);

        if(cargo.isPresent()){
            Unidade cargoUpdate = cargo.get();

            System.out.println("Informe o nome do cargo: ");

            cargoUpdate.setDescricao(scanner.nextLine());

            unidadeRepository.save(cargoUpdate);
        }else{
            System.out.println("Unidade nao encontrado");
        }

    }

    private void visualizar(){
        List<Unidade> cargos = (List<Unidade>) unidadeRepository.findAll();
        cargos.forEach(c -> System.out.println(c));
    }

    private void delete(){
        visualizar();

        System.out.println("Informe o id do cargo que deseja excluir: ");
        int id = Integer.parseInt(scanner.nextLine());

        unidadeRepository.deleteById(id);
    }

}
