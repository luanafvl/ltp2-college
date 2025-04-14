package entities;
import java.util.ArrayList;
import java.util.List;
public class Disciplina {
	String nome;
	String dia;
	String horario;
	
	List<Aluno> alunos = new ArrayList<>();
	
	public Disciplina() {}
	
	public Disciplina(String nome, String dia, String horario) {
		this.nome = nome;
		this.dia = dia;
		this.horario = horario;
	}
	
	public void exibirAlunos() {
		for (Aluno al : alunos) {
			System.out.println(al);
		}
	}
	
	public void addAluno(String nome, String matricula) {
		Aluno a = new Aluno(nome, matricula);
		alunos.add(a);
	}
}
