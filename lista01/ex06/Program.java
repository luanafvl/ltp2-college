package lista01;
import java.util.Scanner;
public class Program {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String usuario = "usuario";
		String senha = "senha";
		
		int i;
		
		for (i = 0; i < 3; i++) {
			System.out.println("Usuario");
			String usuarioInput = sc.nextLine();
			System.out.println("Senha");
			String senhaInput = sc.nextLine();
			
			if (usuarioInput == usuario && senhaInput == senha) {
				System.out.println("Login bem sucedido!");
				break;
			} else {
				System.out.println("Usuario ou senha incorretos!\nTentativa: " + (i+1));
			}
		}
		
		if(i == 3) {
			System.out.println("Tentativas esgotadas, programa encerrado!");
		}
	}
}
