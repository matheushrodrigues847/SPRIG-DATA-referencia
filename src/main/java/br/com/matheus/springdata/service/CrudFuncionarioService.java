package br.com.matheus.springdata.service;

import br.com.matheus.springdata.orm.Funcionario;
import br.com.matheus.springdata.repository.CargoRepository;
import br.com.matheus.springdata.repository.FuncionarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    private final CrudCargoService crudCargoService;
    private Scanner scanner = new Scanner(System.in);

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CrudCargoService crudCargoService){
        this.funcionarioRepository = funcionarioRepository;
        this.crudCargoService = crudCargoService;
    }

    public void iniciar(Scanner scanner){
        System.out.println("Informe a sua opcao: ");
        System.out.println("1- cadastrar um novo funcionario");
        System.out.println("2- editar um funcionario");
        System.out.println("3- visualizar os funcionarios");
        System.out.println("4- deletar um funcionario");
        System.out.println("5- buscar Funcionario Por Nome");
        System.out.println("6- buscar Funcionario Por Nome");

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
                break;
            }
            case 5:{
                buscarFuncionarioPorNome();
            }

        }
    }

    private void salvar(){
        System.out.println("Informe o nome do funcionario: ");
        String nome = scanner.nextLine();

        System.out.println("Informe o cpf do funcionario: ");
        String cpf = scanner.nextLine();

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);

        funcionario.setCargo(crudCargoService.getCargoById(4));

        funcionarioRepository.save(funcionario);
    }

    private void atualizar(){
        visualizar();

        System.out.println("Informe o numero do id: ");
        int id = Integer.parseInt(scanner.nextLine());

        Optional<Funcionario> cargo = funcionarioRepository.findById(id);

        if(cargo.isPresent()){
            Funcionario funcionario = cargo.get();

            System.out.println("Informe o nome do cargo: ");

            funcionario.setNome(scanner.nextLine());

            funcionarioRepository.save(funcionario);
        }else{
            System.out.println("Funcionario nao encontrado");
        }

    }


    private void visualizar(){
        System.out.println("Informe o numero da pagina que deseja visualizar: ");
        Integer pagina = Integer.parseInt(scanner.nextLine());

        //paginacao
        Pageable pageable = PageRequest.of(pagina, 2, Sort.by(Sort.Direction.ASC, "nome"));

        Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);

        System.out.println(funcionarios);
        System.out.println("Numero da pagina " + funcionarios.getNumber());
        System.out.println("total de elementos " + funcionarios.getTotalElements());

        funcionarios.forEach(c -> System.out.println(c));

    }

    private void delete(){
        visualizar();

        System.out.println("Informe o id do funcionario que deseja excluir: ");
        int id = Integer.parseInt(scanner.nextLine());

        funcionarioRepository.deleteById(id);
    }

    private void buscarFuncionarioPorNome(){
        String nome = scanner.nextLine();
        funcionarioRepository.findByNome(nome).forEach(f -> System.out.println("Funcionario: "+ f.getNome()));
    }


}
