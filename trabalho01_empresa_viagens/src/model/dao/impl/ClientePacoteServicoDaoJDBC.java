package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;

import model.entities.PacoteViagem;

public class ClientePacoteServicoDaoJDBC {
	
	Scanner sc = new Scanner(System.in);
	
    private Connection conn;

    public ClientePacoteServicoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    public void adicionarPacoteParaCliente(Integer idCliente, Integer idPacoteViagem) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
        	
            st = conn.prepareStatement(
            		"INSERT INTO rel_cliente_pacote "
            		+ "(id_cliente, id_pacote_viagem) "
            		+ "VALUES (?, ?)"
            		);
            
            st.setInt(1, idCliente);
            st.setInt(2, idPacoteViagem);

            st.executeUpdate();
            System.out.println("Pacote associado ao cliente com sucesso!");

        } 
        catch (SQLException e) {
            System.out.println("Erro ao adicionar pacote para cliente: " + e.getMessage());
        } 
        finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }


    public void listarPacotesDoCliente(Integer idCliente) {
    	
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            
    		st = conn.prepareStatement(
					"SELECT "
					+ "pv.id_pacote_viagem, "
					+ "pv.nome "
					+ "FROM tb_pacote_viagem pv "
					+ "INNER JOIN rel_cliente_pacote r ON pv.id_pacote_viagem = r.id_pacote_viagem "
	        		+ "WHERE r.id_cliente = ?"
					);
    
            st.setInt(1, idCliente);
            rs = st.executeQuery();
            
            PacoteViagem pacote = new PacoteViagem();
            
            StringBuilder pacoteClienteMsg = new StringBuilder("Pacotes do cliente: ");

            while (rs.next()) {
                
            	pacote.setId(rs.getInt("id_pacote_viagem"));
                pacote.setNome(rs.getString("nome"));
                pacoteClienteMsg.append("\n").append(pacote.getId()).append(" - ").append(pacote.getNome());
            }
            
            System.out.println(pacoteClienteMsg);

        } catch (SQLException e) {
            System.out.println("Erro ao listar pacotes do cliente: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException e) {
                System.out.println("Erro finalizar: " + e.getMessage());
            }
        }
    }
    
    public void adicionarServicoAoPacoteCliente(Integer idCliente, Integer idPacote, Integer idServico) {
        
    	PreparedStatement st = null;
        ResultSet rs = null;

        try {
        	st = conn.prepareStatement(
                "INSERT INTO rel_cliente_pacote_servico "
                + "(id_cliente, id_pacote_viagem, id_servico) "
                + "VALUES (?, ?, ?)"
            );
            
            st.setInt(1, idCliente);
            st.setInt(2, idPacote);
            st.setInt(3, idServico);
            st.executeUpdate();

            System.out.println("Serviço associado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao associar serviço ao pacote: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException e) {
                System.out.println("Erro ao finalizar: " + e.getMessage());
            }
        }
    }
    
    
    public void listarServicosDoClienteNoPacote(Integer idCliente, Integer idPacoteViagem) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
        	
    	    st = conn.prepareStatement(
    	    		"SELECT "
    	    		+ "s.id_servico, "
	    			+ "s.servico "
	                + "FROM tb_servico s " 
	    			+ "INNER JOIN rel_cliente_pacote_servico r "
	    			+ "ON r.id_servico = s.id_servico " 
	                + "WHERE r.id_cliente = ? AND r.id_pacote_viagem = ?"
    	    		);
    	    
    	    st.setInt(1, idCliente);
    	    st.setInt(2, idPacoteViagem);

    	    rs = st.executeQuery();

    	    StringBuilder servicosPacoteClienteMsg = new StringBuilder("Serviços do cliente " + idCliente + " no pacote " + idPacoteViagem + ":");
    	    while (rs.next()) {
    	        Integer idServico = rs.getInt("id_servico");
    	        String nome = rs.getString("servico");

    	        servicosPacoteClienteMsg.append("\n").append(idServico).append(" - ").append(nome);
    	    }
    	    
    	    System.out.println(servicosPacoteClienteMsg);

        } catch (SQLException e) {
            System.out.println("Erro ao listar serviços: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException e) {
                System.out.println("Erro ao finalizar: " + e.getMessage());
            }
        }
    }
}