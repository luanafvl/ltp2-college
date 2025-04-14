package lista01;
import java.util.ArrayList;
import java.util.List;
import entities.Produto;
public class Program {
	public static void main(String[] args) {
		List<Produto> produtos = new ArrayList<>();
		
		Produto p1 = new Produto("Detergente", 3);
		Produto p2 = new Produto("Amaciante", 5);
		
		produtos.add(p1);
		produtos.add(p2);
		
		p1.alertaReposicao();
		p2.alertaReposicao();
	}
}
