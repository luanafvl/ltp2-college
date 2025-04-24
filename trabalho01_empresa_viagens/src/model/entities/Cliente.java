package model.entities;

import java.io.Serializable;

import java.util.Objects;

import enums.TipoCliente;

public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String telefone;
	private String email;
	private TipoCliente tipo;
	private String cpf;
	private String passaporte;
	
	
	public Cliente() {}
	
	public Cliente(String nome, String telefone, String email, TipoCliente tipo, String cpf, String passaporte) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.tipo = tipo;
		this.cpf = cpf;
		this.passaporte = passaporte;
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public TipoCliente getTipo() {
		return tipo;
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPassaporte() {
		return passaporte;
	}

	public void setPassaporte(String passaporte) {
		this.passaporte = passaporte;
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
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		
		String documento = null;
		
		if(passaporte != null) {
			documento = passaporte;
		} else if (cpf != null){
			documento = cpf;
		}
		
		return "[id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", tipo="
				+ tipo + ", documento= " + documento + "]";
	}
}
