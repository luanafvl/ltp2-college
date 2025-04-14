package lista02;
import entities.Funcionario;
import entities.Gerente;
import entities.Vendedor;
public class Program {
  
	public static void main(String[] args) {
  	 	Funcionario f1 = new Gerente("Márcio", 10000.0);
  	 	Funcionario f2 = new Vendedor("Joana", 5000.0);
  	 	
  	 	System.out.println("Antes:");
  	 	System.out.println(f1);
	 	System.out.println(f2);
	 	
  	 	System.out.println();
  	 	f1.calcularSalario();
  	 	f2.calcularSalario();
  	 	
  	 	System.out.println("Depois:");
  	 	System.out.println(f1);
  	 	System.out.println(f2);
	}
}
