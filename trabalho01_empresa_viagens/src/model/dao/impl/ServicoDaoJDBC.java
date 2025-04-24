package model.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import model.dao.ServicoDao;
import model.entities.Servico;

public class ServicoDaoJDBC implements ServicoDao {

	Scanner sc = new Scanner(System.in);
	
	private Connection conn;
	
	public ServicoDaoJDBC (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert() {
		
		PreparedStatement st = null;
		
		if (conn != null) {
			try {
				
				System.out.print("Nome do serviço: ");
                String nome = sc.nextLine();
                System.out.print("Preço: ");
                Double preco = sc.nextDouble();
                sc.nextLine();
                System.out.print("Descrição: ");
                String descricao = sc.nextLine(); 
				
				st = conn.prepareStatement(
						"INSERT INTO tb_servico "
						+ "(servico, preco, descricao) "
						+ "VALUES (?, ?, ?)"
						);
				
				st.setString(1, nome);
				st.setDouble(2, preco);
				st.setString(3, descricao);
				
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
	public void update() {
		
		PreparedStatement st = null;
		
		if (conn != null) {
			try {
				
				findAll();
				
				System.out.print("Informe o id do serviço: ");
				Integer id = sc.nextInt();
				
				System.out.println("Entre com os novos dados");
				System.out.print("Nome do serviço: ");
                String nome = sc.nextLine();
                System.out.print("Preço: ");
                Double preco = sc.nextDouble();
                sc.nextLine();
                System.out.print("Descrição: ");
                String descricao = sc.nextLine();
                sc.next();
				
				st = conn.prepareStatement(
						"UPDATE tb_servico "
						+ "SET servico = ?, preco = ?, descricao = ? "
						+ "WHERE id_servico = ?"
						);
				
				st.setString(1, nome);
				st.setDouble(2, preco);
				st.setString(3, descricao);
				st.setInt(4, id);
				
				st.executeUpdate();
				
				System.out.println("Serviço atualizado com sucesso");
			}
			catch (SQLException e) {
				System.out.println("Erro ao atualizar serviço: " + e.getMessage());
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
				
				System.out.print("Informe o id do serviço: ");
				Integer id = sc.nextInt();
				
				st = conn.prepareStatement(
						"DELETE FROM tb_servico "
						+ "WHERE id_servico = ?"
						);
				
				st.setInt(1, id);
				
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
	public void findById() {
		PreparedStatement st = null;
		ResultSet rs = null;
		if(conn != null) {
			try {
				
				System.out.print("Informe o id do serviço: ");
				Integer id = sc.nextInt();
				
				st = conn.prepareStatement(
						"SELECT "
						+ "id_servico, "
						+ "servico, "
						+ "preco, "
						+ "descricao "
						+ "FROM tb_servico "
						+ "WHERE id_servico = ?"
						);

				st.setInt(1, id);
				rs = st.executeQuery();
				
				Servico s = new Servico();
				
				if (rs.next()) {
					s.setId(rs.getInt("id_servico"));
					s.setNome(rs.getString("servico"));
					s.setPreco(rs.getDouble("preco"));
					s.setDescricao(rs.getString("descricao"));
				}
				
				System.out.println(s);
			}
			catch (SQLException e) {
				System.out.println("Erro ao listar serviço: " + e.getMessage());
			}
		}
	}
	
	
	@Override
	public void findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		if(conn!=null) {
			
			try {
				
				st = conn.prepareStatement(
						"SELECT "
						+ "id_servico, "
						+ "servico "
						+ "FROM tb_servico"
						);
				
				rs = st.executeQuery();
				
				StringBuilder servicoMsg = new StringBuilder("Serviços cadastrados: ");
				
				while(rs.next()) {
					Servico s = new Servico();
					s.setId(rs.getInt("id_servico"));
		            s.setNome(rs.getString("servico"));
		            
		            servicoMsg.append("\n").append(s.getId()).append(" - ").append(s.getNome());
				}
				
				System.out.println(servicoMsg);
			}
			catch (SQLException e) {
				System.out.println("Erro ao listar serviços: " + e.getMessage());
			}
		}
	}
}