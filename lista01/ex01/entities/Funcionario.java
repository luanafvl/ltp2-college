package entities;
public class Funcionario {
	String nome;
	Double salario;
	String cargo;
	
	public Funcionario() {
		
	}
	
	public Funcionario(String nome, Double salario, String cargo) {
		this.nome = nome;
		this.salario = salario;
		this.cargo = cargo;
	}
	
	public Double calcularBonus() {
		double bonus = 0;
		
		if(cargo == "Gerente")
			bonus = salario * 0.2;
		else if (cargo == "Analista")
			bonus = salario * 0.1;
		else if (cargo == "Estagiário")
			bonus = salario * 0.05;
		
		return bonus;
			
	}
}
