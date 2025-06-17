package controller;

import java.sql.Connection;
import java.util.Scanner;

import model.dao.impl.PacoteViagemDaoJDBC;
import model.entities.PacoteViagem;
import view.PacoteViagemView;

public class PacoteViagemController {
	
	Scanner sc;
	Connection conn;
	
	PacoteViagemView pacoteViagemView = new PacoteViagemView(sc, conn);
	PacoteViagemDaoJDBC pacoteViagemDao = new PacoteViagemDaoJDBC(conn);
	PacoteViagem pacoteViagem = new PacoteViagem();
	Integer id;
	
	public PacoteViagemController(Scanner sc, Connection conn) {
		this.sc = sc;
		this.conn = conn;
	}
	
	public void insertPacoteViagem() {
		pacoteViagem = pacoteViagemView.coletaDadosPacoteViagem();
		if (pacoteViagem != null) pacoteViagemDao.insert(pacoteViagem);
	}
	
	public void updatePacoteViagem() {
		pacoteViagemDao.findAll();
		id = pacoteViagemView.coletaIdPacoteViagem();
		if (id != 0) {
		System.out.println("Entre com os novos dados do pacoteViagem: ");
		pacoteViagem = pacoteViagemView.coletaDadosPacoteViagem();
			if (pacoteViagem != null) {
				pacoteViagemDao.update(pacoteViagem);
				System.out.println("Operação concluída com sucesso");
			}
		}
	}
	
	public void deletePacoteViagemById() {
		pacoteViagemDao.findAll();
		id = pacoteViagemView.coletaIdPacoteViagem();
		if (id != 0) {
			pacoteViagemDao.deleteById(id);
			System.out.println("Operação concluída com sucesso.");
		}
	}
	
	public void findPacoteViagemById() {
		id = pacoteViagemView.coletaIdPacoteViagem();
		if (id != 0) {
			pacoteViagem = pacoteViagemDao.findById(id);
			System.out.println(pacoteViagem);
		}
	}
	
	public void findAllPacoteViagens() {
		pacoteViagemDao.findAll();
	}
	
	public void findAllDestinos() {
		pacoteViagemDao.findAllDestinos();
	}
	
	public void findAllTipos() {
		pacoteViagemDao.findAllTipos();
	}
}
