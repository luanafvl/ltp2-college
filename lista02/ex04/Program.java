package lista02;
import entities.Banco;
public class Program {
	
	public static void main(String[] args) {
			
		Banco b1 = new Banco("BB");
		Banco b2 = new Banco("Inter");
		
		System.out.println("Antes");
		System.out.println(b1);
		System.out.println(b2);
		
		b1.alterarTaxaJuros(0.5);
		b1.alterarTaxaJuros(0.75);
		
		System.out.println("Depois");
		System.out.println(b1);
		System.out.println(b2);
	}
}
