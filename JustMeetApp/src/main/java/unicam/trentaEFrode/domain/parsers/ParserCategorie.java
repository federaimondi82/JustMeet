package unicam.trentaEFrode.domain.parsers;

import java.util.ArrayList;
import java.util.List;

import unicam.trentaEFrode.domain.mainElements.Categoria;

public class ParserCategorie {

	private static ParserCategorie instance;	
	private ParserCategorie() {

	}
	
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
		
		if(json.contains(":"))json=json.split(":")[1];
		if(json.contains("{"))json=json.replace("{", "");
		if(json.contains("}"))json=json.replace("}", "");
		if(json.contains("\""))json=json.replace("\"", "");
		
		List<Categoria> catList =new ArrayList<>();
		
		int j=json.split("_").length;		
		for(int i=0;i<j;i++) {
			String cat=json.split("_")[i];
			String[] data =cat.split("-");
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
