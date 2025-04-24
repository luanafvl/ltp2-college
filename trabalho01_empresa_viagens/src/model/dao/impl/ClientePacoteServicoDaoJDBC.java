package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entities.PacoteViagem;
import model.entities.Servico;

public class ClientePacoteServicoDaoJDBC {
	
    private Connection conn;

    public ClientePacoteServicoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    public void adicionarPacoteParaCliente(String nomeCliente, String nomePacote) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
        	
            int clienteId;
            
            st = conn.prepareStatement(
            		"SELECT "
            		+ "id_cliente "
            		+ "FROM tb_cliente "
            		+ "WHERE nome = ?"
            		);
            
            st.setString(1, nomeCliente);
            rs = st.executeQuery();
            
            if (rs.next()) {
                clienteId = rs.getInt("id_cliente");
            } else {
                System.out.println("Cliente não encontrado.");
                return;
            }
            
            rs.close();
            st.close();

            int pacoteId;
            
            st = conn.prepareStatement(
            		"SELECT "
            		+ "id_pacote_viagem "
            		+ "FROM tb_pacote_viagem "
            		+ "WHERE nome = ?"
            		);
            
            st.setString(1, nomePacote);
            rs = st.executeQuery();
            
            if (rs.next()) {
                pacoteId = rs.getInt("id_pacote_viagem");
            } else {
                System.out.println("Pacote não encontrado.");
                return;
            }
            
            rs.close();
            st.close();

            st = conn.prepareStatement(
            		"INSERT INTO rel_cliente_pacote "
            		+ "(id_cliente, id_pacote_viagem) "
            		+ "VALUES (?, ?, ?)"
            		);
            
            st.setInt(1, clienteId);
            st.setInt(2, pacoteId);

            st.executeUpdate();
            System.out.println("Pacote associado ao cliente com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar pacote para cliente: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }



    public List<PacoteViagem> listarPacotesDoCliente(String nomeCliente) {
        List<PacoteViagem> lista = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
        	
            int clienteId;
            
            st = conn.prepareStatement("SELECT "
            		+ "id_cliente "
            		+ "FROM tb_cliente "
            		+ "WHERE nome = ?");
            
            st.setString(1, nomeCliente);
            rs = st.executeQuery();
            
            if (rs.next()) {
                clienteId = rs.getInt("id_cliente");
            } else {
                System.out.println("Cliente não encontrado.");
                return lista;
            }
            
            rs.close();
            st.close();
            
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
					+ "INNER JOIN rel_cliente_pacote r ON pv.id_pacote_viagem = r.id_pacote_viagem "
	        		+ "WHERE r.id_cliente = ?"
					);
    
            st.setInt(1, clienteId);
            rs = st.executeQuery();

            while (rs.next()) {
                PacoteViagem pacote = new PacoteViagem();
                pacote.setId(rs.getInt("id_pacote_viagem"));
                pacote.setNome(rs.getString("nome"));
                pacote.setDescricao(rs.getString("descricao"));
                pacote.setPreco(rs.getDouble("preco"));
                pacote.setDuracao(rs.getInt("duracao"));
                pacote.setDestino(rs.getString("destino"));
                pacote.setTipo(rs.getString("tipo_pacote_viagem"));
                lista.add(pacote);
            }

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

        return lista;
    }
    
    public void adicionarServicoAoPacoteCliente(String nomeCliente, String nomePacote, String nomeServico) {
        
    	PreparedStatement st = null;
        ResultSet rs = null;

        try {
            
            int idCliente;
            
            st = conn.prepareStatement(
            		"SELECT "
            		+ "id_cliente "
            		+ "FROM tb_cliente "
            		+ "WHERE nome = ?"
            		);
            
            st.setString(1, nomeCliente);
            rs = st.executeQuery();
            
            if (rs.next()) {
                idCliente = rs.getInt("id_cliente");
            } else {
                System.out.println("Cliente não encontrado.");
                return;
            }
            
            rs.close();
            st.close();

            int idPacote;
            
            st = conn.prepareStatement(
            		"SELECT "
            		+ "id_pacote_viagem "
            		+ "FROM tb_pacote_viagem "
            		+ "WHERE nome = ?"
            		);
            
            st.setString(1, nomePacote);
            rs = st.executeQuery();
            
            if (rs.next()) {
                idPacote = rs.getInt("id_pacote_viagem");
            } else {
                System.out.println("Pacote não encontrado.");
                return;
            }
            
            rs.close();
            st.close();

            int idServico;
            st = conn.prepareStatement(
            		"SELECT "
            		+ "id_servico "
            		+ "FROM tb_servico "
            		+ "WHERE nome = ?"
            		);
            
            st.setString(1, nomeServico);
            rs = st.executeQuery();
            
            if (rs.next()) {
                idServico = rs.getInt("id_servico");
            } else {
                System.out.println("Serviço não encontrado.");
                return;
            }
            
            rs.close();
            st.close();

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
    
    
    public List<Servico> listarServicosDoClienteNoPacote(String nomeCliente, String nomePacote) {
        List<Servico> lista = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
        	
            int idCliente;
            int idPacote;

            st = conn.prepareStatement(
            		"SELECT "
            		+ "id_cliente "
            		+ "FROM tb_cliente "
            		+ "WHERE nome = ?"
            		);
            
            st.setString(1, nomeCliente);
            rs = st.executeQuery();
            
            if (rs.next()) {
                idCliente = rs.getInt("id_cliente");
            } else {
                System.out.println("Cliente não encontrado.");
                return lista;
            }
            
            rs.close();
            st.close();

            st = conn.prepareStatement(
            		"SELECT id_pacote_viagem "
            		+ "FROM tb_pacote_viagem "
            		+ "WHERE nome = ?"
            		);
            
            st.setString(1, nomePacote);
            rs = st.executeQuery();
            
            if (rs.next()) {
                idPacote = rs.getInt("id_pacote_viagem");
            } else {
                System.out.println("Pacote não encontrado.");
                return lista;
            }
            
            rs.close();
            st.close();

            st = conn.prepareStatement(
                    "SELECT "
                    + "s.id_servico, "
                    + "s.nome, "
                    + "s.preco, "
                    + "s.descricao "
                    + "FROM tb_servico s "
                    + "INNER JOIN rel_pacote_servico rps "
                    + "ON s.id_servico = r.id_servico "
                    + "INNER JOIN rel_cliente_pacote rcp "
                    + "ON rcp.id_pacote = rps.id_pacote "
                    + "WHERE rcp.id_cliente = ? AND rcp.id_pacote_viagem = ?"
            		);
            
            st.setInt(1, idCliente);
            st.setInt(2, idPacote);
            rs = st.executeQuery();

            while (rs.next()) {
                Servico servico = new Servico();
                servico.setId(rs.getInt("id_servico"));
                servico.setNome(rs.getString("nome"));
                servico.setPreco(rs.getDouble("preco"));
                servico.setDescricao("descricao");
                lista.add(servico);
            }

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

        return lista;
    }
}