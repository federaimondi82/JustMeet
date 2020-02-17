package unicam.trentaEFrode.domain.mainElements;

import java.net.ConnectException;

/**
 * Classe per inviare i dati al lato backend per quanto riguarda la regitrazione di dati.
 *
 */
public class Registratore {
	
	private static Registratore instance = null;
	
	public static Registratore getInstance() {
		if(instance == null) instance = new Registratore();
		return instance;
	}
	
	/**Registra nel database l'evento o apporta modifiche ad esso.
	 * Se l'id dell'evento passato e' -1, l'evento e' stato appena creato e deve essere registrato nel database.
	 * Altrimenti deve essere aggiornato.
	 * Ritorna true se la registrazione e' andata a buon fine; false altrimenti.
	 * @param evento
	 * @return ritorna true se l'evento e' stato salvato, altrimenti false
	 * @throws ConnectException 
	 */
	public boolean registra(Evento evento) {
		return ConnectBackEnd.getInstance().restRequest("/eventi/nuovo/", "POST", evento.toString());
	}
		
	/**
	 * Registra nel database l'utente con le informazioni contenute nel docu.
	 * @param docu
	 * @return Ritorna true se la registrazione e' andata a buon fine; false altrimenti.
	 * @throws ConnectException 
	 */
	public boolean registra(DocuDiRegis docu) {
		return ConnectBackEnd.getInstance().restRequest("/utenti/", "POST", docu.toString());
	}
	
	/**
	 * Registra nel database la partecipazione dell'utente con id idUtente all'evento con id idEvento.
	 * @
	 * */
	public boolean registraPartecipazione(int idEvento, int idUtente) {
		return ConnectBackEnd.getInstance().restRequest("/partecipa/", "POST", idEvento +":" + idUtente);
	}
}

