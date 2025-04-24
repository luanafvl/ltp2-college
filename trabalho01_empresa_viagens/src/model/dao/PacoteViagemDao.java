package model.dao;

import java.util.List;

import model.entities.PacoteViagem;

public interface PacoteViagemDao {

	void insert(PacoteViagem obj);
	void update(String nomePacote, PacoteViagem obj);
	void deleteByName(String nomePacote);
	PacoteViagem findByName(String nome);
	List<PacoteViagem> findAll();
	
}
