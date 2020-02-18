package unicam.trentaEFrode.domain.mainElements;

import java.util.ArrayList;
import java.util.List;

import unicam.trentaEFrode.domain.parsers.ParserEventi;
import unicam.trentaEFrode.domain.users.Organizzatore;
import unicam.trentaEFrode.domain.users.Ruolo;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;


/**
 * Consente di mantenere traccia degli eventi dell'utente che utilizza l'applicazione
 * @author Trenta e Frode
 */
public class Agenda {
	
	/*
	 * Il proprietario dell'agenda.
	 * */
	private Ruolo proprietario;
	
	/**
	 * Lista degli eventi che hanno un riferimento al proprietario
	 */
	private List<Evento> eventi;
		
	/**
	 * Crea un'agenda vuota. 
	 * L'agenda deve conoscere il suo proprietario per sapere quali eventi deve contenere.
	 * @param proprietario : l'istanza del proprietario.
	 */
	public Agenda(Ruolo proprietario) {
		this.eventi = new ArrayList<>();
		this.proprietario = proprietario;
		carica();
	}
	
	/**
	 * Aggiunge l'evento avente l'id specificato all'agenda.
	 * @param idEvento : l'id dell'evento da aggiungere.
	 * @return true se l'evento è stato aggiunto, false altrimenti
	 *
	 */
	public boolean aggiungiEvento(int idEvento) {
		return aggiungiEvento(ParserEventi.getInstance().parseEventi(ConnectBackEnd.getInstance().restRequest("/eventi/" + idEvento, "GET")).get(0));
	}
	
	/**
	 * Aggiunge l'evento specificato all'agenda.
	 * @param evento : l'evento da aggiungere.
	 * @return true se l'evento è stato aggiunto, false altrimenti
	 * */
	public boolean aggiungiEvento(Evento evento) {
		return eventi.add(evento);
	}
	
	/**
	 * Ritorna la lista degli eventi.
	 * Se non sono stati salvati in locale, si interroga il database per memorizzarli.
	 * @return la lista degli eventi
	 */
	public List<Evento> getEventi() {
		return this.eventi;
	}

	private void carica() {
		eventi = proprietario instanceof Organizzatore ?
				ParserEventi.getInstance().parseEventi(ConnectBackEnd.getInstance().restRequest("/eventi/utenti/" + UtenteRegistrato.getInstance().getId(), "GET")):
				ParserEventi.getInstance().parseEventi(ConnectBackEnd.getInstance().restRequest("/partecipa/utente/" + UtenteRegistrato.getInstance().getId(), "GET"));
	}

	/**
	 * Rimuove in locale e dal database l'evento con l'id specificato
	 * @param id : l'id dell'evento da cancellare.
	 * @return true se l'operazione è andata a buon fine, false altrimenti.
	 * */
	public boolean cancella(int id) {
		 return eventi.removeIf(e -> e.id() == id);
	}

	/**
	 * Cerca l'evento con l'id specificato nella lista degli eventi.
	 * @param idEvento : l'id dell'evento da cercare.
	 * @return true se l'evento è stato trovato, false altrimenti.
	 * */
	public boolean esiste(int idEvento) {
		return eventi.stream().filter(e -> e.id() == idEvento).count() > 0;		 
	}

	public boolean isEmpty() {
		return eventi.isEmpty();
	}

	public void modificaEvento(Evento evento) {
		this.eventi.stream().forEach(e -> {if(e.id() == evento.id()) e = evento;}); 
	}

}