package unicam.trentaEFrode.domain.users;

import java.util.List;

import unicam.trentaEFrode.domain.mainElements.Evento;

public interface Ruolo {

	public List<Evento> getEventi();
	
	/**
	 * Aggiunge l'evento specificato all'agenda
	 * @param evento : l'evento da aggiungere
	 * @return se è stato aggiunto.
	 */
	public boolean aggiungiEvento(Evento evento);
	
	/**
	 * Toglie l'evento specificato all'agenda
	 * @param id : l'evento da togliere
	 * @return se è stato cancellato.
	 */
	public boolean cancellaEvento(int id);
}
