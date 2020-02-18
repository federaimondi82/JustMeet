package unicam.trentaEFrode.domain.mainElements;

import java.time.LocalDate;

/**
 * Oggetto indispensabile per la registrazione; viene passato al Registratore che ne effettua i controlli; oggetto che consente all'utente non registrato di inserire i propri dati.
 */
public class DocuDiRegis {

	private String nome;
	private String cognome;
	private String email;
	private String nickname;
	private String password;
	private String ripetiPassword;
	private LocalDate dataDiNascita;
	private String cap;
	private String citta;
	private String provincia;
	private String interessi;

	/**
	 * Costruttore con parametri
	 */
	public DocuDiRegis(String nome, String cognome, String email,String nickname, String password, String ripetiPassword,
			LocalDate dataDiNascita, String cap, String citta, String provincia) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.ripetiPassword = ripetiPassword;
		this.dataDiNascita = dataDiNascita;
		this.cap = cap;
		this.citta = citta;
		this.provincia = provincia;
	}

	/**
	 * Costruttore per il semibuilder
	 */
	public DocuDiRegis(){}

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
	 * @return the nickName
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @return the Password
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
	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	/**
	 * @return the cap
	 */
	public String getCap() {
		return cap;
	}

	/**
	 * @return the citta
	 */
	public String getcitta() {
		return citta;
	}
	
	public String getInteressi(){
		return this.interessi;
	}

	public DocuDiRegis setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public DocuDiRegis setCognome(String cognome) {
		this.cognome = cognome;
		return this;
	}

	public DocuDiRegis setEmail(String email) {
		this.email = email;
		return this;
	}

	public DocuDiRegis setPassword(String password) {
		this.password = password;
		return this;
	}

	public DocuDiRegis setRipetiPassword(String ripetiPassword) {
		this.ripetiPassword = ripetiPassword;
		return this;
	}

	public DocuDiRegis setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
		return this;
	}

	public DocuDiRegis setCap(String cap) {
		this.cap = cap;
		return this;
	}

	public DocuDiRegis setCitta(String citta) {
		this.citta = citta;
		return this;
	}

	public DocuDiRegis setNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}
	
	public DocuDiRegis setInteressi(String interessi) {
		this.interessi=interessi;
		return this;
	}
	
	@Override
	public String toString() {
		return "" + nome + ":" + cognome + ":" + email + ":" + nickname
				+ ":" + password + ":" + ripetiPassword + ":" + dataDiNascita.toString()
				+ ":" + cap + ":" + citta+":" + provincia + ":" +interessi+"";
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
}