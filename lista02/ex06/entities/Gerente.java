package entities;
public class Gerente extends Funcionario {
	
	Double bonus = 2000.0;
	
	public Gerente(String nome, Double salarioBase) {
		this.nome = nome;
		this.salarioBase = salarioBase;
	}
	@Override
	public void calcularSalario(){
		salarioBase += bonus;
	}
}
