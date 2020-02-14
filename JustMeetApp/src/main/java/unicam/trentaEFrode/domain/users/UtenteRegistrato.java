package unicam.trentaEFrode.domain.users;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import unicam.trentaEFrode.domain.mainElements.Categoria;
import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.mainElements.GestoreEventi;


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
	private Organizzatore organizzatore;
	private Partecipante partecipante;
	
	
	private static UtenteRegistrato utente;
	
	public static UtenteRegistrato getInstance() {
		if(utente==null) utente=new UtenteRegistrato();
		return utente;
	}
		
	private UtenteRegistrato() {	
		this.organizzatore = null;
		this.partecipante = null;
	}
	

	
	/*INIZIO semi builder*/

	public UtenteRegistrato id(int id) {
		this.id=id;
		return this;
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

	public Organizzatore getOrganizzatore() {
		return organizzatore;
	}

	public List<Integer> creaEvento(Evento evento) {
		List<Integer> risposta = GestoreEventi.getInstance().effettuaControlli(evento);
		if(risposta.get(0) == -1) { // se è andato tutto bene
			if(organizzatore == null) this.organizzatore = new Organizzatore();
			risposta.clear();
			risposta.add(this.organizzatore.aggiungiEvento(evento)?-1:0);
		}
		return risposta;
	}

	public boolean partecipa(int idEvento) {
		if(partecipante == null) partecipante = new Partecipante();
		else if(partecipante.esiste(idEvento)) return false;
		boolean risposta = GestoreEventi.getInstance().partecipa(idEvento, id);
		if(risposta) risposta = partecipante.partecipa(idEvento);
		return risposta;
	}
}