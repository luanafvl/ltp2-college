package lista02;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class Program {
  
	public static void main(String[] args) {
  	 	
		try {
      // Colocar o endereço do arquivo
			lerArquivo("endereço");
		}
		catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
		}
	}
	
	static void lerArquivo(String nomeArquivo) throws FileNotFoundException {
			
		File file = new File(nomeArquivo);
		
		if(!file.exists()) {
			throw new FileNotFoundException();
		} else {
			Scanner sc = null;
			try {
				sc = new Scanner(file);
				while (sc.hasNextLine()) {
					System.out.println(sc.nextLine());
				}
			}
			catch (IOException e) {
				System.out.println(e.getMessage());
			}
			finally {
				if(sc != null)
					sc.close();
			}
		}
	}
}
