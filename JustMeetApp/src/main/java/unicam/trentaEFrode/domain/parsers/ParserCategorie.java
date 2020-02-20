package unicam.trentaEFrode.domain.parsers;

import java.util.ArrayList;
import java.util.List;

import unicam.trentaEFrode.domain.mainElements.Categoria;

/*
 * Classe che si occupa di parsare le categorie dal client al server e viceversa.
 * */
public class ParserCategorie {

	private static ParserCategorie instance;	
	
	public static ParserCategorie getInstance() {
		if(instance==null) instance=new ParserCategorie();
		return instance;
	}
	
	/**
	 * Parsa il json di ritorno dala server e lo trasforma in una List di Categorie
	 * @param json la stringa in arrivo dal server
	 * @return una lista di categorie
	 * @see List
	 * @see Categorie
	 */
	public List<Categoria> parseCategorieFromServer(String json) {

		List<Categoria> catList =new ArrayList<>();
		
		String[] categorie = json.split("_");
		for(int i=0;i<categorie.length;i++) {
			String[] data =categorie[i].split("-");
			catList.add(new Categoria(Integer.parseInt(data[0]), data[1], data[2]));
		}
		return catList;
	}
	
	/**
	 * Trasforma la lista di categorie in una stringa da inviare al server
	 * @param categorie una lista di categorie , esempio gli interessi dell'utente
	 * @return una stringa usata per inviare i dati al server
	 */
	public String parseCategorieToServer(List<Categoria> categorie) {
		
		String s="";
		
		for(Categoria cat : categorie) {
			s+=cat.getId()+"_";
		}
		return s;
	}
}