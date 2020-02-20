package unicam.trentaEFrode.domain.mainElements;

/**
 * Luogo dove vengono svolti gli eventi caricati dagli utenti
 */
public class Luogo {
	
	private int id;
	/**
	 * Nome della citta del luogo.
	 */
	private String citta;
	/**
	 * Indirizzo del luogo.
	 */
	private String indirizzo;
	/**
	 * E' una stringa perche' potrebbero esserci caratteri alfanumerici nel numero civico
	 */
	private String numeroCivico;
	/**
	 * E' una stringa perche' CAP che iniziano con 0 potrebbero non essere letti
	 */
	private String cap;
	/**
	 * Il nome del luogo dove si svolge l'evento. Ad esempio il nome di un teatro, del campo da gioco ecc...
	 */
	private String nome;
	/**
	 * Sigla della provincia
	 */
	private String provincia;

	public Luogo(
			int id,
			String nome, 
			String indirizzo, 
			String numeroCivico, 
			String cap, 
			String citta, 
			String provincia			
			) {	
		this(nome, indirizzo, numeroCivico, cap, citta,  provincia);
		this.id = id;
	}

	/**
	 * Crea un luogo senza specificare il nome del luogo
	 * */
	public Luogo(
			String nome, 
			String indirizzo, 
			String numeroCivico, 
			String cap, 
			String citta, 
			String provincia			
			) {
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.numeroCivico = numeroCivico;
		this.cap = cap;
		this.citta = citta;
		this.provincia = provincia;
	}

	/**
	 * Crea un luogo senza specificare il nome del luogo
	 * */
	public Luogo(
			String indirizzo, 
			String numeroCivico, 
			String cap, 
			String citta, 
			String provincia			
			) {
		this.nome = "";
		this.indirizzo = indirizzo;
		this.numeroCivico = numeroCivico;
		this.cap = cap;
		this.citta = citta;
		this.provincia = provincia;
	}
	/**
	 * @return the city
	 */
	public String citta() {
		return citta;
	}

	/**
	 * @param citta the city to set
	 */
	public void cambiacitta(String citta, String cap) {
		this.citta = citta;
		this.cap = cap;
	}

	/**
	 * @return the address
	 */
	public String indirizzo() {
		return indirizzo;
	}

	/**
	 * @param indirizzo the address to set
	 */
	public void cambiaIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * @return the number
	 */
	public String numeroCivico() {
		return numeroCivico;
	}

	/**
	 * @param numeroCivico the number to set
	 */
	public void cambiaNumeroCivico(String numeroCivico) {
		this.numeroCivico = numeroCivico;
	}

	/**
	 * @return the cap
	 */
	public String cap() {
		return cap;
	}

	/**
	 * @return the placeName
	 */
	public String nome() {
		return nome;
	}

	/**
	 * @param nome the placeName to set
	 */
	public void cambiaNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the province
	 */
	public String provincia() {
		return provincia;
	}

	/**
	 * @param provincia the province to set
	 */
	public void cambiaProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	@Override
	public String toString() {
		return nome.replace(" ", "_") + ":" + indirizzo.replace(" ", "_") + ":" + numeroCivico + ":" + cap+ ":" + citta.replace(" ", "_") + ":" + provincia;
	}
	
	
	/**
	 * Confronta l'attuale luogo con quello passato.
	 * @param luogo : l'istanza del luogo da confrontare.
	 * @return true se i due luoghi hanno gli stessi attributi, false altrimenti.
	 */
	public boolean equals(Luogo luogo) {
		return
				this.nome.equals(luogo.nome()) &&
				this.indirizzo.equals(luogo.indirizzo()) &&
				this.numeroCivico.equals(luogo.numeroCivico()) &&
				this.cap.equals(luogo.cap()) &&
				this.citta.equals(luogo.citta()) &&
				this.provincia.equals(luogo.provincia());		
	}
	
	public String stringaLuogo() {
		return nome + " " + indirizzo + " " + numeroCivico + " " + cap + " " + citta + " " + provincia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}