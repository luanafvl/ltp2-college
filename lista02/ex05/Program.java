package lista02;
import java.util.Scanner;
public class Program {
  
	public static void main(String[] args) {
  	
   	Scanner sc = new Scanner(System.in);
   	
   	System.out.print("Informe o número: ");
   	String valor = sc.nextLine();
   	
   	int result = converterParaInteiro(valor);
   	System.out.println(result);
	}
	
	
	static Integer converterParaInteiro(String valor) {
		
		int novoValor = 0;
		
		try {
			novoValor = Integer.parseInt(valor);
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid input");
			e.getMessage();
		}
		
		return novoValor;
	}
}
