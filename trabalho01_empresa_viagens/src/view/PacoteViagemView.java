package view;

import java.sql.Connection;
import java.util.Scanner;

import controller.PacoteViagemController;
import model.entities.PacoteViagem;

public class PacoteViagemView {

	Scanner sc;
	Connection conn;
	
	PacoteViagemController pacoteViagemController = new PacoteViagemController(sc, conn);
	
	public PacoteViagemView(Scanner sc, Connection conn) {
		this.sc = sc;
		this.conn = conn;
	}
	
	public void menuPacoteViagem() {

		int optMenuPacoteViagem = -1;
    	while (optMenuPacoteViagem != 0) {
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
            optMenuPacoteViagem = Integer.parseInt(sc.nextLine());

            switch(optMenuPacoteViagem) {
            	case 0 -> System.out.println("Voltando...");
            	case 1 -> pacoteViagemController.insertPacoteViagem();
            	case 2 -> pacoteViagemController.updatePacoteViagem();
            	case 3 -> pacoteViagemController.deletePacoteViagemById();
            	case 4 -> pacoteViagemController.findPacoteViagemById();
            	case 5 -> pacoteViagemController.findAllPacoteViagens();
                default -> System.out.println("Opção inválida.");
            }
    	}
	}
	
	public PacoteViagem coletaDadosPacoteViagem() {
		
		PacoteViagem pacoteViagem = new PacoteViagem();
		
		try {
			System.out.print("Nome do pacote: ");
            pacoteViagem.setNome(sc.nextLine());
            System.out.print("Descrição: ");
            pacoteViagem.setDescricao(sc.nextLine());;
            System.out.print("Preço: ");
            pacoteViagem.setPreco(Double.parseDouble(sc.nextLine()));
            System.out.print("Duração (dias): ");
            pacoteViagem.setDuracao(Integer.parseInt(sc.nextLine()));
            pacoteViagemController.findAllDestinos();
            System.out.print("Destino: ");
            pacoteViagem.setIdDestino(Integer.parseInt(sc.nextLine()));
            pacoteViagemController.findAllTipos();
            System.out.print("Tipo de pacote: ");
            pacoteViagem.setIdTipo(Integer.parseInt(sc.nextLine()));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			pacoteViagem = null;
		}
		
		return pacoteViagem;
	}
	
	public Integer coletaIdPacoteViagem() {
		
		Integer id = 0;
		
		System.out.print("Informe id do pacote: ");
		
		try { 
			id = Integer.parseInt(sc.nextLine());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		return id;
	}
}
