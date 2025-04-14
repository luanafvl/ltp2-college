package entities;
public class Quadrado extends Figura {
	
	Double lado;
	
	public Quadrado(Double lado) {
		this.lado = lado;
	}
	
	@Override
	public void calcularArea() {
		area = Math.pow(lado, 2);
	}
}
