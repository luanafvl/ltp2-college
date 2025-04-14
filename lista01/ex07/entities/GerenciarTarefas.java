package entities;
import java.util.ArrayList;
import java.util.List;
public class GerenciarTarefas {
	String nome;
	
	List<GerenciarTarefas> tarefas = new ArrayList<>();
	
	public GerenciarTarefas() {}
	
	public GerenciarTarefas(String nome) {
		this.nome = nome;
	}
	
	public void addTarefa(String tarefa) {
		GerenciarTarefas gt = new GerenciarTarefas(tarefa);
		tarefas.add(gt);
	}
	
	public void removerTarefa(String tarefa) {
		for(GerenciarTarefas gt : tarefas) {
			if (gt.nome.equals(tarefa)) {
				tarefas.remove(gt);
			}
		}
	}
	
	public void exibirTarefas() {
		for(GerenciarTarefas gt : tarefas) {
			System.out.println(gt);
		}
	}
}
