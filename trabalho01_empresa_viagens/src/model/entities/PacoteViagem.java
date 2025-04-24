package model.entities;

import java.util.Objects;

public class PacoteViagem{
	
	private Integer id;
	private String nome;
	private Double preco;
	private String descricao;
	private Integer duracao;
	private Integer idDestino;
	private String destino;
	private Integer idTipo;
	private String tipo;
	
	
	public PacoteViagem() {}
	
	public PacoteViagem(String nome, Double preco, String descricao, Integer duracao, 
			Integer idDestino, Integer idTipo) {
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.duracao = duracao;
		this.idDestino = idDestino;
		this.idTipo = idTipo;	
	}
	
	public PacoteViagem(String nome, Double preco, String descricao, Integer duracao, 
			String destino, String tipo) {
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.duracao = duracao;
		this.destino = destino;
		this.tipo = tipo;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public Integer getIdDestino() {
		return idDestino;
	}

	public void setIdDestino(Integer idDestino) {
		this.idDestino = idDestino;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Integer getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PacoteViagem other = (PacoteViagem) obj;
		return Objects.equals(id, other.id);
	}

	
	@Override
	public String toString() {
		return "PacoteViagem [id=" + id + ", nome=" + nome + ", destino=" + destino + ", duracao=" + duracao
				+ ", preco=" + preco + ", tipo=" + tipo + ", descricao=" + descricao + "]";
	}
	
	
}
