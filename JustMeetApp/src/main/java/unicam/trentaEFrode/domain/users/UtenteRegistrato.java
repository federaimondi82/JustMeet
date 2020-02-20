package unicam.trentaEFrode.domain.users;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import unicam.trentaEFrode.domain.mainElements.Categoria;
import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.mainElements.GestoreEventi;
import unicam.trentaEFrode.domain.mainElements.RegistroRuoli;
import unicam.trentaEFrode.domain.mainElements.Registratore;


public class UtenteRegistrato implements Utente {

	private int id;	
	private String nome;
	private String cognome;
	private String email;
	private String nickname;
	private String password;
	private String ripetiPassword;
	private GregorianCalendar dataDiNascita;
	private String citta;
	private String cap;
	private String provincia;
	private List<Categoria> interessi;
	private Ruolo ruolo;
	
	
	private static UtenteRegistrato utente;
	
	public static UtenteRegistrato getInstance() {
		if(utente==null) utente = new UtenteRegistrato();
		return utente;
	}
		
	public UtenteRegistrato id(int id) {
		this.id=id;	
		return this;
	}

	/**
	 * Permette la creazione di un nuovo evento sul backend;
	 * 
	 * @param nome
	 * @param dataOra
	 * @param minPartecipanti
	 * @param maxPartecipanti
	 * @param descrizione
	 * @param durata
	 * @param nomeLuogo
	 * @param indirizzo
	 * @param numeroCivico
	 * @param cap
	 * @param citta
	 * @param provincia
	 * @param categoria
	 * @return una lista di errori
	 */
	public List<Integer> creaEvento(
			String nome, 
			GregorianCalendar dataOra, 
			Integer minPartecipanti, 
			Integer maxPartecipanti, 
			String descrizione, 
			Integer durata, 
			String nomeLuogo, 
			String indirizzo, 
			String numeroCivico, 
			String cap, 
			String citta, 
			String provincia,		
			String categoria
			) {
		Evento evento = new Evento(nome, dataOra, minPartecipanti, maxPartecipanti, descrizione, durata, nomeLuogo, indirizzo, numeroCivico, cap, citta, provincia, categoria);
		evento.setOrganizzatore(this.id);
		return creaEvento(evento);	
	}

	
	/**
	 * Passa l'evento appena creato al gestore eventi per effettuare i controlli.
	 * @param evento : l'evento creato da controllare
	 * @return la lista di interi corrispondenti agli errori riscontrati.
	 */
	public List<Integer> creaEvento(Evento evento) {
		return GestoreEventi.getInstance().effettuaControlli(evento);
	}
	
	/**
	 * Avvia la partecipazione all'evento con id idEvento
	 * @param idEvento : l'evento da partecipare
	 * @return se l'operazione è andata a buon fine.
	 */
	public boolean partecipa(int idEvento) {
		return Registratore.getInstance().registraPartecipazione(idEvento, id);
	}

	/**
	 * Avvia la ricerca con i parametri passati.
	 * @param primoAccesso : se la ricerca è partita dal sistema all'avvio dell'applicazione (la ricerca 
	 * si baserà sui valori di default).
	 * @param parola : la parola chiave inserita dall'utente.
	 * @param categoria : la categoria inserita dall'utente.
	 * @param citta : la citta' inserita dall'utente.
	 * @param provincia : la provincia inserita dall'utente.
	 * @param inizio : l'inizio del range temporale inserito dall'utente.
	 * @param fine : la fine del range temporale inserito dall'utente.
	 * @return la lista degli eventi trovati.
	 */
	public List<Evento> cerca(boolean primoAccesso, String parola, String categoria, String citta, 
			String provincia, GregorianCalendar inizio, GregorianCalendar fine) {
			if(inizio == null) inizio = new GregorianCalendar();
			if(fine == null) {
				fine = new GregorianCalendar();
				fine.add(Calendar.DAY_OF_MONTH, 7);
			} 
		return primoAccesso?
				GestoreEventi.getInstance().cerca("null", "null", "null", "null", inizio, fine, this.id):
				GestoreEventi.getInstance().cerca(parola, categoria, citta, provincia, inizio, fine, this.id);
	}

	
	/**
	 * Ritorna true se l'utente ha organizzato almeno un evento, false altrimenti.
	 * @return true se l'utente ha organizzato almeno un evento, false altrimenti.
	 */
	public boolean isOrganizzatore() {
		return !this.setRuolo(1).getEventi().isEmpty();
	}

	/**
	 * Ritorna true se l'utente ha confermato la partecipazione ad almeno un evento, false altrimenti.
	 * @return true se l'utente ha confermato la partecipazione ad almeno un evento, false altrimenti.
	 */
	public boolean isPartecipante() {
		return !this.setRuolo(2).getEventi().isEmpty();
	}
	
	public UtenteRegistrato nome(String nome) {
		this.nome=nome;
		return this;
	}
	public UtenteRegistrato cognome(String cognome) {
		this.cognome=cognome;
		return this;
	}
	public UtenteRegistrato email(String email) {
		this.email=email;
		return this;
	}
	public UtenteRegistrato nickname(String nickname) {
		this.nickname=nickname;
		return this;
	}
	public UtenteRegistrato password(String password) {
		this.password=password;
		return this;
	}
	public UtenteRegistrato ripetiPassword(String ripetiPassword) {
		this.ripetiPassword=ripetiPassword;
		return this;
	}
	public UtenteRegistrato dataDiNascita(GregorianCalendar dataDiNascita) {
		this.dataDiNascita=dataDiNascita;
		return this;
	}
	public UtenteRegistrato citta(String citta) {
		this.citta=citta;
		return this;
	}
	public UtenteRegistrato cap(String cap) {
		this.cap=cap;
		return this;
	}
	public UtenteRegistrato interessi(List<Categoria> list) {
		this.interessi=list;
		return this;
	}
	
	public UtenteRegistrato provincia(String provincia) {
		this.provincia = provincia;
		return this;
	}
	/*FINE semi builder*/


	
	public List<Categoria> getMieCategorie() {
		return this.interessi;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the ripetiPassword
	 */
	public String getRipetiPassword() {
		return ripetiPassword;
	}

	/**
	 * @return the dataDiNascita
	 */
	public GregorianCalendar getDataDiNascita() {
		return dataDiNascita;
	}

	/**
	 * @return the citta
	 */
	public String getCitta() {
		return citta;
	}

	/**
	 * @return the cap
	 */
	public String getCap() {
		return cap;
	}
	
	
	@Override
	public void inviaFeedback() {
		// TODO Auto-generated method stub
		
	}
	
	private String getDataFormattata() {
		String s="";
		s+=this.dataDiNascita.get(Calendar.YEAR)+"/";
		s+=this.dataDiNascita.get(Calendar.MONTH)+"/";
		s+=this.dataDiNascita.get(Calendar.DAY_OF_MONTH);
		return s;
	}
	
	@Override
	public String toString() {
		return id + "," + nome + "," + cognome + "," + email
				+ "," + nickname + "," + password + "," + ripetiPassword
				+ "," + getDataFormattata() + "," + citta + "," + cap;
	}
	
	public String toStringInteressi() {
		String interessi="";
		for(Categoria c : getMieCategorie()) {
			interessi+=c.toString()+"_";
		}
		return interessi=interessi.substring(0, interessi.length()-1);
	}

	public String getProvincia() {
		return provincia;
	}

	public Ruolo getRuolo() {
		return this.ruolo;
	}
	
	public Ruolo setRuolo(int idRuolo) {
		this.ruolo = RegistroRuoli.getInstance().getRuolo(idRuolo);	
		return ruolo;
	}

}