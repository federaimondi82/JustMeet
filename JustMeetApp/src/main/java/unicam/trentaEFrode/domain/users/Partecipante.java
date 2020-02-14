package unicam.trentaEFrode.domain.users;

import unicam.trentaEFrode.domain.mainElements.Agenda;

public class Partecipante implements Ruolo{

	private Agenda agenda;

	public Partecipante() {
		agenda = new Agenda();
	}
	
	public boolean partecipa(int idEvento) {
		return agenda.aggiungiEvento(idEvento);
	}

	public boolean esiste(int idEvento) {
		return agenda.esiste(idEvento);
	}
	
}
