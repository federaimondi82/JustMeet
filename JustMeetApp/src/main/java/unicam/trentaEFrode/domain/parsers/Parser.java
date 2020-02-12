package unicam.trentaEFrode.domain.parsers;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import unicam.trentaEFrode.domain.mainElements.Categoria;
import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;


public class Parser {
	
	private static Parser instance;	
	private Parser() {

	}
	
	public static Parser getInstance() {
		if(instance==null) instance=new Parser();
		return instance;
	}

	/**
	 * Spezza il json di ritorno dal server e costruisce l'utente registrato
	 * @param value
	 * @return L'unica istanza di utente resistarto, l'utente che sta usando l'applicazione lato client
	 */
	public UtenteRegistrato parseUtenteFromServer(String value){
		
		System.out.println(value);
		
		//eleminazione di alcuni caratteri inutili all'inizio,alla fine o dentro la stringa
		String s1=value.substring(1, value.length()-1);				
		String[] json=s1.replace("\"", "").split(",");		
		GregorianCalendar dataNascita=parsaDataDiNascita(json);
		String interessi=json[10].split(":")[1].substring(1, json[10].split(":")[1].length()-1);
		
		
		UtenteRegistrato.getInstance().id(Integer.parseInt(json[0].split(":")[1]))
			.nome(json[1].split(":")[1])
			.cognome(json[2].split(":")[1])
			.email(json[3].split(":")[1])
			.nickname(json[4].split(":")[1])
			.password(json[5].split(":")[1])
			.ripetiPassword(json[6].split(":")[1])
			.dataDiNascita(dataNascita)
			.citta(json[8].split(":")[1])
			.cap(json[9].split(":")[1])		
			.interessi(parseCategorieFromServer(interessi));
		
		
		return UtenteRegistrato.getInstance();
	}
	
	
	/**
	 * Consente di estrapolare la data di nascita dell'utente dal json proveninte dal server
	 * @param json
	 * @return
	 */
	private GregorianCalendar parsaDataDiNascita(String[] json) {
		//costruisce una istanza per la data di nascita
		int gg=Integer.parseInt(String.valueOf(json[7].split(":")[1].split("/")[0]));
		int mm=Integer.parseInt(json[7].split(":")[1].split("/")[1]);
		int aaaa=Integer.parseInt(json[7].split(":")[1].split("/")[2]);
		return new GregorianCalendar(aaaa, mm, gg);
	}

	/**
	 * Spezza il valore di una stringa nel file di cache per comporre i dati del'utente registrato
	 * @param value
	 * @return L'unica istanza di utente resistarto, l'utente che sta usando l'applicazione lato client
	 */
	public void parseUtenteFromFile(String value){
		//TODO modificare per gli interessi dell'utente	
		try {
			String[] user=value.split(",");
			
			//costruisce una istanza per la data di nascita
			int gg=Integer.parseInt(user[7].split("/")[0]);
			int mm=Integer.parseInt(user[7].split("/")[0]);
			int aaaa=Integer.parseInt(user[7].split("/")[0]);
			GregorianCalendar dataNascita=new GregorianCalendar(aaaa, mm, gg);
			
			
			UtenteRegistrato.getInstance().id(Integer.parseInt(user[0]))
			.nome(user[1])
			.cognome(user[2])
			.email(user[3])
			.nickname(user[4])
			.password(user[5])
			.ripetiPassword(user[6])
			.dataDiNascita(dataNascita)
			.citta(user[8])
			.cap(user[9]);
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	
	/**
	 * Parsa il json di ritorno dala server e lo trasforma in una List di Categorie
	 * @param json la stringa in arrivo dal server
	 * @return una lista di categorie
	 * @see List
	 * @see Categorie
	 */
	public List<Categoria> parseCategorieFromServer(String json) {
		
		//istanza per il return del metodo
		List<Categoria> catList =new ArrayList<>();
		
		//splitta la stringa restante in parti che diventeranno istanze di tipo Categoria
		for(String cat : json.split("_")) {
			//String newCat=cat.substring(1, cat.length()-1);
			String[] data =cat.split(":");
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
