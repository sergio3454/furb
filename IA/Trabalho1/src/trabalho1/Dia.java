package trabalho1;

public class Dia {

	private Disciplina aula1;
	private Disciplina aula2;

	public boolean ehMesmaAula() {
		return aula1 == aula2;
	}

	public boolean professorEstaLivre(Professor professor) {
		return aula1.getProfessorMinistrando() != professor && aula2.getProfessorMinistrando() != professor;
	}

	public boolean estaCompleto() {
		return aula1 != null && aula2 != null;
	}

	public Disciplina getAula1() {
		return this.aula1;
	}

	public Disciplina getAula2() {
		return this.aula2;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Disciplina))
			return false;

		Dia outro = (Dia) obj;

		if (!this.getAula1().equals(outro.getAula1())) return false;
		if (!this.getAula2().equals(outro.getAula2())) return false;

		return true;
	}

}
