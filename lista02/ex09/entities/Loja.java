package entities;

public class Loja {

  public Loja(){}

  public void vender (String produto) {
    System.out.println("Venda de " + produto);
  }

  public void vender (String produto, int quantidade) {
    System.out.println("Venda de " + quantidade + " unidade(s) de " + produto);
  }

  public void vender (String produto, double desconto) {
	  desconto *=100;
    System.out.println("Venda com " + String.format("%.2f", desconto) + "%");
  }
}
