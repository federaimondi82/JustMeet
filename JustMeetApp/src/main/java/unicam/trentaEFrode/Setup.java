package unicam.trentaEFrode;

import java.util.List;
import java.util.stream.Collectors;

import unicam.trentaEFrode.domain.mainElements.ConnectBackEnd;
import unicam.trentaEFrode.domain.mainElements.RegistroStatico;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;
import unicam.trentaEFrode.ui.AppViewManager;

/**
 * Una pure Fabrication per quanto riguarda la connessione al backend e la visualizzaizone delle pagine
 * 
 *
 */
public class Setup {
	
	private static Setup instance;
	private RegistroStatico reg;
	
	
	private Setup() {
		this.reg = RegistroStatico.getInstance();
	}
	
	public static Setup getInstance() {
		if(instance==null) instance=new Setup();
		return instance;
	}
	
	/**
	 * Viene controllato se il dispositivo usato e' connesso alla rete<br>
	 * inviando una richiesta al beckend
	 */
	public boolean check_connection() {
		boolean result = false;
		try {		
			String s = ConnectBackEnd.getInstance().restRequest("/testConnessione", "GET");
			result = Boolean.parseBoolean(s);					
			if(result) System.out.println("connessione ok");
		} catch (Exception e) {
			System.out.println("nessuna connessione");
			return false;
		}
		return result;
	}

	/**
     * Controlla se il file di cache esiste<br>
     * se il file di cache esiste: il file viene letto per controllare se contiene i dati dell'autenticazione
     * se non esiste: il file viene creato, l'utente e' rimandato alla View di registrazione
     */
	public void loadCacheFile() {
		//TODO implementare il caso d'uso
//		try {
//			if(RegistroStatico.getInstance().cacheFileExists()) {
//				//Se il file di cache esiste ma e' vuoto si va all'autenticazione
//				//altriemnte alla pagina principale
//				
//				if(UtenteRegistrato.getInstance().getNome()==null) {	
//					
//					try {
//						RegistroStatico.getInstance().fileReader();
//						RegistroStatico.getInstance().leggiUtente();
//						RegistroStatico.getInstance().leggiInteressi();
//						System.out.println(UtenteRegistrato.getInstance().toString());
//						System.out.println(UtenteRegistrato.getInstance().toStringInteressi());
//					}catch(IndexOutOfBoundsException e) {
//						AppViewManager.AUTENTICAZIONE.switchView();
//					}
//					
//					AppViewManager.AUTENTICAZIONE.switchView();
//				}else {
//
//					AppViewManager.HOME_PAGE.switchView();
//				}
//
//			}
//			else {
//				//se il file di cache non esiste viene cerato e si rimanda alla registrazione
//				if(RegistroStatico.getInstance().fileCreator()) {
//					AppViewManager.REGISTRAZIONE.switchView();
//				}
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		
	}
	
	

}
