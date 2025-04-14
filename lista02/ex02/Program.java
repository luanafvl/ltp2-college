package lista02;
import entities.Calculadora;
public class Program {
	
	public static void main(String[] args) {
		
		Calculadora c = new Calculadora();
		
		System.out.println(c.soma(1, 2));
		System.out.println(c.soma(5.5, 0.5));
		System.out.println(c.soma(3, 6, 9));
	}
}
