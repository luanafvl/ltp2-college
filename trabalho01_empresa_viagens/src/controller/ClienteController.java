package controller;

import java.sql.Connection;
import java.util.Scanner;

import model.dao.impl.ClienteDaoJDBC;
import model.entities.Cliente;
import view.ClienteView;

public class ClienteController {
	
	Scanner sc;
	Connection conn;
	
	ClienteView clienteView = new ClienteView(sc, conn);
	ClienteDaoJDBC clienteDao = new ClienteDaoJDBC(conn);
	Cliente cliente = new Cliente();
	Integer id;
	
	public ClienteController(Scanner sc, Connection conn) {
		this.sc = sc;
		this.conn = conn;
	}
	
	public void insertCliente() {
		cliente = clienteView.coletaDadosCliente();
		if (cliente != null) clienteDao.insert(cliente);
	}
	
	public void updateCliente() {
		clienteDao.findAll();
		id = clienteView.coletaIdCliente();
		if (id != 0) {
		System.out.println("Entre com os novos dados do cliente: ");
		cliente = clienteView.coletaDadosCliente();
			if (cliente != null) {
				clienteDao.update(cliente);
				System.out.println("Operação concluída com sucesso");
			}
		}
	}
	
	public void deleteClienteById() {
		clienteDao.findAll();
		id = clienteView.coletaIdCliente();
		if (id != 0) {
			clienteDao.deleteById(id);
			System.out.println("Operação concluída com sucesso.");
		}
	}
	
	public void findClienteById() {
		id = clienteView.coletaIdCliente();
		if (id != 0) {
			cliente = clienteDao.findById(id);
			System.out.println(cliente);
		}
	}
	
	public void findAllClientes() {
		clienteDao.findAll();
	}
}
