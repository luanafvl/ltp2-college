package controller;

import java.sql.Connection;
import java.util.Scanner;

import model.dao.impl.PacoteViagemDaoJDBC;
import model.entities.PacoteViagem;
import view.PacoteViagemView;

public class PacoteViagemController {
	
	PacoteViagemController pacoteViagemController;

	private PacoteViagemView pacoteViagemView;
	private PacoteViagemDaoJDBC pacoteViagemDao;
	
	public PacoteViagemController(Scanner sc, Connection conn, PacoteViagemView pacoteViagemView) {
		this.pacoteViagemView = pacoteViagemView;
		this.pacoteViagemDao = new PacoteViagemDaoJDBC(conn);
	}
	
	public void insertPacoteViagem() {
		PacoteViagem pacoteViagem = pacoteViagemView.coletaDadosPacoteViagem(pacoteViagemController);
		if (pacoteViagem != null) pacoteViagemDao.insert(pacoteViagem);
	}
	
	public void updatePacoteViagem() {
		pacoteViagemDao.findAll();
		Integer id = pacoteViagemView.coletaIdPacoteViagem();
		if (id != 0) {
		System.out.println("Entre com os novos dados do pacoteViagem: ");
		PacoteViagem pacoteViagem = pacoteViagemView.coletaDadosPacoteViagem(pacoteViagemController);
			if (pacoteViagem != null) {
				pacoteViagemDao.update(pacoteViagem);
				System.out.println("Operação concluída com sucesso");
			}
		}
	}
	
	public void deletePacoteViagemById() {
		pacoteViagemDao.findAll();
		Integer id = pacoteViagemView.coletaIdPacoteViagem();
		if (id != 0) {
			pacoteViagemDao.deleteById(id);
			System.out.println("Operação concluída com sucesso.");
		}
	}
	
	public void findPacoteViagemById() {
		Integer id = pacoteViagemView.coletaIdPacoteViagem();
		if (id != 0) {
			PacoteViagem pacoteViagem = pacoteViagemDao.findById(id);
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