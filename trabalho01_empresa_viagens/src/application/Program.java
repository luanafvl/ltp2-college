package application;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import db.DB;
import enums.TipoCliente;
import model.dao.ServicoDao;
import model.dao.impl.ClienteDaoJDBC;
import model.dao.impl.ClientePacoteServicoDaoJDBC;
import model.dao.impl.PacoteViagemDaoJDBC;
import model.dao.impl.ServicoDaoJDBC;
import model.entities.Cliente;
import model.entities.PacoteViagem;
import model.entities.Servico;

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
	                System.out.print("Nome do cliente: ");
	                sc.next();
	                String nomeCliente = sc.nextLine();
	                System.out.print("Nome do pacote: ");
	                String nomePacote = sc.nextLine();
	                clientePacoteServicoDAO.adicionarPacoteParaCliente(nomeCliente, nomePacote);
	                System.out.println("Compra registrada!");
	            }

	            case 5 -> {
	                System.out.print("Nome do cliente: ");
	                String nomeCliente = sc.nextLine();
	                List<PacoteViagem> pacotes = clientePacoteServicoDAO.listarPacotesDoCliente(nomeCliente);
	                pacotes.forEach(System.out::println);
	            }

	            case 6 -> {
	                System.out.print("Nome do cliente: ");
	                String nomeCliente = sc.nextLine();
	                System.out.print("Nome do pacote: ");
	                String nomePacote = sc.nextLine();
	                System.out.print("Nome do serviço: ");
	                String nomeServico = sc.nextLine();
	                clientePacoteServicoDAO.adicionarServicoAoPacoteCliente(nomeCliente, nomePacote, nomeServico);
	                System.out.println("Serviço adicionado ao pacote!");
	            }

	            case 7 -> {
	                System.out.print("Nome do cliente: ");
	                String nomeCliente = sc.nextLine();
	                System.out.print("Nome do pacote: ");
	                String nomePacote = sc.nextLine();
	                List<Servico> servicos = clientePacoteServicoDAO.listarServicosDoClienteNoPacote(nomeCliente, nomePacote);
	                servicos.forEach(System.out::println);
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
                	System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("É brasileiro ou estrangeiro? (b/e): ");
                    String nacionalidade = sc.nextLine();
                    
                    nacionalidade = nacionalidade.toLowerCase();
                    
                    if (nacionalidade.equals("b")) {
                    	
                    	System.out.print("Informe seu CPF: ");
                    	String cpf = sc.nextLine();
                    	Cliente c = new Cliente(nome, telefone, email, TipoCliente.NACIONAL, cpf, null);
                    	clienteDAO.insert(c);
                    } else if (nacionalidade.equals("e")) {
                    	
                    	System.out.print("Informe seu passaporte: ");
                    	String passaporte = sc.nextLine();
                    	Cliente c = new Cliente(nome, telefone, email, TipoCliente.NACIONAL, null, passaporte);
                    	clienteDAO.insert(c);
                    }
                }
                    
                case 2 -> {
                	
                	System.out.print("Informe o número do documento para a atualização do cliente: ");
                	String documento = sc.nextLine();
                	
                	System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("É brasileiro ou estrangeiro? (b/e): ");
                    String nacionalidade = sc.nextLine();
                    
                    nacionalidade = nacionalidade.toLowerCase();
                    
                    if (nacionalidade.equals("b")) {
                    	
                    	System.out.print("Informe seu CPF: ");
                    	String cpf = sc.nextLine();
                    	Cliente c = new Cliente(nome, telefone, email, TipoCliente.NACIONAL, cpf, null);
                    	clienteDAO.update(documento, c);
                    } else if (nacionalidade.equals("e")) {
                    	
                    	System.out.print("Informe seu passaporte: ");
                    	String passaporte = sc.nextLine();
                    	Cliente c = new Cliente(nome, telefone, email, TipoCliente.NACIONAL, null, passaporte);
                    	clienteDAO.update(documento, c);
                    }
                }
                
                case 3 -> {
                	System.out.print("Informe o número do documento: ");
                	String documento = sc.nextLine();
                	
                	clienteDAO.deleteByDocument(documento);
                }
                
                case 4 -> {
                	System.out.print("Informe o número do documento: ");
                	String documento = sc.nextLine();
                	
                	Cliente cli = clienteDAO.findByDocument(documento);
                	System.out.println(cli);
                }
                
                case 5 -> {
                	List<Cliente> clientes = clienteDAO.findAll();
                    for(Cliente c : clientes) {
                    	System.out.println(c);
                    }
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
            		System.out.print("Nome do pacote: ");
                    String nome = sc.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = sc.nextLine();
                    System.out.print("Preço: ");
                    double preco = sc.nextDouble();
                    System.out.print("Duração (dias): ");
                    int duracao = sc.nextInt();
                    System.out.print("Destino: ");
                    int destino = sc.nextInt();
                    System.out.print("Tipo de pacote: ");
                    int tipo = sc.nextInt();
                    sc.nextLine();
                    PacoteViagem p = new PacoteViagem(nome, preco, descricao, duracao, destino, tipo);
                    pacoteDAO.insert(p);
            	}
            	case 2 -> {
            		
            		System.out.println("Informe o nome do pacote a ser atualizado: ");
            		String nomePacote = sc.nextLine();
            		
            		System.out.print("Nome do pacote: ");
                    String nome = sc.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = sc.nextLine();
                    System.out.print("Preço: ");
                    double preco = sc.nextDouble();
                    System.out.print("Duração (dias): ");
                    int duracao = sc.nextInt();
                    System.out.print("Destino: ");
                    int destino = sc.nextInt();
                    System.out.print("Tipo de pacote: ");
                    int tipo = sc.nextInt();
                    sc.nextLine();
                    PacoteViagem p = new PacoteViagem(nome, preco, descricao, duracao, destino, tipo);
                    pacoteDAO.update(nomePacote, p);
            	}
            	
            	case 3 -> {
            		System.out.print("Nome do pacote: ");
                    String nomePacote = sc.nextLine();
                    
                    pacoteDAO.deleteByName(nomePacote);
            	}
            	
            	case 4 -> {
            		System.out.print("Nome do pacote: ");
                    String nomePacote = sc.nextLine();
                    
                    pacoteDAO.findByName(nomePacote);
            	}
            	
            	case 5 -> {
            		List<PacoteViagem> pacotes = pacoteDAO.findAll();
                    for(PacoteViagem pv : pacotes) {
                    	System.out.println(pv);
                    }
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
            		System.out.print("Nome do serviço: ");
            		sc.nextLine();
                    String nome = sc.nextLine();
                    System.out.print("Preço: ");
                    double preco = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = sc.nextLine();                   
                    Servico s = new Servico (nome, preco, descricao);
                    servicoDAO.insert(s);
            	}
            	
            	case 2 -> {
            		
            		System.out.println("Informe o nome do serviço a ser atualizado: ");
            		String nomeServico = sc.nextLine();
            		
            		System.out.print("Nome do serviço: ");
                    String nome = sc.nextLine();
                    System.out.print("Preço: ");
                    double preco = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = sc.nextLine();
                    sc.next();
                    Servico s = new Servico (nome, preco, descricao);
                    servicoDAO.update(nomeServico, s);
            	}
            	
            	case 3 -> {
            		System.out.println("Informe o nome do serviço: ");
            		String nomeServico = sc.nextLine();
            		
            		servicoDAO.deleteByName(nomeServico);
            	}
            	
            	case 4 -> {
            		System.out.println("Informe o nome do serviço: ");
            		String nomeServico = sc.nextLine();
            		
            		servicoDAO.findByName(nomeServico);
            	}
            	
            	case 5 -> {
            		List<Servico> servicos = servicoDAO.findAll();
                    for(Servico s: servicos) {
                    	System.out.println(s);
                    }
            	}
            	case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
    	}
    }
}
