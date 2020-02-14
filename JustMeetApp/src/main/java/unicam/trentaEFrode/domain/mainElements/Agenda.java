package unicam.trentaEFrode.domain.mainElements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import unicam.trentaEFrode.domain.parsers.ParserEventi;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;
import unicam.trentaEFrode.exceptions.EventoPresente;


/**
 * Consente di mantenere traccia degli eventi dell'utente che utilizza l'applicazione
 * @author Trenta e Frode
 *
 */
public class Agenda {

	/**
	 * Lista degli eventi creati dal possessore dell'agenda
	 */
	private List<Evento> eventi;
		
	/**
	 * Crea un'agenda vuota
	 */
	public Agenda() {
		this.eventi = new ArrayList<>();
	}
	

	
	/**
	 * Aggiunge un evento all'agenda.
	 * Ritorna false se l'evento e' gie' presente.
	 * @throws EventoPresente
	 */
	public boolean aggiungiEvento(int idEvento) {
		return eventi.add(ParserEventi.getInstance().parseEventi(ConnectBackEnd.getInstance().restRequest("/eventi/" + idEvento, "GET")).get(0));
	}
	
	/**
	 * Ritorna la lista degli eventi
	 */
	public List<Evento> getEventi() {
		if(eventi.size() == 0 ) eventi = ParserEventi.getInstance().parseEventi(ConnectBackEnd.getInstance().restRequest("/eventi/utenti/" + UtenteRegistrato.getInstance().getId(), "GET"));
		return this.eventi;
	}


	public void carica(List<Evento> eventi) {
		this.eventi = eventi;
	}



	public boolean cancella(int id) {
		boolean result = Boolean.getBoolean(ConnectBackEnd.getInstance().restRequest("/eventi/cancella/" + id, "DELETE"));
		if(result) result = eventi.removeIf(e -> e.id() == id);
		return result;
	}



	public boolean esiste(int idEvento) {
		return eventi.stream().filter(e -> e.id() == idEvento).count() > 0;		 
	}
}