package lista02;
import entities.Circulo;
import entities.Figura;
import entities.Quadrado;
public class Program {
  
	public static void main(String[] args) {
  	 	
		Figura f1 = new Quadrado(5.0);
		Figura f2 = new Circulo(5.0);
		
		f1.calcularArea();
		f2.calcularArea();
		
		System.out.println("Área do quadrado = " + String.format("%.2f", f1.mostrarArea()));
		System.out.println("Área do círculo = " + String.format("%.2f", f2.mostrarArea()));
	}
}
