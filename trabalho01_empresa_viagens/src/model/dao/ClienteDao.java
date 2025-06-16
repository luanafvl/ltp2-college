// Define o pacote onde está a interface
package model.dao;

import model.entities.Cliente;

// Interface que representa o contrato para operações de acesso a dados da entidade Cliente
public interface ClienteDao {

	/**
     	* Insere um novo cliente no banco de dados.
     	* @param pacote O objeto Cliente a ser inserido.
     	*/
	void insert(Cliente obj);

	/**
     	* Atualiza os dados de um cliente existente no banco.
     	* @param O id do usuário a ser atualizado e o objeto com os dados atualizados.
     	*/
	void update(Integer id, Cliente obj);

	/**
     	* Remove um cliente do banco com base no ID.
     	* @param id O ID do pacote que deve ser removido.
     	*/
	void deleteById(Integer id);

	/**
    	 * Busca um cliente pelo seu ID.
     	* @param id O ID do pacote que deve ser buscado.
     	* @return O objeto Cliente correspondente, ou null se não encontrado.
     	*/
	Cliente findById(Integer id);

	/**
    	 * Retorna todos os clientes cadastrados.
     	* @return Lista de Cliente.
     	*/
	void findAll();
}
