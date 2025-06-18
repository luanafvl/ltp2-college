package controller;

import java.sql.Connection;
import java.util.Scanner;

import model.dao.impl.ClienteDaoJDBC;
import model.entities.Cliente;
import view.ClienteView;

public class ClienteController {

	private ClienteView clienteView;
    private ClienteDaoJDBC clienteDao;
	
	public ClienteController(Scanner sc, Connection conn, ClienteView clienteView) {
		this.clienteView = clienteView;
		this.clienteDao = new ClienteDaoJDBC(conn);
	}
	
	public void insertCliente() {
		Cliente cliente = clienteView.coletaDadosCliente();
		if (cliente != null) clienteDao.insert(cliente);
	}
	
	public void updateCliente() {
		clienteDao.findAll();
		Integer id = clienteView.coletaIdCliente();
		if (id != 0) {
		System.out.println("Entre com os novos dados do cliente: ");
		Cliente cliente = clienteView.coletaDadosCliente();
			if (cliente != null) {
				clienteDao.update(cliente);
				System.out.println("Operação concluída com sucesso");
			}
		}
	}
	
	public void deleteClienteById() {
		clienteDao.findAll();
		Integer id = clienteView.coletaIdCliente();
		if (id != 0) {
			clienteDao.deleteById(id);
			System.out.println("Operação concluída com sucesso.");
		}
	}
	
	public void findClienteById() {
		Integer id = clienteView.coletaIdCliente();
		if (id != 0) {
			Cliente cliente = clienteDao.findById(id);
			if (cliente != null) System.out.println(cliente);
			else System.out.println("Cliente não encontrado.");
		}
	}
	
	public void findAllClientes() {
		clienteDao.findAll();
	}
}