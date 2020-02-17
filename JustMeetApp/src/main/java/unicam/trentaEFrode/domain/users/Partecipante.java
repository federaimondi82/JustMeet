package unicam.trentaEFrode.domain.users;

import java.util.List;

import unicam.trentaEFrode.domain.mainElements.Agenda;
import unicam.trentaEFrode.domain.mainElements.Evento;

public class Partecipante implements Ruolo{

	private Agenda agenda;

	public Partecipante() {
		this.agenda = new Agenda(this);
	}
	
	public boolean partecipa(int idEvento) {
		return agenda.aggiungiEvento(idEvento);
	}

	public boolean esiste(int idEvento) {
		return agenda.esiste(idEvento);
	}

	public List<Evento> getEventi() {
		return agenda.getEventi();
	}
	
}
