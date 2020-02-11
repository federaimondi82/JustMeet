package unicam.trentaEFrode.domain.parsers;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import unicam.trentaEFrode.domain.mainElements.Categoria;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;


public class ParserUser {
	
	private static ParserUser instance;	
	private ParserUser() {

	}
	
	public static ParserUser getInstance() {
		if(instance==null) instance=new ParserUser();
		return instance;
	}

	/**
	 * Spezza il json di ritorno dal server e costruisce l'utente registrato
	 * @param value
	 * @return L'unica istanza di utente resistarto, l'utente che sta usando l'applicazione lato client
	 */
	public UtenteRegistrato parseUtenteFromServer(String value){
		/**
		 * Ritorna una cosa tipo:
		 * 
		 *{"id":23,"nome":"Mario","cognome":"Rossi","email":"6133@email.it","nickname":"2964",
		 *"password":"abc","ripetiPassword":"abc","dataDiNascita":"20/20/2000","citta":"Roma",
		 *"cap":"6","interessi":"{1.sport.calcio_2.sport.pallavolo}"}
		 * */
		System.out.println(value);
		
		//eleminazione di alcuni caratteri inutili all'inizio,alla fine o dentro la stringa
		String s1=value.substring(1, value.length()-1);//elimina le parentesi di inizio e fine				
		//String[] json=s1.replace("\"", "").split(",");		
		String[] json=s1.split(",");
		
		System.out.println(s1);
		
		GregorianCalendar dataNascita=ParserData.getInstance().parsaDataDiNascita(json[7].split(":")[1]);
		List<Categoria> interessi=ParserCategorie.getInstance().parseCategorieFromServer(json[10]);	
		
		UtenteRegistrato.getInstance().id(Integer.parseInt(json[0].split(":")[1]))
			.nome(json[1].split(":")[1].substring(1, json[1].split(":")[1].length()-1))
			.cognome(json[2].split(":")[1].substring(1, json[2].split(":")[1].length()-1))
			.email(json[3].split(":")[1].substring(1, json[3].split(":")[1].length()-1))
			.nickname(json[4].split(":")[1].substring(1, json[4].split(":")[1].length()-1))
			.password(json[5].split(":")[1].substring(1, json[5].split(":")[1].length()-1))
			.ripetiPassword(json[6].split(":")[1].substring(1, json[6].split(":")[1].length()-1))
			.dataDiNascita(dataNascita)
			.citta(json[8].split(":")[1].substring(1, json[8].split(":")[1].length()-1))
			.cap(json[9].split(":")[1].substring(1, json[9].split(":")[1].length()-1))		
			.interessi(interessi);
		
		
		return UtenteRegistrato.getInstance();
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
	
	
	
	
}
