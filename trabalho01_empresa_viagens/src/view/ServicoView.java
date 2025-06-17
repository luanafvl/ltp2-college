package view;

import java.sql.Connection;
import java.util.Scanner;

import controller.ServicoController;
import model.entities.Servico;

public class ServicoView {

	Scanner sc;
	Connection conn;
	
	ServicoController servicoController = new ServicoController(sc, conn);
	
	public ServicoView(Scanner sc, Connection conn) {
		this.sc = sc;
		this.conn = conn;
	}
	
	public void menuServico() {
		int optMenuServico = -1;
    	while (optMenuServico != 0) {
    		System.out.println("\n--- GERENCIANDO SERVIÇOS ---");
            System.out.println("|1. Cadastrar serviço      |");
            System.out.println("|2. Atualizar serviço      |");
            System.out.println("|3. Remover serviço        |");
            System.out.println("|4. Buscar serviço         |");
            System.out.println("|5. Listar serviços        |");
            System.out.println("|0. Voltar                 |");
            System.out.println("----------------------------");
            System.out.println();
            System.out.print("Escolha: ");
            optMenuServico = Integer.parseInt(sc.nextLine());

            switch(optMenuServico) {
            	case 0 -> System.out.println("Voltando...");
            	case 1 -> servicoController.insertServico();
            	case 2 -> servicoController.updateServico();
            	case 3 -> servicoController.deleteServicoById();
            	case 4 -> servicoController.findServicoById();
            	case 5 -> servicoController.findAllServicos();
                default -> System.out.println("Opção inválida.");
            }
    	}
	}
	
	public Servico coletaDadosServico() {
		
		Servico servico = new Servico();
		
		try {
			System.out.print("Nome do serviço: ");
	        String nome = sc.nextLine();
	        System.out.print("Preço: ");
	        Double preco = Double.parseDouble(sc.nextLine());
	        sc.nextLine();
	        System.out.print("Descrição: ");
	        String descricao = sc.nextLine();
	        
	        servico.setNome(nome);
	        servico.setPreco(preco);
	        servico.setDescricao(descricao);
	        
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			servico = null;
		}
		return servico;
	}
	
	public Integer coletaIdServico() {
		
		Integer id = 0;
		
		try {
			System.out.print("Informe o id do serviço: ");
			id = Integer.parseInt(sc.nextLine());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		return id;
	}
}
