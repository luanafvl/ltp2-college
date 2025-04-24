package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.ServicoDao;
import model.entities.Servico;

public class ServicoDaoJDBC implements ServicoDao {

private Connection conn;
	
	public ServicoDaoJDBC (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Servico s) {
		
		PreparedStatement st = null;
		
		if (conn != null) {
			try {
				
				st = conn.prepareStatement("INSERT INTO tb_servico "
						+ "(servico, preco, descricao) "
						+ "VALUES (?, ?, ?)"
						);
				
				st.setString(1, s.getNome());
				st.setDouble(2, s.getPreco());
				st.setString(3, s.getDescricao());
				
				st.executeUpdate();
				
				System.out.println("Serviço adicionado com sucesso.");
				
			}
			catch (SQLException e) {
				System.out.println("Erro ao adicionar serviço: " + e.getMessage());
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
	public void update(String nomeServico, Servico s) {
		
		PreparedStatement st = null;
		
		if (conn != null) {
			try {
				st = conn.prepareStatement(
						"UPDATE tb_cliente "
						+ "SET servico = ?, preco = ?, descricao = ? "
						+ "WHERE servico = ?"
						);
				
				st.setString(1, s.getNome());
				st.setDouble(2, s.getPreco());
				st.setString(3, s.getDescricao());
				st.setString(4, nomeServico);
				
				st.executeUpdate();
				
				System.out.println("Servico atualizado com sucesso");
			}
			catch (SQLException e) {
				System.out.println("Erro ao atualizar servico: " + e.getMessage());
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
	public void deleteByName(String nome) {
		PreparedStatement st = null;
		if(conn != null) {
			try {
				st = conn.prepareStatement(
						"DELETE FROM tb_servico"
						+ "WHERE servico = ?"
						);
				
				st.setString(1, nome);
				
				st.executeUpdate();
				
				System.out.println("Serviço removido com sucesso.");
			}
			catch (SQLException e) {
				System.out.println("Erro ao remover serviço: " + e.getMessage());
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
	public Servico findByName(String nome) {
		PreparedStatement st = null;
		ResultSet rs = null;
		if(conn != null) {
			try {
				st = conn.prepareStatement(
						"SELECT "
						+ "id_servico, "
						+ "servico, "
						+ "preco, "
						+ "descricao, "
						+ "WHERE pv.nome = ?"
						);

				st.setString(1, nome);
				rs = st.executeQuery();
				if (rs.next()) {
					Servico s = new Servico();
					s.setId(rs.getInt("id_servico"));
					s.setNome(rs.getString("servico"));
					s.setPreco(rs.getDouble("preco"));
					s.setDescricao(rs.getString("descricao"));
					
					return s;
				}
			}
			catch (SQLException e) {
				System.out.println("Erro ao listar serviço: " + e.getMessage());
			}
		}
		return null;
	}
	
	
	@Override
	public List<Servico> findAll() {
		
		List<Servico> servicos = new ArrayList<>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		if(conn!=null) {
			
			try {
				
				st = conn.prepareStatement(
						"SELECT "
						+ "id_servico, "
						+ "servico, "
						+ "preco, "
						+ "descricao "
						+ "FROM tb_servico"
						);
				
				rs = st.executeQuery();
				
				while(rs.next()) {
					Servico s = new Servico();
					s.setId(rs.getInt("id_servico"));
		            s.setNome(rs.getString("servico"));
		            s.setPreco(rs.getDouble("preco"));
		            s.setDescricao(rs.getString("descricao"));
		            
		            servicos.add(s);
				}
			}
			catch (SQLException e) {
				System.out.println("Erro ao listar serviços: " + e.getMessage());
			}
		}
		return servicos;
	}
}
