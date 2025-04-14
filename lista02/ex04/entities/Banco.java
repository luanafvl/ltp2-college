package entities;
public class Banco {
	private String nome;
	static Double taxaJuros = 0.02;
	
	public Banco() {}
	
	public Banco(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void alterarTaxaJuros(Double novaTaxa) {
		taxaJuros = novaTaxa;
	}
	
	@Override
	public String toString() {
		return "Banco: " + nome + " - Taxa de Juros: " + taxaJuros;
	}
}
