package unicam.trentaEFrode.domain.mainElements;

import java.net.ConnectException;
import java.util.Map;

import unicam.trentaEFrode.domain.users.UtenteRegistrato;

/**
 * Classe per inviare i dati al lato backend per quanto riguarda la regitrazione di dati
 * @author feder
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
	public boolean registra(Evento evento) throws ConnectException {
		return	ConnectBackEnd.getInstance().restRequest("/eventi/nuovo/", "POST", evento.toString()+":"+String.valueOf(UtenteRegistrato.getInstance().getId()));
	}
	
	//TODO FEDERICO, ti ho lasciato il metodo che avevo pensato io. forse ti pu� essere utile
	/**
	 * Crea la chiamata per modificare dei singoli campi dell'evento avente l'id = id.
	 * @throws ConnectException 
	 * 
	 * */
	public boolean modificaVeronica(int id, Map<String, String> campi) throws ConnectException {
		String mapToString = "";
		int count = 0;
		for (Map.Entry<String, String> campo: campi.entrySet()) {
			count ++; // TRA IL CAMPO E IL VALORE INSERISCO = MENTRE TRA I CAMPI INSERISCO : per facilitare l'eventuale split
			mapToString += campo.getKey() + "=" + campo.getValue() + (count != campi.size()? ":" : "");
		}
		return ConnectBackEnd.getInstance().restRequest("/eventi/aggiorna/" + id , "UPDATE", mapToString);		
	}
		
	/**
	 * Registra nel database l'utente con le informazioni contenute nel docu.
	 * @param docu
	 * @return Ritorna true se la registrazione e' andata a buon fine; false altrimenti.
	 * @throws ConnectException 
	 */
	public boolean registra(DocuDiRegis docu) throws ConnectException {
		return ConnectBackEnd.getInstance().restRequest("/utenti/", "POST", docu.toString());
		
	}
}

