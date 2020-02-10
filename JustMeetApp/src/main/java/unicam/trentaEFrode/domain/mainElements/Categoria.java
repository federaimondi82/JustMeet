package unicam.trentaEFrode.domain.mainElements;

import unicam.trentaEFrode.domain.users.UtenteRegistrato;

/**
 * Rappresenta le possibili categorie di eventi o interessi dell'utente
 *
 *@see Evento
 *@see UtenteRegistrato
 */
public class Categoria {
	
	/**
	 * L'identificativo di questa categoria proveniente dal database
	 */
	private int id;
	
	/**
	 * Nome della categoria.
	 */
	private String nome;
	
	/**
	 * Descrizione della categoria.
	 */
	private String descrizione;

	public Categoria(int id,String nome, String descrizione) {
		this.id=id;
		this.nome = nome;
		this.descrizione = descrizione;
	}
	

	/**
	 * @return the name
	 */
	public String nome() {
		return nome;
	}

	/**
	 * @param nome the name to set
	 */
	public void cambiaNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the description
	 */
	public String descrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the description to set
	 */
	public void cambiaDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return id + ":" + nome + ":" + descrizione;
	}
	
	public boolean equals(Categoria categoria) {
		return nome == categoria.nome();
	}
}