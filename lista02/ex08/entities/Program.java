package lista02;
import entities.Loja;
public class Program {
  
	public static void main(String[] args) {
  	 	
		Loja l = new Loja();
		
		l.vender("Shampoo");
		l.vender("Condicionador", 2);
		l.vender("Sabonete", 0.355);
	}
}
