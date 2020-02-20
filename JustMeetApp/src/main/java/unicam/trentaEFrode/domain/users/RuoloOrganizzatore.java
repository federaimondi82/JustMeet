package unicam.trentaEFrode.domain.users;

import java.util.List;

import unicam.trentaEFrode.domain.mainElements.Evento;

public interface RuoloOrganizzatore extends Ruolo {
	
	/**
	 * Avvia una modifica all'evento specificato. se la modifica va a buon fine, l'evento modificato 
	 * viene aggiornato in locale
	 * @param evento : l'evento modificato
	 * @return se è stato modificato.
	 */
	public List<Integer> modificaEvento(Evento evento);
	
	/**
	 * Tenta di cambiare l'organizzatore all'evento con id idEvento.
	 * Se la modifica va a buon fine, l'evento viene spostato nell'agenda dell'istanza Partecipante.
	 * @param idEvento l'id dell'evento su cui bisogna modificare l'id dell'organizzatore.
	 * @param idNuovoOrganizzatore : l'id del nuovo organizzatore dell'evento.
	 * @return true se le modifiche sono andate a buon fine.
	 */
	public boolean setOrganizzatore(int idEvento, int idNuovoOrganizzatore,int idVecchioOrganizzatore);

	public List<String> getPartecipanti(Evento nuovoEvento);
}
