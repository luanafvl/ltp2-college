package model.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.PacoteViagemDao;
import model.entities.PacoteViagem;

public class PacoteViagemDaoJDBC implements PacoteViagemDao {

	private Connection conn;
	
	public PacoteViagemDaoJDBC (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(PacoteViagem pv) {
		
		PreparedStatement st = null;
		
		if (conn != null) {
			try {
				
				st = conn.prepareStatement(
						"INSERT INTO tb_pacote_viagem "
						+ "(nome, preco, descricao, duracao, id_destino, id_tipo_pacote_viagem) "
						+ "VALUES (?, ?, ?, ?, ?, ?)"
						);
				
				st.setString(1, pv.getNome());
				st.setDouble(2, pv.getPreco());
				st.setString(3, pv.getDescricao());
				st.setInt(4, pv.getDuracao());
				st.setInt(5, pv.getIdDestino());
				st.setInt(6, pv.getIdTipo());
				
				st.executeUpdate();
				
				System.out.println("Pacote adicionado com sucesso.");
				
			}
			catch (SQLException e) {
				System.out.println("Erro ao adicionar pacote: " + e.getMessage());
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
	public void update(String nomePacote, PacoteViagem pv) {
		
		PreparedStatement st = null;
		
		if (conn != null) {
			try {
				st = conn.prepareStatement(
						"UPDATE tb_cliente "
						+ "SET nome = ?, preco = ?, descricao = ?, duracao = ?, id_destino = ?, id_tipo_pacote_viagem = ? "
						+ "WHERE nome = ?"
						);
				
				st.setString(1, pv.getNome());
				st.setDouble(2, pv.getPreco());
				st.setString(3, pv.getDescricao());
				st.setInt(4, pv.getDuracao());
				st.setInt(5, pv.getIdDestino());
				st.setInt(6, pv.getIdTipo());
				st.setString(7, nomePacote);
			
				st.executeUpdate();
				
				System.out.println("Pacote atualizado com sucesso");
			}
			catch (SQLException e) {
				System.out.println("Erro ao atualizar pacote: " + e.getMessage());
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
						"DELETE FROM tb_pacote_viagem "
						+ "WHERE nome = ?"
						);
				
				st.setString(1, nome);
				
				st.executeUpdate();
				
				System.out.println("Pacote removido com sucesso.");
			}
			catch (SQLException e) {
				System.out.println("Erro ao remover pacote: " + e.getMessage());
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
	public PacoteViagem findByName(String nome) {
		PreparedStatement st = null;
		ResultSet rs = null;
		if(conn != null) {
			try {
				st = conn.prepareStatement(
						"SELECT "
						+ "pv.id_pacote_viagem, "
						+ "pv.nome, "
						+ "pv.preco, "
						+ "pv.descricao, "
						+ "pv.duracao, "
						+ "d.destino, "
						+ "tpv.tipo_pacote_viagem "
						+ "FROM tb_tipo_pacote_viagem tpv "
						+ "INNER JOIN tb_pacote_viagem pv "
						+ "ON pv.id_tipo_pacote_viagem = tpv.id_tipo_pacote_viagem "
						+ "INNER JOIN tb_destino d "
						+ "ON pv.id_destino = d.id_destino "
						+ "WHERE pv.nome = ?"
						);

				st.setString(1, nome);
				rs = st.executeQuery();
				if (rs.next()) {
					PacoteViagem pv = new PacoteViagem();
					pv.setId(rs.getInt("id_pacote_viagem"));
					pv.setNome(rs.getString("nome"));
					pv.setPreco(rs.getDouble("preco"));
					pv.setDescricao(rs.getString("descricao"));
					pv.setDuracao(rs.getInt("duracao"));
					pv.setDestino(rs.getString("destino"));
					pv.setTipo(rs.getString("tipo_pacote_viagem"));
					
					return pv;
				}
			}
			catch (SQLException e) {
				System.out.println("Erro ao listar pacote: " + e.getMessage());
			}
		}
		return null;
	}

	@Override
	public List<PacoteViagem> findAll() {
		
		List<PacoteViagem> pacotes = new ArrayList<>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		if(conn!=null) {
			
			try {
				
				st = conn.prepareStatement(
						"SELECT "
						+ "pv.id_pacote_viagem, pv.nome, pv.preco, pv.descricao, pv.duracao, "
						+ "d.destino, "
						+ "tpv.tipo_pacote_viagem "
						+ "FROM tb_tipo_pacote_viagem tpv "
						+ "INNER JOIN tb_pacote_viagem pv "
						+ "ON pv.id_tipo_pacote_viagem = tpv.id_tipo_pacote_viagem "
						+ "INNER JOIN tb_destino d "
						+ "ON d.id_destino = pv.id_destino"
						);
				
				rs = st.executeQuery();
				
				while(rs.next()) {
					PacoteViagem pv = new PacoteViagem();
		            pv.setId(rs.getInt("id_pacote_viagem"));
		            pv.setNome(rs.getString("nome"));
		            pv.setPreco(rs.getDouble("preco"));
		            pv.setDescricao(rs.getString("descricao"));
		            pv.setDuracao(rs.getInt("duracao"));
		            pv.setDestino(rs.getString("destino"));
		            pv.setTipo(rs.getString("tipo_pacote_viagem"));
		            
		            pacotes.add(pv);
				}
			}
			catch (SQLException e) {
				System.out.println("Erro ao listar pacotes: " + e.getMessage());
			}
		}
		return pacotes;
	}
}
