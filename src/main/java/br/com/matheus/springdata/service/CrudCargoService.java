package br.com.matheus.springdata.service;

import br.com.matheus.springdata.orm.Cargo;
import br.com.matheus.springdata.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CrudCargoService {
    private final CargoRepository cargoRepository;
    private Scanner scanner = new Scanner(System.in);

    public CrudCargoService(CargoRepository cargoRepository){
        this.cargoRepository = cargoRepository;
    }

    public void iniciar(Scanner scanner){
        System.out.println("Informe a sua opcao: ");
        System.out.println("1- cadastrar um novo cargo");
        System.out.println("2- editar um cargo");
        System.out.println("3- visualizar os cargos");
        System.out.println("4- deletar um cargo");

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
        String novoCargo = scanner.nextLine();

        Cargo cargo = new Cargo();
        cargo.setNomeDoCargo(novoCargo);

        cargoRepository.save(cargo);
    }

    private void atualizar(){
        visualizar();

        System.out.println("Informe o numero do id: ");
        int id = Integer.parseInt(scanner.nextLine());

        Optional<Cargo> cargo = cargoRepository.findById(id);

        if(cargo.isPresent()){
            Cargo cargoUpdate = cargo.get();

            System.out.println("Informe o nome do cargo: ");

            cargoUpdate.setNomeDoCargo(scanner.nextLine());

            cargoRepository.save(cargoUpdate);
        }else{
            System.out.println("Cargo nao encontrado");
        }

    }

    private void visualizar(){
        List<Cargo> cargos = (List<Cargo>) cargoRepository.findAll();
        cargos.forEach(c -> System.out.println(c));
        cargos.stream().filter(c -> c.getId()>2).collect(Collectors.toList());
    }

    private void delete(){
        visualizar();

        System.out.println("Informe o id do cargo que deseja excluir: ");
        int id = Integer.parseInt(scanner.nextLine());

        cargoRepository.deleteById(id);
    }

    public Cargo getCargoById(Integer id){
       return cargoRepository.findById(id).get();
    }
}
