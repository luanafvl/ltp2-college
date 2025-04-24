package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import enums.TipoCliente;
import model.dao.ClienteDao;
import model.entities.Cliente;

public class ClienteDaoJDBC implements ClienteDao {

	private Connection conn;
	
	public ClienteDaoJDBC (Connection conn) {
		this.conn = conn;
		
	}
	
	@Override
	public void insert(Cliente cliente) {
		
		PreparedStatement st = null;
		
		if (conn != null) {
			try {
				
				st = conn.prepareStatement("INSERT INTO tb_cliente "
						+ "(nome, telefone, email, cpf, passaporte, id_tipo_cliente) "
						+ "VALUES (?, ?, ?, ?, ?, ?);"
						);
				
				st.setString(1, cliente.getNome());
				st.setString(2, cliente.getTelefone());
				st.setString(3, cliente.getEmail());
				st.setString(4, cliente.getCpf());
				st.setString(5, cliente.getPassaporte());
				if(cliente.getTipo() == TipoCliente.NACIONAL) {
					st.setInt(6, 1);
				} else if (cliente.getTipo() == TipoCliente.ESTRANGEIRO) {
					st.setInt(6, 2);
				}
			
				st.executeUpdate();
				
				System.out.println("Cliente cadastrado!");
				
			}
			catch (SQLException e) {
				System.out.println("Erro ao criar cliente: " + e.getMessage());
			}
			finally {
				try {
	            	if (st != null) st.close();
				}
				catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		    }
		}
	}

	
	@Override
	public void update(String documento, Cliente cliente) {
		
		PreparedStatement st = null;
		
		if (conn != null) {
			try {
				st = conn.prepareStatement(
						"UPDATE tb_cliente "
						+ "SET nome = ?, telefone = ?, email = ?, cpf = ?, passaporte = ?, id_tipo_cliente = ? "
						+ "WHERE cpf = ? or passaporte = ?"
						);
				
				st.setString(1, cliente.getNome());
				st.setString(2, cliente.getTelefone());
				st.setString(3, cliente.getEmail());
				st.setString(4, cliente.getCpf());
				st.setString(5, cliente.getPassaporte());
				if(cliente.getTipo() == TipoCliente.NACIONAL) {
					st.setInt(6, 1);
				} else if (cliente.getTipo() == TipoCliente.ESTRANGEIRO) {
					st.setInt(6, 2);
				}
				st.setString(7, documento);
				st.setString(8, documento);
			
				st.executeUpdate();
				
				System.out.println("Cliente atualizado com sucesso");
			}
			catch (SQLException e) {
				System.out.println("Erro ao atualizar cliente: " + e.getMessage());
			}
			finally {	
				try {
					
	            	if (st != null) st.close();
				}
				catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		    }
		}
	}
	

	@Override
	public void deleteByDocument(String documento) {
		PreparedStatement st = null;
		if(conn != null) {
			try {
				st = conn.prepareStatement(
						"DELETE FROM tb_cliente "
						+ "WHERE cpf = ? or passaporte = ?"
						);
				
				st.setString(1, documento);
				st.setString(2, documento);
				
				st.executeUpdate();

				
				System.out.println("Cliente removido com sucesso.");
			}
			catch (SQLException e) {
				System.out.println("Erro ao remover cliente: " + e.getMessage());
			}
			finally {	
				try {
	            	if (st != null) st.close();
				}
				catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		    }
		}
	}

	
	@Override
	public Cliente findByDocument(String documento) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		if(conn != null) {
			try {
				st = conn.prepareStatement(
						"SELECT "
						+ "c.id_cliente as id, "
						+ "c.nome as nome, "
						+ "c.telefone, "
						+ "c.email, "
						+ "c.cpf, "
						+ "c.passaporte, "
						+ "tc.tipo_cliente as tipo "
						+ "FROM tb_cliente c "
						+ "INNER JOIN tb_tipo_cliente tc "
						+ "ON c.id_tipo_cliente = tc.id_tipo_cliente "
						+ "WHERE c.cpf = ? or c.passaporte = ?"
						);
				
				st.setString(1, documento);
				st.setString(2, documento);

				rs = st.executeQuery();
				
				if (rs.next()) {	
					Cliente cli = new Cliente();
					cli.setId(rs.getInt("id"));
					cli.setNome(rs.getString("nome"));
					cli.setTelefone(rs.getString("telefone"));
					cli.setEmail(rs.getString("email"));
					cli.setTipo(TipoCliente.valueOf(rs.getString("tipo")));
					cli.setCpf(rs.getString("cpf"));
					cli.setPassaporte(rs.getString("passaporte"));
					return cli;					
				}
			}
			catch (SQLException e) {
				System.out.println("Erro ao encontrar cliente: " + e.getMessage());
			}
			finally {	
				try {
					if (rs != null) rs.close();
	            	if (st != null) st.close();
				}
				catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		    }
		}
		return null;
	}

	
	@Override
	public List<Cliente> findAll() {
		
		List<Cliente> clientes = new ArrayList<>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		if(conn!=null) {
			
			try {
				
				st = conn.prepareStatement(
						"SELECT c.id_cliente, "
						+ "c.nome, "
						+ "c.telefone, "
						+ "c.email,"
						+ "c.cpf, "
						+ "c.passaporte, "
						+ "tc.tipo_cliente "
						+ "FROM tb_cliente c "
						+ "INNER JOIN tb_tipo_cliente tc "
						+ "ON c.id_tipo_cliente = tc.id_tipo_cliente"
						);
				
				rs = st.executeQuery();
				
				while(rs.next()) {
					Cliente cli = new Cliente();
		            cli.setId(rs.getInt("id_cliente"));
		            cli.setNome(rs.getString("nome"));
		            cli.setTelefone(rs.getString("telefone"));
		            cli.setEmail(rs.getString("email"));
		            cli.setCpf(rs.getString("cpf"));
		            cli.setPassaporte(rs.getString("passaporte"));
		            String tipo = rs.getString("tipo_cliente");
		            cli.setTipo(TipoCliente.valueOf(tipo));
		            
		            clientes.add(cli);
				}
			}
			catch (SQLException e) {
				System.out.println("Erro ao listar clientes: " + e.getMessage());
			}
		}
		return clientes;
	}
}