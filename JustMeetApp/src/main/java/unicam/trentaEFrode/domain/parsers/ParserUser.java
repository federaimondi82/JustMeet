package unicam.trentaEFrode.domain.parsers;

import java.util.GregorianCalendar;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;

/*
 * Classe che si occupa di parsare gli utenti dal client al server e viceversa.
 * */
public class ParserUser {
	
	private static ParserUser instance;	
	
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
		String[] arr = value.split(":");
		UtenteRegistrato.getInstance().id(Integer.parseInt(arr[0])).
		nome(arr[1]).cognome(arr[2]).email(arr[3]).nickname(arr[4]).password(arr[5]).dataDiNascita(ParserData.getInstance().parsaData(arr[7])).citta(arr[8]).cap(arr[9]).provincia(arr[10]);
		return UtenteRegistrato.getInstance();
	}
	
	
	

	/**
	 * Spezza il valore di una stringa nel file di cache per comporre i dati del'utente registrato
	 * @param value
	 * @return L'unica istanza di utente resistarto, l'utente che sta usando l'applicazione lato client
	 */
	public void parseUtenteFromFile(String value){
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
