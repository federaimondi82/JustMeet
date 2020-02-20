package unicam.trentaEFrode.domain.parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
 * Classe che si occupa di parsare i partecipanti dal client al server e viceversa.
 * */
public class ParserPartecipanti {
	private static ParserPartecipanti instance = null;

	public static ParserPartecipanti getInstance() {
		if (instance == null) instance = new ParserPartecipanti();
		return instance;
	}

	public List<String> parsePartecipantiFromServer(String partecipanti) {
		//id-nome-cognome,id-nome-cognome
		return partecipanti == "" | partecipanti == null ? new ArrayList<String>() : Arrays.asList(partecipanti.split(","));
	}
	
}
