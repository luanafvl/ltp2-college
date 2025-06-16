package view;

// Importações necessárias para conexão com o banco e entrada de dados
import java.sql.Connection;
import java.util.Scanner;

import controller.ClienteController;
import db.DB; // Classe responsável por fornecer a conexão com o banco
import model.dao.ServicoDao;
import model.dao.impl.ClientePacoteServicoDaoJDBC;
import model.dao.impl.PacoteViagemDaoJDBC;
import model.dao.impl.ServicoDaoJDBC;

/*
 * Classe principal do menu de interações com o usuário
 * Responsável por chamar os métodos de gerenciamento de clientes, pacotes e serviços.
 */
public class Menu {
	
	// Método principal do menu
	public void menuPrincipal() {
	    
		Scanner sc = new Scanner(System.in); // Leitor de entrada
	    Connection conn = DB.getConnection(); // Abre conexão com o banco de dados
	    
	    ClienteView clienteView = new ClienteView(sc, conn);
	    
	    // Cria instância do DAO que lida com relacionamento cliente-pacote-serviço
	    ClientePacoteServicoDaoJDBC clientePacoteServicoDAO = new ClientePacoteServicoDaoJDBC(conn);

	    int optMenuPrincipal = -1; // Controle do menu
	    while (optMenuPrincipal != 0) {
	        // Exibição do menu principal
	        System.out.println("\n---------------- MENU PRINCIPAL ----------------");
	        System.out.println("|1. Gerenciar Clientes                         |");
	        System.out.println("|2. Gerenciar Pacotes de Viagem                |");
	        System.out.println("|3. Gerenciar Serviços                         |");
	        System.out.println("|4. Cliente compra pacote                      |");
	        System.out.println("|5. Listar pacotes de um cliente               |");
	        System.out.println("|6. Adicionar serviço a um pacote de um cliente|");
	        System.out.println("|7. Listar serviços em um pacote de um cliente |");
	        System.out.println("|0. Sair                                       |");
	        System.out.println("------------------------------------------------");
            System.out.println();
	        System.out.print("Escolha: ");
	        optMenuPrincipal = Integer.parseInt(sc.nextLine()); // Lê a opção do usuário

	        // Executa a ação conforme a escolha
	        switch (optMenuPrincipal) {
	        	case 0 -> System.out.print("Saindo...\n\nPrograma encerrado.");
	            case 1 -> clienteView.menuCliente();
	            case 2 -> menuP.gerenciarPacotes(sc, conn); 
	            case 3 -> menuP.gerenciarServicos(sc, conn);
	            case 4 -> clientePacoteServicoDAO.adicionarPacoteParaCliente();
	            case 5 -> clientePacoteServicoDAO.listarPacotesDoCliente();
	            case 6 -> clientePacoteServicoDAO.adicionarServicoAoPacoteCliente(clientePacoteServicoDAO.listarPacotesDoCliente());
	            case 7 -> clientePacoteServicoDAO.listarServicosDoClienteNoPacote();
	            default -> System.out.println("Opção inválida.");
	        }
	    }

	    // Fecha a conexão e o scanner
	    DB.closeConnection();
	    sc.close();
	}
	
	// Menu secundário para gerenciar clientes
	public void gerenciarClientes(Scanner sc, Connection conn, ClienteController clienteController) {

		ClienteView clienteView = new ClienteView(sc, conn); // View específico para cliente
		
		
	}
	
	// Menu para gerenciar pacotes de viagem
	public void gerenciarPacotes(Scanner sc, Connection conn) {

		PacoteViagemDaoJDBC pacoteDAO = new PacoteViagemDaoJDBC(conn); // DAO de pacotes

		int opt2 = -1;
    	while (opt2 != 0) {
    		System.out.println("\n--- GERENCIANDO PACOTES DE VIAGEM ---");
            System.out.println("|1. Cadastrar pacote       |");
            System.out.println("|2. Atualizar pacote       |");
            System.out.println("|3. Remover pacote         |");
            System.out.println("|4. Buscar pacote          |");
            System.out.println("|5. Listar pacotes         |");
            System.out.println("|0. Voltar                 |");
            System.out.println("----------------------------");
            System.out.println();
            System.out.print("Escolha: ");
            opt2 = Integer.parseInt(sc.nextLine());

            switch(opt2) {
            	case 0 -> System.out.println("Voltando...");
            	case 1 -> pacoteDAO.insert();
            	case 2 -> pacoteDAO.update();
            	case 3 -> pacoteDAO.deleteById();
            	case 4 -> pacoteDAO.findById();
            	case 5 -> pacoteDAO.findAll();
                default -> System.out.println("Opção inválida.");
            }
    	}
	}
	
	// Menu para gerenciar os serviços oferecidos
	public void gerenciarServicos(Scanner sc, Connection conn) {

        ServicoDao servicoDAO = new ServicoDaoJDBC(conn); // DAO para serviço
        
    	int opt2 = -1;
    	while (opt2 != 0) {
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
            opt2 = Integer.parseInt(sc.nextLine());

            switch(opt2) {
            	case 0 -> System.out.println("Voltando...");
            	case 1 -> servicoDAO.insert();
            	case 2 -> servicoDAO.update();
            	case 3 -> servicoDAO.deleteById();
            	case 4 -> servicoDAO.findById();
            	case 5 -> servicoDAO.findAll();
                default -> System.out.println("Opção inválida.");
            }
    	}
    }
}
