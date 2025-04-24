package application;

import java.sql.Connection;

import java.util.Scanner;

import db.DB;

import model.dao.ServicoDao;
import model.dao.impl.ClienteDaoJDBC;
import model.dao.impl.ClientePacoteServicoDaoJDBC;
import model.dao.impl.PacoteViagemDaoJDBC;
import model.dao.impl.ServicoDaoJDBC;

public class Program {

	public static void main(String[] args) {
	    
		Scanner sc = new Scanner(System.in);
	    Connection conn = DB.getConnection();

	    Program programa = new Program();

	    ClientePacoteServicoDaoJDBC clientePacoteServicoDAO = new ClientePacoteServicoDaoJDBC(conn);

	    int opcao = -1;
	    while (opcao != 0) {
	        System.out.println("\n--- MENU PRINCIPAL ---");
	        System.out.println("1. Gerenciar Clientes");
	        System.out.println("2. Gerenciar Pacotes de Viagem");
	        System.out.println("3. Gerenciar Serviços");
	        System.out.println("4. Cliente compra pacote");
	        System.out.println("5. Listar pacotes de um cliente");
	        System.out.println("6. Adicionar serviço a um pacote de um cliente");
	        System.out.println("7. Listar serviços em um pacote de um cliente");
	        System.out.println("0. Sair");
	        System.out.print("Escolha: ");
	        opcao = sc.nextInt();

	        switch (opcao) {
	            case 1 -> programa.gerenciarClientes();
	            case 2 -> programa.gerenciarPacotes(); 
	            case 3 -> programa.gerenciarServicos();

	            case 4 -> {
	                clientePacoteServicoDAO.adicionarPacoteParaCliente();
	            }

	            case 5 -> {
	            	clientePacoteServicoDAO.listarPacotesDoCliente();
	            }

	            case 6 -> {
	            	clientePacoteServicoDAO.adicionarServicoAoPacoteCliente(clientePacoteServicoDAO.listarPacotesDoCliente());
	            }

	            case 7 -> {
	                clientePacoteServicoDAO.listarServicosDoClienteNoPacote();
	            }

	            case 0 -> System.out.println("Saindo...");
	            default -> System.out.println("Opção inválida.");
	        }
	    }

	    DB.closeConnection();
	    sc.close();
	}
	
	public void gerenciarClientes() {
		
		Scanner sc = new Scanner(System.in);		
		Connection conn = DB.getConnection();
		
		ClienteDaoJDBC clienteDAO = new ClienteDaoJDBC(conn);
		
		int opt2 = -1;
    	
    	while (opt2 != 0) {
    		System.out.println("\n--- GERENCIANDO CLIENTES ---");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Atualizar cliente");
            System.out.println("3. Remover cliente");
            System.out.println("4. Buscar cliente");
            System.out.println("5. Listar clientes");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opt2 = sc.nextInt();
            
            switch(opt2) {
                case 1 -> {
                	clienteDAO.insert();
                }
                    
                case 2 -> {
                	clienteDAO.update();
                }
                
                case 3 -> {
                	clienteDAO.deleteById();
                }
                
                case 4 -> {
                	clienteDAO.findById();
                }
                
                case 5 -> {
                	clienteDAO.findAll();
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
    	}
	}
	
	public void gerenciarPacotes() {
		
		Scanner sc = new Scanner(System.in);		
		Connection conn = DB.getConnection();
		
		PacoteViagemDaoJDBC pacoteDAO = new PacoteViagemDaoJDBC(conn);
		
		int opt2 = -1;
    	
    	while (opt2 != 0) {
    		System.out.println("\n--- GERENCIANDO PACOTES DE VIAGEM ---");
            System.out.println("1. Cadastrar pacote");
            System.out.println("2. Atualizar pacote");
            System.out.println("3. Remover pacote");
            System.out.println("4. Buscar pacote");
            System.out.println("5. Listar pacotes");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opt2 = sc.nextInt();
            
            switch(opt2) {
            	case 1 -> {
            		pacoteDAO.insert();
            	}
            	case 2 -> {
            		pacoteDAO.update();
            	}
            	
            	case 3 -> {
                    pacoteDAO.deleteById();
            	}
            	
            	case 4 -> {
                    pacoteDAO.findById();
            	}
            	
            	case 5 -> {
            		pacoteDAO.findAll();
            	}
            	case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
    	}
	}
	
	public void gerenciarServicos() {
		Scanner sc = new Scanner(System.in);
        Connection conn = DB.getConnection(); 
		
        ServicoDao servicoDAO = new ServicoDaoJDBC(conn);
        
    	int opt2 = -1;
    	
    	while (opt2 != 0) {
    		System.out.println("\n--- GERENCIANDO SERVIÇOS ---");
            System.out.println("1. Cadastrar serviço");
            System.out.println("2. Atualizar serviço");
            System.out.println("3. Remover serviço");
            System.out.println("4. Buscar serviço");
            System.out.println("5. Listar serviços");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opt2 = sc.nextInt();
            
            switch(opt2) {
            	case 1 -> {
            		servicoDAO.insert();
            	}
            	
            	case 2 -> {
            		servicoDAO.update();
            	}
            	
            	case 3 -> {
            		servicoDAO.deleteById();
            	}
            	
            	case 4 -> {
            		servicoDAO.findById();
            	}
            	
            	case 5 -> {
            		servicoDAO.findAll();
            	}
            	case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
    	}
    }
}