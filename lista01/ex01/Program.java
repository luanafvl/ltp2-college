package lista01;
import entities.Funcionario;
public class Program {
	public static void main(String[] args) {
		Funcionario f1 = new Funcionario("Mario", 2000.0, "Estagiário");
		Funcionario f2 = new Funcionario("Joao", 5000.0, "Analista");
		Funcionario f3 = new Funcionario("Maria", 10000.0, "Gerente");
		
		System.out.println(f1.calcularBonus());
		System.out.println(f2.calcularBonus());
		System.out.println(f3.calcularBonus());
	}
}
