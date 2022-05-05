package br.com.matheus.springdata;

import br.com.matheus.springdata.service.CrudCargoService;
import br.com.matheus.springdata.service.CrudFuncionarioService;
import br.com.matheus.springdata.service.RelatorioFuncionarioDinamico;
import br.com.matheus.springdata.service.RelatorioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringdataApplication implements CommandLineRunner {
	private final CrudCargoService crudCargoService;
	private final CrudFuncionarioService crudFuncionarioService;
	private final RelatorioService relatorioService;
	private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;

	private boolean continuar = true;

	public SpringdataApplication(CrudCargoService crudCargoService, CrudFuncionarioService crudFuncionarioService, RelatorioService relatorioService, RelatorioFuncionarioDinamico relatorioFuncionarioDinamico){
		this.crudCargoService = crudCargoService;
		this.crudFuncionarioService = crudFuncionarioService;
		this.relatorioService = relatorioService;
		this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringdataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while(continuar){
			System.out.println("Informe a sua opcao");
			System.out.println("0- sair");
			System.out.println("1- cargo");
			System.out.println("2- funcionario");
			System.out.println("3- relatorio");
			System.out.println("4- specifation");

			int opcao = Integer.parseInt(scanner.nextLine());

			switch (opcao){
				case 1:{
					crudCargoService.iniciar(scanner);
					break;
				}
				case 2:{
					crudFuncionarioService.iniciar(scanner);
					break;
				}case 3:{
					relatorioService.iniciar(scanner);
					break;
				}case 4:{
					relatorioFuncionarioDinamico.iniciar(scanner);
					break;
				}

				default:{
					continuar = false;
				}
			}



		}


	}
}
