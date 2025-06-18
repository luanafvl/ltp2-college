package controller;

import java.sql.Connection;
import java.util.Scanner;

import model.dao.impl.ServicoDaoJDBC;
import model.entities.Servico;
import view.ServicoView;

public class ServicoController {

	private ServicoView servicoView;
	private ServicoDaoJDBC servicoDao;
	
	private ServicoController servicoController;
	
	public ServicoController(Scanner sc, Connection conn, ServicoView servicoView) {
		this.servicoView = servicoView;
		this.servicoDao = new ServicoDaoJDBC(conn);
	}
	
	public void insertServico() {
		Servico servico = servicoView.coletaDadosServico(servicoController);
		if (servico != null) servicoDao.insert(servico);
	}
	
	public void updateServico() {
		servicoDao.findAll();
		Integer id = servicoView.coletaIdServico();
		if (id != 0) {
		System.out.println("Entre com os novos dados do servico: ");
		Servico servico = servicoView.coletaDadosServico(servicoController);
			if (servico != null) {
				servicoDao.update(servico);
				System.out.println("Operação concluída com sucesso");
			}
		}
	}
	
	public void deleteServicoById() {
		servicoDao.findAll();
		Integer id = servicoView.coletaIdServico();
		if (id != 0) {
			servicoDao.deleteById(id);
			System.out.println("Operação concluída com sucesso.");
		}
	}
	
	public void findServicoById() {
		Integer id = servicoView.coletaIdServico();
		if (id != 0) {
			Servico servico = servicoDao.findById(id);
			System.out.println(servico);
		}
	}
	
	public void findAllServicos() {
		servicoDao.findAll();
	}
}