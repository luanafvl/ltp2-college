package model.dao;

import java.util.List;

import model.entities.Servico;

public interface ServicoDao {

	void insert(Servico obj);
	void update(String nomeServico, Servico obj);
	void deleteByName(String nomeServico);
	Servico findByName(String nomeServico);
	List<Servico> findAll();
}
