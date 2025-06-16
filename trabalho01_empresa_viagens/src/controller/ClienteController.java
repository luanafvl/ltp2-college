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
		System.out.println("Informe os dados do cliente: ");
		cliente = clienteView.coletaDadosCliente();
		clienteDao.insert(cliente);
		System.out.println("Cliente cadastrado!");
	}
	
	public void updateCliente() {
		clienteDao.findAll();
		id = clienteView.coletaIdCliente();
		System.out.println("Entre com os novos dados do cliente: ");
		cliente = clienteView.coletaDadosCliente();
		clienteDao.update(id, cliente);
		System.out.println("Cliente atualizado com sucesso");
	}
	
	public void deleteClienteById() {
		clienteDao.findAll();
		id = clienteView.coletaIdCliente();
		clienteDao.deleteById(id);
		System.out.println("Cliente removido com sucesso.");
	}
	
	public void findClienteById() {
		id = clienteView.coletaIdCliente();
		cliente = clienteDao.findById(id);
		System.out.println(cliente);
	}
	
	public void findAllClientes() {
		clienteDao.findAll();
	}
}
