package entities;
public class Vendedor extends Funcionario {
	
	public Vendedor(String nome, Double salarioBase) {
		this.nome = nome;
		this.salarioBase = salarioBase;
	}
	
	@Override
	public void calcularSalario() {
		Double bonus = salarioBase * 0.05;
		salarioBase += bonus;
	}
}
