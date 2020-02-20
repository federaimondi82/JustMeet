package unicam.trentaEFrode.domain.users;

import java.util.List;

import unicam.trentaEFrode.domain.mainElements.Agenda;
import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.mainElements.GestoreEventi;

public class Partecipante implements RuoloPartecipante{

	private Agenda agenda;

	public Partecipante() {
		this.agenda = new Agenda(this);
	}
	
	@Override
	public boolean aggiungiEvento(Evento evento) {
		return partecipa(evento.id());
	}
	
	/**
	 * Aggiunge l'evento specificato all'agenda
	 * @param evento : l'evento da aggiungere
	 * @return se è stato aggiunto.
	 */
	private boolean partecipa(int idEvento) {
		return agenda.aggiungiEvento(idEvento);
	}

	/**
	 * Ritorna true se è salvato in locale un evento avento come id idEvento, false altrimenti.
	 * @param idEvento : l'id dell'evento da cercare.
	 * @return true se è salvato in locale un evento avento come id idEvento, false altrimenti.
	 */
	public boolean esiste(int idEvento) {
		return agenda.esiste(idEvento);
	}

	public List<Evento> getEventi() {
		return agenda.getEventi();
	}
	
	@Override
	public boolean cancellaEvento(int id) {
		return disdiciPartecipazione(id);	
	}
	
	/**
	 * Disdice la partecipazione all'evento avente come id idEvento.
	 * @param idEvento : l'od dell'evento da disdire
	 * @return true se l'operazione è andata a buon fine, false altrimenti.
	 */
	private boolean disdiciPartecipazione(int idEvento) {
		return GestoreEventi.getInstance().disdiciPartecipazione(idEvento, UtenteRegistrato.getInstance().getId());
	}

	

	
	
}
