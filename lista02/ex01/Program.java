package lista02;


public class Program {
  
	public static void main(String[] args) {
  	
   	System.out.println(divisao(5,0));
	}
  
	static Integer divisao(Integer a, Integer b) {
  	
   	Integer result = 0;
  	
   	try {
       	result = a/b;
   	}
   	catch (ArithmeticException e) {
       	System.out.println("Impossível dividir por 0.");
   	}
  	
   	return result;
	}
}
