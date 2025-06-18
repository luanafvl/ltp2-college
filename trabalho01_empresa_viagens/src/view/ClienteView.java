package view;

import java.util.Scanner;

import controller.ClienteController;
import model.entities.Cliente;

public class ClienteView {
	
	private Scanner sc;
	
	public ClienteView (Scanner sc) {
		this.sc = sc;
	}
	
	public void menuCliente(ClienteController clienteController) {
		
		int optMenuCliente = -1;
    	while (optMenuCliente != 0) {
    		System.out.println("\n--- GERENCIANDO CLIENTES ---");
            System.out.println("|1. Cadastrar cliente      |");
            System.out.println("|2. Atualizar cliente      |");
            System.out.println("|3. Remover cliente        |");
            System.out.println("|4. Buscar cliente         |");
            System.out.println("|5. Listar clientes        |");
            System.out.println("|0. Voltar                 |");
            System.out.println("----------------------------");
            System.out.println();
            System.out.print("Escolha: ");
            optMenuCliente = Integer.parseInt(sc.nextLine());

            // Chama os métodos do DAO de acordo com a opção
            switch(optMenuCliente) {
            	case 0 -> System.out.println("Voltando...");
                case 1 -> {
                	System.out.println("Informe os dados do cliente: ");
                	clienteController.insertCliente();
                }
                case 2 -> clienteController.updateCliente();
                case 3 -> clienteController.deleteClienteById();
                case 4 -> clienteController.findClienteById();
                case 5 -> clienteController.findAllClientes();
                default -> System.out.println("Opção inválida.");
            }
    	}
	}
	
	public Cliente coletaDadosCliente() {
		
		Cliente cliente = new Cliente();
		
		try {
			System.out.print("Nome: ");
			cliente.setNome(sc.nextLine());
		    System.out.print("Telefone: ");
		    cliente.setTelefone(sc.nextLine());
		    System.out.print("Email: ");
		    cliente.setEmail(sc.nextLine());
		    System.out.print("É brasileiro ou estrangeiro? (b/e): ");
		    String nacionalidade = sc.nextLine().toLowerCase();
		    
		    if (nacionalidade.equals("b")) {
		    	System.out.print("Informe seu CPF: ");
		    	cliente.setCpf(sc.nextLine());
		    	cliente.setIdTipo(1);
		    	
		    } else if (nacionalidade.equals("e")) {
		    	System.out.print("Informe seu passaporte: ");
		    	cliente.setPassaporte(sc.nextLine());
		    	cliente.setIdTipo(2);
		    }
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			cliente = null;
		}
		
        return cliente;
	}
	
	public Integer coletaIdCliente() {
		
		Integer id = 0;
		
		System.out.print("Informe id do cliente: ");
		
		try { 
			id = Integer.parseInt(sc.nextLine());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		return id;
	}
}
