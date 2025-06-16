package view;

import java.sql.Connection;
import java.util.Scanner;

import controller.ClienteController;
import enums.TipoCliente;
import model.entities.Cliente;

public class ClienteView {
	
	Scanner sc;
	Connection conn;
	
	ClienteController clienteController = new ClienteController(sc, conn);
	
	public ClienteView (Scanner sc, Connection conn) {
		this.sc = sc;
		this.conn = conn;
	}
	
	public void menuCliente() {
		
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
                case 1 -> clienteController.insertCliente();
                case 2 -> clienteController.updateCliente();
                case 3 -> clienteController.deleteClienteById();
                case 4 -> clienteController.findClienteById();
                case 5 -> clienteController.findAllClientes();
                default -> System.out.println("Opção inválida.");
            }
    	}
	}
	
	public Cliente coletaDadosCliente() {
		System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("É brasileiro ou estrangeiro? (b/e): ");
        String nacionalidade = sc.nextLine().toLowerCase();
        
        Integer tipo = 0;
        String cpf = null;
        String passaporte = null;
        
        
        if (nacionalidade.equals("b")) {
        	System.out.print("Informe seu CPF: ");
        	cpf = sc.nextLine();
        	tipo = TipoCliente.NACIONAL.ordinal();
        	
        } else if (nacionalidade.equals("e")) {
        	System.out.print("Informe seu passaporte: ");
        	passaporte = sc.nextLine();
        	tipo = TipoCliente.ESTRANGEIRO.ordinal();
        }
        
        Cliente cliente = new Cliente(nome, telefone, email, tipo, cpf, passaporte);
        
        return cliente;
	}
	
	public Integer coletaIdCliente() {
		
		System.out.print("Informe id do cliente: ");
    	Integer id = Integer.parseInt(sc.nextLine());
		
		return id;
	}
}
