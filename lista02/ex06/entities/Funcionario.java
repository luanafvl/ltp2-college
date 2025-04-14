package entities;
public class Funcionario {
	String nome;
	Double salarioBase;
	
	public Funcionario() {}
	
	public Funcionario(String nome, Double salarioBase) {
		this.nome = nome;
		this.salarioBase = salarioBase;
	}
	public void calcularSalario() {}
	
	public String toString() {
		return "Nome: " + nome
				+ " Salário: R$" + String.format("%.2f", salarioBase);
	}
}
