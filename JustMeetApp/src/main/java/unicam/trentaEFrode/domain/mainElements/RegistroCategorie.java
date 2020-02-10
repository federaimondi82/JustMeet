package unicam.trentaEFrode.domain.mainElements;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import unicam.trentaEFrode.domain.parsers.ParserUser;
import unicam.trentaEFrode.domain.parsers.ParserCategorie;
import unicam.trentaEFrode.exceptions.CategoriaInesistente;

/**
 * Le categorie scelte dall'utente per eventuali ricerche in base alle sue preferenze
 * 
 * @author trenta e Frode
 *
 */
public class RegistroCategorie {

	private static RegistroCategorie instance = null;
			
	private List<Categoria> categorie;
	private List<Categoria> interessi;
	
	public static RegistroCategorie getInstance() throws ConnectException {
		if(instance == null) instance = new RegistroCategorie();
		return instance;
	}
	
	private RegistroCategorie() throws ConnectException {
		this.categorie = caricaCategorie();
		this.interessi=new ArrayList<>();
	}
	
	/**
	 * Legge gli interessi dell'utente memorizzati nel file di cache
	 */
	public void carcaInteressi() {
		this.interessi=RegistroStatico.getInstance().leggiInteressi();
	}
	
	/**
	 * Restituisce una lista di interessi dell'utente
	 * @return
	 */
	public List<Categoria> getInteressi(){
		carcaInteressi();
		return this.interessi;
	}
	/**
	 * L'utente puo' aggiungere solo le categorie provenienti dal server
	 * @return la lista delle categorie possibili per eventuali scelte
	 * @throws ConnectException 
	 */
	public List<Categoria> caricaCategorie() throws ConnectException {
		String s=ConnectBackEnd.getInstance().restRequest("/cat/", "GET");
		return ParserCategorie.getInstance().parseCategorieFromServer(s);
	}
	
	public List<Categoria> categorie() {
		return categorie;
	}

	/**
	 * Restituisce la lista dei nomi delle categorie.
	 * */
	public List<String> listaNomiCategorie() {
		List<String> nomi = new ArrayList<>();
		for(Categoria c: categorie) nomi.add(c.toString());
		return nomi;
	}
	
	public Categoria getCategoria(String nome) throws CategoriaInesistente {
		for(Categoria c:categorie) if(c.nome()==nome) return c;
		throw new CategoriaInesistente();
	}
	
	
	/** Ritorna una istanza di Categoria in base al suo id
	 * @param id
	 * @return ritorna la categoria in base al suo id
	 * @throws CategoriaInesistente 
	 */
	public Categoria getCategoria(int id) throws CategoriaInesistente {
		Predicate<Categoria> pred=(ele)->ele.getId()==id;	
		Categoria h=null;
		try {
			h=this.categorie.stream().filter(pred).findFirst().get();
		}catch(IndexOutOfBoundsException | NoSuchElementException e) {throw new CategoriaInesistente();}
		return h;
	}
	

}