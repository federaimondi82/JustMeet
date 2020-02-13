package unicam.trentaEFrode.domain.mainElements;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Evento {
	
	/**
	 * L'id dell'organizzatore
	 * */
	private int organizzatore;
	
	private int id;

	/**
	 * Titolo dell'evento da creare
	 */
	private String nome;
	
	/**
	 * La data e ora di inizio dell'evento organizzato.
	 */
	private GregorianCalendar dataOra;
	
	/**
	 * Numero minimo di partecipanti per cui l'evento abbia luogo.
	 */
	private Integer minPartecipanti;
	
	/**
	 * Numero massimo di partecipanti ammessi.
	 */
	private Integer maxPartecipanti;

	/**
	 * Descrizione dell'evento, che pu� specificare eventuali dettagli.
	 */
	private String descrizione;
	
	/**
	 * Durata, espressa in ore, dell'evento.
	 */
	private Integer durata;

	/**
	 * Il luogo dove si svolgera' l'evento
	 */
	private Luogo luogo;
	
	/**
	 * La categoria dell'evento
	 */
	private Categoria categoria;
	
	/**
	 * 	Lista id dei partecipanti
	 * */
	List<Integer> partecipanti;

	private boolean confermato;

	/**
	 * Crea un evento vuoto.
	 * */
	public Evento() {
		this.id = -1;
		this.nome = "";
		this.dataOra = null;
		this.minPartecipanti = 0;
		this.maxPartecipanti = 0;
		this.descrizione = "";
		this.durata = 0;
		this.luogo = null;
		this.categoria = null;
		this.partecipanti = new ArrayList<>();
	}

	/**
	 * Crea l'evento con i parametri specificati 
	 */
	public Evento(
			String nome, 
			GregorianCalendar dataOra, 
			Integer minPartecipanti, 
			Integer maxPartecipanti, 
			String descrizione, 
			Integer durata, 
			Luogo luogo, 
			Categoria categoria
			) {
		this.id = -1;
		this.nome = nome;
		this.dataOra = dataOra;
		this.minPartecipanti = minPartecipanti;
		this.maxPartecipanti = maxPartecipanti;
		this.descrizione = descrizione;
		this.durata = durata;
		this.luogo = luogo;
		this.categoria = categoria;
		this.partecipanti = new ArrayList<>();
	}

	/**
	 * Construttore con un parametro iniziale
	 * */
	public Evento(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int id() {
		return id;
	}

	/**
	 * @return il nome dell'evento
	 */
	public String nome() {
		return nome;
	}

	/**
	 * @return data e ora dell'evento
	 */
	public GregorianCalendar dataOra() {
		return this.dataOra;
	}

	public void cambiaDataOra(GregorianCalendar dataOra) {
		this.dataOra = dataOra;	
	}
	
	/**
	 * @return il numero minimo di partecipanti
	 */
	public Integer minPartecipanti() {
		return minPartecipanti;
	}
	
	/**
	 * @return il numero minimo di partecipanti o la stringa "Non specificato" se l'organizzatore non ha 
	 * inserito il numero minimo di partecipanti.
	 */
	public String minPartecipantiStringa() {
		return minPartecipanti==0? ("" + minPartecipanti + ""):"Non specificato";
	}
	
	/**
	 * @return the maxPartecipants
	 */
	public Integer maxPartecipanti() {
		return maxPartecipanti;
	}

	/**
	 * @return il numero minimo di partecipanti o la stringa "Non specificato" se l'organizzatore non ha 
	 * inserito il numero minimo di partecipanti.
	 */
	public String maxPartecipantiStringa() {
		return maxPartecipanti==0? ("" + maxPartecipanti + ""):"Non specificato";
	}
	
	/**
	 * @return the descrizione
	 */
	public String descrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void cambiaDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return the durata
	 */
	public Integer durata() {
		return durata;
	}

	/**
	 * @return the organizzatore
	 */
	public int getOrganizzatore() {
		return organizzatore;
	}

	/**
	 * @return the confermato
	 */
	public boolean isConfermato() {
		return confermato;
	}

	/**
	 * @return the category
	 */
	public Categoria categoria() {
		return categoria;
	}

	/**
	 * @return the place
	 */
	public Luogo luogo() {
		return luogo;
	}

	/**
	 * @param maxPartecipanti the maxPartecipants to set
	 */
	public void cambiaMaxPartecipanti(Integer maxPartecipanti) {
		if(this.minPartecipanti==0 || this.minPartecipanti < maxPartecipanti) this.maxPartecipanti = maxPartecipanti;
	}

	/**
	 * Cambia il numero minimo dei partecipanti ammessi all'evento.
	 * @param minPartecipanti: il nuovo valore del numero minimo dei partecipanti ammessi all'evento.
	 */
	public void cambiaMinPartecipanti(Integer minPartecipanti) {
		if(this.maxPartecipanti == 0 ||minPartecipanti < this.maxPartecipanti) this.minPartecipanti = minPartecipanti;
	}

	public Evento setOrganizzatore(int organizzatore) {
		this.organizzatore = organizzatore;
		return this;
	}

	public Evento setId(int id) {
		this.id = id;
		return this;
	}

	public Evento setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public Evento setDataOra(GregorianCalendar dataOra) {
		this.dataOra = dataOra;
		return this;
	}

	public Evento setMinPartecipanti(Integer minPartecipanti) {
		this.minPartecipanti = minPartecipanti;
		return this;
	}

	public Evento setMaxPartecipanti(Integer maxPartecipanti) {
		this.maxPartecipanti = maxPartecipanti;
		return this;
	}

	public Evento setDescrizione(String descrizione) {
		this.descrizione = descrizione;
		return this;
	}

	public Evento setDurata(Integer durata) {
		this.durata = durata;
		return this;
	}

	public Evento setLuogo(Luogo luogo) {
		this.luogo = luogo;
		return this;
	}

	public Evento setCategoria(Categoria categoria) {
		this.categoria = categoria;
		return this;
	}

	public Evento setPartecipanti(List<Integer> partecipanti) {
		this.partecipanti = partecipanti;
		return this;
	}

	public Evento setConfermato(boolean confermato) {
		this.confermato = confermato;
		return this;
	}

	@Override
	public String toString() {
		int aaaa=dataOra.get(Calendar.YEAR);
		int mm=dataOra.get(Calendar.MONTH);
		int gg=dataOra.get(Calendar.DAY_OF_MONTH);
		int HH=dataOra.get(Calendar.HOUR_OF_DAY);
		int MM=dataOra.get(Calendar.MINUTE);
		String data=aaaa+":"+mm+":"+gg+":"+HH+":"+MM;
		
		/*
		 * {id}:{nome}:{aaaa}:{mm}:{gg}:{HH}:{MM}:{min}:"
			+ "{max}:{descr}:{durata}:{nomeLuogo}:{citta}:{indirizzo}:{civico}:"
			+ "{cap}:{prov}:{idCat}:{idUtente}"
		 * */
		
		return id + ":" + nome.replace(" ", "_") + ":" + data + ":" + minPartecipanti
				+ ":" + maxPartecipanti + ":" + descrizione.replace(" ", "_") + ":" + durata
				+ ":" + luogo.toString() + ":" +categoria.getId() + ":" + organizzatore;
	}	
	
	public boolean equals(Evento evento) {
		return
			this.id == evento.id() &&
			this.nome.equals(evento.nome()) &&
			this.dataOra.equals(evento.dataOra()) &&
			this.minPartecipanti == evento.minPartecipanti() &&
			this.maxPartecipanti == evento.maxPartecipanti() &&
			this.descrizione.equals(evento.descrizione()) &&
			this.durata == durata() &&
			this.luogo.equals(evento.luogo()) &&
			this.categoria.equals(evento.categoria())
			? true:false;
	}

	public String durataStringa() {
		return durata == 0? "Non specificata": durata.toString();
	}

	public String stringaCompleta() {
		
		return 
			"Nome:" + nome + "\n" + 
			"Categoria: " + categoria + "\n" + 
			"Dove: " + luogo.stringaLuogo() + "\n" + 
			"Quando: " + dataOra.toString() + "\n" + 
			"Durata stimata: " + durataStringa() + "\n" + 
			"Descrizione: " + descrizione + "\n" + 
			"Numero minimo di partecipanti: " + minPartecipantiStringa() + "\n" + 
			"Numero massimo di partecipanti: " + maxPartecipantiStringa() + "\n" + 
			"Numero partecipanti attuali: " + partecipanti.size();
	}
}