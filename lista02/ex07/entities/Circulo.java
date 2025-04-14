package entities;
public class Circulo extends Figura {
	
	Double raio;
	
	public Circulo(Double raio) {
		this.raio = raio;
	}
	
	@Override
	public void calcularArea() {
		area = Math.PI * Math.pow(raio, 2);
	}
}
