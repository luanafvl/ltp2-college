package lista02;
import entities.Animal;
import entities.Cachorro;
import entities.Gato;
public class Program {
	
	public static void main(String[] args) {
		
		Animal a1 = new Animal();
		Animal a2 = new Cachorro();
		Animal a3 = new Gato();
		
		a1.fazerSom();
		a2.fazerSom();
		a3.fazerSom();
	}
}
