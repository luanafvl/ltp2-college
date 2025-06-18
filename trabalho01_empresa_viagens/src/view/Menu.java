package view;

// Importações necessárias para conexão com o banco e entrada de dados
import java.sql.Connection;
import java.util.Scanner;

import controller.ClienteController;
import controller.PacoteViagemController;
import controller.ServicoController;
import db.DB; // Classe responsável por fornecer a conexão com o banco
import model.dao.impl.ClientePacoteServicoDaoJDBC;

/*
 * Classe principal do menu de interações com o usuário
 * Responsável por chamar os métodos de gerenciamento de clientes, pacotes e serviços.
 */
public class Menu {
	
	// Método principal do menu
	public void menuPrincipal() {
	    
		Scanner sc = new Scanner(System.in); // Leitor de entrada
	    Connection conn = DB.getConnection(); // Abre conexão com o banco de dados
	    
	    ClienteView clienteView = new ClienteView(sc);
	    PacoteViagemView pacoteViagemView = new PacoteViagemView(sc);
	    ServicoView servicoView = new ServicoView(sc);
	    
	    ClienteController clienteController = new ClienteController(sc, conn, clienteView);
	    PacoteViagemController pacoteViagemController = new PacoteViagemController(sc, conn, pacoteViagemView);
	    ServicoController servicoController = new ServicoController(sc, conn, servicoView);
	    
	    
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
	            case 1 -> clienteView.menuCliente(clienteController);
	            case 2 -> pacoteViagemView.menuPacoteViagem(pacoteViagemController); 
	            case 3 -> servicoView.menuServico(servicoController);
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
}
