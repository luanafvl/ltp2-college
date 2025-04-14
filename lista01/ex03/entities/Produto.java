package entities;
public class Produto {
	String nome;
	Integer quantidade;
	
	public Produto() {}
	
	public Produto(String nome, Integer quantidade) {
		this.nome = nome;
		this.quantidade = quantidade;
	}
	
	public void alertaReposicao() {
		if(quantidade < 5)
			System.out.println(nome + ": Alerta, reposição!");
	}
}
