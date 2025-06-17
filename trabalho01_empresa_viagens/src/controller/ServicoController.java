package controller;

import java.sql.Connection;
import java.util.Scanner;

import model.dao.impl.ServicoDaoJDBC;
import model.entities.Servico;
import view.ServicoView;

public class ServicoController {

	Scanner sc;
	Connection conn;
	
	ServicoView servicoView = new ServicoView(sc, conn);
	ServicoDaoJDBC servicoDao = new ServicoDaoJDBC(conn);
	Servico servico = new Servico();
	Integer id;
	
	public ServicoController(Scanner sc, Connection conn) {
		this.sc = sc;
		this.conn = conn;
	}
	
	public void insertServico() {
		servico = servicoView.coletaDadosServico();
		if (servico != null) servicoDao.insert(servico);
	}
	
	public void updateServico() {
		servicoDao.findAll();
		id = servicoView.coletaIdServico();
		if (id != 0) {
		System.out.println("Entre com os novos dados do servico: ");
		servico = servicoView.coletaDadosServico();
			if (servico != null) {
				servicoDao.update(servico);
				System.out.println("Operação concluída com sucesso");
			}
		}
	}
	
	public void deleteServicoById() {
		servicoDao.findAll();
		id = servicoView.coletaIdServico();
		if (id != 0) {
			servicoDao.deleteById(id);
			System.out.println("Operação concluída com sucesso.");
		}
	}
	
	public void findServicoById() {
		id = servicoView.coletaIdServico();
		if (id != 0) {
			servico = servicoDao.findById(id);
			System.out.println(servico);
		}
	}
	
	public void findAllServicos() {
		servicoDao.findAll();
	}
}
