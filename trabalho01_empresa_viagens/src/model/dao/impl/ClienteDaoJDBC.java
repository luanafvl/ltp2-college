package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;

import enums.TipoCliente;
import model.dao.ClienteDao;
import model.entities.Cliente;

public class ClienteDaoJDBC implements ClienteDao {

	Scanner sc = new Scanner(System.in);
	
	private Connection conn;
	
	public ClienteDaoJDBC (Connection conn) {
		this.conn = conn;
		
	}
	
	@Override
	public void insert() {
		
		PreparedStatement st = null;
		
		if (conn != null) {
			try {
				
				System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("Telefone: ");
                String telefone = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("É brasileiro ou estrangeiro? (b/e): ");
                String nacionalidade = sc.nextLine().toLowerCase();
                
                Integer tipo = 0;
                String cpf = null;
                String passaporte = null;
                
                
                if (nacionalidade.equals("b")) {
                	System.out.print("Informe seu CPF: ");
                	cpf = sc.nextLine();
                	tipo = TipoCliente.NACIONAL.ordinal();
                	
                } else if (nacionalidade.equals("e")) {
                	System.out.print("Informe seu passaporte: ");
                	passaporte = sc.nextLine();
                	tipo = TipoCliente.ESTRANGEIRO.ordinal();
                }
				
				st = conn.prepareStatement("INSERT INTO tb_cliente "
						+ "(nome, telefone, email, cpf, passaporte, id_tipo_cliente) "
						+ "VALUES (?, ?, ?, ?, ?, ?);"
						);
				
				st.setString(1, nome);
				st.setString(2, telefone);
				st.setString(3, email);
				st.setString(4, cpf);
				st.setString(5, passaporte);
				st.setInt(6, tipo+1);
				
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
	public void update() {
		
    	PreparedStatement st = null;
		
		if (conn != null) {
			try {
				
				findAll();
				
				System.out.print("Informe id do cliente: ");
		    	Integer id = sc.nextInt();
		    	
		    	System.out.println("Entre com os novos dados ");
		    	
		    	System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("Telefone: ");
                String telefone = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
				
				st = conn.prepareStatement(
						"UPDATE tb_cliente "
						+ "SET nome = ?, telefone = ?, email = ? "
						+ "WHERE id_cliente = ?"
						);
				
				st.setString(1, nome);
				st.setString(2, telefone);
				st.setString(3, email);
				st.setInt(4, id);
			
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
					System.out.println("Erro ao finalizar: " + e.getMessage());
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
				
				System.out.print("Informe o id do cliente: ");
				Integer id = sc.nextInt();
				
				st = conn.prepareStatement(
						"DELETE FROM tb_cliente "
						+ "WHERE id_cliente = ? "
						);
				
				st.setInt(1, id);
				
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
	public void findById() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		if(conn != null) {
			try {
				
				System.out.print("Informe o id do cliente: ");
				Integer id = sc.nextInt();
				
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
						+ "WHERE c.id_cliente = ?"
						);
				
				st.setInt(1, id);

				rs = st.executeQuery();
				
				Cliente cli = new Cliente();
				
				if (rs.next()) {	
					cli.setId(rs.getInt("id"));
					cli.setNome(rs.getString("nome"));
					cli.setTelefone(rs.getString("telefone"));
					cli.setEmail(rs.getString("email"));
					cli.setTipo(TipoCliente.valueOf(rs.getString("tipo")));
					cli.setCpf(rs.getString("cpf"));
					cli.setPassaporte(rs.getString("passaporte"));
				}
				
				System.out.println(cli);
				
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
	}

	
	@Override
	public void findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		if(conn!=null) {
			
			try {
				
				st = conn.prepareStatement(
						"SELECT "
						+ "c.id_cliente, "
						+ "c.nome "
						+ "FROM tb_cliente c"
						);
				
				rs = st.executeQuery();
				
				StringBuilder clientesMsg = new StringBuilder("Clientes cadastrados:");
				
				while(rs.next()) {
					Cliente cli = new Cliente();
		            cli.setId(rs.getInt("id_cliente"));
		            cli.setNome(rs.getString("nome"));
		            clientesMsg.append("\n").append(cli.getId()).append(" - ").append(cli.getNome());
				}
				System.out.println(clientesMsg);
			}
			catch (SQLException e) {
				System.out.println("Erro ao listar clientes: " + e.getMessage());
			}
		}
	}
}
