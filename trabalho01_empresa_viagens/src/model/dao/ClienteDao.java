package model.dao;

import java.util.List;

import model.entities.Cliente;

public interface ClienteDao {

	void insert(Cliente obj);
	void update(String documento, Cliente obj);
	void deleteByDocument(String documento);
	Cliente findByDocument(String documento);
	List<Cliente> findAll();
}
