package unicam.trentaEFrode.domain.mainElements;

import java.util.ArrayList;
import java.util.List;

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
	 * Flag per sapere se l'event manager e' connesso alla rete
	 * TODO Aggiungere descrizione piu chiara
	 */
	private boolean connesso;

	/**
	 * Crea un'agenda vuota
	 */
	public Agenda() {
		this.eventi = new ArrayList<>();
		this.connesso = false;
	}
	

	
	/**
	 * Aggiunge un evento all'agenda.
	 * Ritorna false se l'evento e' gie' presente.
	 * @throws EventoPresente
	 */
	public void aggiungiEvento(Evento evento) throws EventoPresente {
		for(Evento e: eventi) if(evento.nome().equals(e.nome())) throw new EventoPresente();
		eventi.add(evento);
	}
	
	/**
	 * Ritorna la lista degli eventi
	 */
	public List<Evento> eventi() {
		return this.eventi;
	}
	
	/**
	 * @return the sendEvents
	 */
	public boolean connesso() {
		return connesso;
	}

	/**
	 * the sendEvents to set
	 */
	public void cambiaConnesso() {
		this.connesso = !connesso;
	}
}