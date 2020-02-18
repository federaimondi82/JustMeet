package unicam.trentaEFrode.domain.mainElements;

import unicam.trentaEFrode.domain.users.UtenteRegistrato;

/**
 * Questa classe consente di modellizzare le categorie/interessi degli eventi;
 * potrebbe essere la categoria "sport" o "cucina" ecc...
 * Le categorie disponibili vengono caricare ad avvio dell'applicazione consultado il backend;
 *  è infatti in backend a dettare le categorie disponibili, questo per non sovraccarivare il
 *  database con categorie personalizzate
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
		return nome.equals(categoria.nome());
	}
}