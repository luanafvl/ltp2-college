// Pacote onde a classe está localizada
package application;

import view.Menu;

// Classe principal que contém o método main, ponto de entrada da aplicação
public class Program {

    // Método principal que será executado quando o programa iniciar
	public static void main(String[] args) {
	    
	    // Cria uma instância da classe Menu, que controla as interações do usuário
	    Menu menu = new Menu();
	    
	    // Chama o método menu(), que exibe o menu principal e gerencia a lógica do sistema
	    menu.menuPrincipal();
    }
}
