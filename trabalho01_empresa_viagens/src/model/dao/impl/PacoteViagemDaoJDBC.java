package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import model.dao.PacoteViagemDao;
import model.entities.PacoteViagem;

public class PacoteViagemDaoJDBC implements PacoteViagemDao {

	Scanner sc = new Scanner(System.in);

	private Connection conn;
	
	public PacoteViagemDaoJDBC (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert() {
		
		PreparedStatement st = null;
		
		if (conn != null) {
			try {
		
        		System.out.print("Nome do pacote: ");
                String nome = sc.nextLine();
                System.out.print("Descrição: ");
                String descricao = sc.nextLine();
                System.out.print("Preço: ");
                Double preco = sc.nextDouble();
                System.out.print("Duração (dias): ");
                Integer duracao = sc.nextInt();
                findAllDestinos();
                System.out.print("Destino: ");
                Integer destino = sc.nextInt();
                findAllTipos();
                System.out.print("Tipo de pacote: ");
                Integer tipo = sc.nextInt();
                sc.nextLine();
				
				st = conn.prepareStatement(
						"INSERT INTO tb_pacote_viagem "
						+ "(nome, preco, descricao, duracao, id_destino, id_tipo_pacote_viagem) "
						+ "VALUES (?, ?, ?, ?, ?, ?)"
						);
				
				st.setString(1, nome);
				st.setDouble(2, preco);
				st.setString(3, descricao);
				st.setInt(4, duracao);
				st.setInt(5, destino);
				st.setInt(6, tipo);
				
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
	public void update() {
		
		PreparedStatement st = null;
		
		if (conn != null) {
			try {
			
				findAll();
				
				System.out.print("Informe o id do pacote: ");
				Integer id = sc.nextInt();
				
				System.out.println("Entre com os novos dados do pacote");
				
				sc.nextLine();			
				
        		System.out.print("Nome do pacote: ");
                String nome = sc.nextLine();
                System.out.print("Descrição: ");
                String descricao = sc.nextLine();
                System.out.print("Preço: ");
                Double preco = sc.nextDouble();
                System.out.print("Duração (dias): ");
                Integer duracao = sc.nextInt();
                findAllDestinos();
                System.out.print("Destino: ");
                Integer destino = sc.nextInt();
                findAllTipos();
                System.out.print("Tipo de pacote: ");
                Integer tipo = sc.nextInt();
                sc.nextLine();
				
				st = conn.prepareStatement(
						"UPDATE tb_pacote_viagem "
						+ "SET nome = ?, preco = ?, descricao = ?, duracao = ?, id_destino = ?, id_tipo_pacote_viagem = ? "
						+ "WHERE id_pacote_viagem = ?"
						);
				
				st.setString(1, nome);
				st.setDouble(2, preco);
				st.setString(3, descricao);
				st.setInt(4, duracao);
				st.setInt(5, destino);
				st.setInt(6, tipo);
				st.setInt(7, id);
			
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
	public void deleteById() {
		PreparedStatement st = null;
		if(conn != null) {
			try {
			
				findAll();
				
				System.out.print("Informe o id do pacote: ");
				Integer id = sc.nextInt();
				
				st = conn.prepareStatement(
						"DELETE FROM tb_pacote_viagem "
						+ "WHERE id_pacote_viagem = ?"
						);
				
				st.setInt(1, id);
				
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
	public void findById() {
		PreparedStatement st = null;
		ResultSet rs = null;
		if(conn != null) {
			try {
			
				System.out.print("Informe o id do pacote: ");
				Integer id = sc.nextInt();
				
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
						+ "WHERE pv.id_pacote_viagem = ?"
						);

				st.setInt(1, id);
				rs = st.executeQuery();
				
				PacoteViagem pv = new PacoteViagem();
				
				if (rs.next()) {
					pv.setId(rs.getInt("id_pacote_viagem"));
					pv.setNome(rs.getString("nome"));
					pv.setPreco(rs.getDouble("preco"));
					pv.setDescricao(rs.getString("descricao"));
					pv.setDuracao(rs.getInt("duracao"));
					pv.setDestino(rs.getString("destino"));
					pv.setTipo(rs.getString("tipo_pacote_viagem"));
				}
				
				System.out.println(pv);
			}
			catch (SQLException e) {
				System.out.println("Erro ao listar pacote: " + e.getMessage());
			}
		}
	}

	@Override
	public void findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		if(conn != null) {
			
			try {
				
				st = conn.prepareStatement(
						"SELECT "
						+ "id_pacote_viagem, "
						+ "nome "
						+ "FROM tb_pacote_viagem"
						);
				
				rs = st.executeQuery();
				
				StringBuilder pacoteViagensMsg = new StringBuilder("Pacotes de viagem cadastrados: ");
				
				while(rs.next()) {
					PacoteViagem pv = new PacoteViagem();
		            pv.setId(rs.getInt("id_pacote_viagem"));
		            pv.setNome(rs.getString("nome"));
		            
		            pacoteViagensMsg.append("\n").append(pv.getId()).append(" - ").append(pv.getNome());
				}
				
				System.out.println(pacoteViagensMsg);
			}
			catch (SQLException e) {
				System.out.println("Erro ao listar pacotes: " + e.getMessage());
			}
		}
	}
	
	
	@Override
	public void findAllDestinos() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		if(conn != null) {
			
			try {
				
				st = conn.prepareStatement(
						"SELECT "
						+ "id_destino, "
						+ "destino "
						+ "FROM tb_destino"
						);
				
				rs = st.executeQuery();
				
				StringBuilder destinoMsg = new StringBuilder("Destinos disponíveis: ");
				
				while(rs.next()) {
		            Integer idDestino = rs.getInt("id_destino");
		            String destino = rs.getString("destino");
		            
		            destinoMsg.append("\n").append(idDestino).append(" - ").append(destino);
				}
				
				System.out.println(destinoMsg);
			}
			catch (SQLException e) {
				System.out.println("Erro ao listar destinos: " + e.getMessage());
			}
		}
	}
	
	
	@Override
	public void findAllTipos() {

		PreparedStatement st = null;
		ResultSet rs = null;
		
		if(conn != null) {
			
			try {
				
				st = conn.prepareStatement(
						"SELECT "
						+ "id_tipo_pacote_viagem, "
						+ "tipo_pacote_viagem "
						+ "FROM tb_tipo_pacote_viagem"
						);
				
				rs = st.executeQuery();
				
				StringBuilder tipoMsg = new StringBuilder("Tipos de pacote disponíveis: ");
				
				while(rs.next()) {
		            Integer idTipo = rs.getInt("id_tipo_pacote_viagem");
		            String tipo = rs.getString("tipo_pacote_viagem");
		            
		            tipoMsg.append("\n").append(idTipo).append(" - ").append(tipo);
				}
				
				System.out.println(tipoMsg);
			}
			catch (SQLException e) {
				System.out.println("Erro ao listar tipos de pacote: " + e.getMessage());
			}
		}
	}
}