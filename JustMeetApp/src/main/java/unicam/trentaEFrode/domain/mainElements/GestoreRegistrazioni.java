package unicam.trentaEFrode.domain.mainElements;

import java.net.ConnectException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.DatatypeConverter;


/**
 * Consente di effettuare i controlli dei dati inseriti dall'utente in fase di registrazione;
 * se i dati passano il controllo vengono inviati e memorizzati nel server.
 */
public class GestoreRegistrazioni extends Gestore{
	
	private static GestoreRegistrazioni instance; 
	
	/**
	 * Ritorna un'istanza di questa classe.
	 * @return un'istanza di questa classe.
	 * */
	public static GestoreRegistrazioni getInstance() { 
		if(instance == null) instance = new GestoreRegistrazioni();
		return instance;
	}
	
	/**
	 * Effettua i controlli sul docu passato e ritorna una lista di codici che identificano il risultato
	 * di questi controlli
	 * @param docu: l'oggetto da controllare.
	 * @return la lista dei codici che identificano il risultato dei controlli.
	 * @throws ConnectException 
	 * */
	public List<Integer> effettuaControlli(DocuDiRegis docu) throws ConnectException{
		String nome = docu.getNome();
		String cognome = docu.getCognome();
		String email = docu.getEmail();
		String nickname = docu.getNickname();
		String password = docu.getPassword();
		String password2 = docu.getRipetiPassword();
		String citta = docu.getcitta();
		String cap = docu.getCap();
		LocalDate dataNascita = docu.getDataDiNascita();
		List<Integer> risposta = new ArrayList<>();
		if(dataNascita !=null && !controllaDataNascita(dataNascita)) {
			risposta.add(5);
			return risposta;
		}
		if(nome == "" || cognome == "" || email == "" || nickname == "" || password == ""|| password2 == ""|| citta == "" || cap == "" || dataNascita == null) risposta.add(1);
		if(email != "" & !controllaEmail(email)) risposta.add(7);
		if(password != "" & password2!= "" & password != password2) risposta.add(6);
		if(cap != "" & !controllaCap(cap)) risposta.add(2);
		if (risposta.size() !=0) return risposta;		
		String passwordCodificata = codificaPassword(password, "SHA-256");
		if (passwordCodificata == null) risposta.add(0);
		else {
			docu.setPassword(passwordCodificata);
			risposta.add(Registratore.getInstance().registra(docu)?-1:0);
		}
		return risposta;
	}
	
	/**
	 * Controlla nel database se c'e' un utente con questa email
	 * @param email
	 * @return ritorna true se l'email e' gia' nel database, altriemnti false
	 * @throws ConnectException 
	 */
	private boolean controllaEmail(String email) throws ConnectException {
		//TODO migliorare, ora ritorna sempre false
		return ! ConnectBackEnd.getInstance().restRequest("/utenti/", "GET",email);
		
	}
	
	/**
	 * Ritorna true se la data di nascita � valida, false altrimenti.
	 * Ogni utente deve essere maggiorenne. 
	 * @param data: la data da controllare.
	 * @return true se l'utente e' maggiorenne.
	 * @return false se l'utente e' minorenne.
	 * */
	public boolean controllaDataNascita(LocalDate data) {
		return Period.between(data, LocalDate.now() ).getYears() >= 18? true : false;
	}
	
	/**
	 * Ritorna la password specificata codificata.
	 * @param password: la password da codificare.
	 * @param nomeAlgoritmo: il nome dell'algoritmo di cifratura da usare.
	 * @return la password cifrata se @nomeAlgoritmo e' valido, null altrimenti.
	 */
	public static String codificaPassword(String password, String nomeAlgoritmo){
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(nomeAlgoritmo);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	    md.update(password.getBytes());
	    String pswCifrata = DatatypeConverter.printHexBinary(md.digest()).toUpperCase();
	    return pswCifrata;
	}
}