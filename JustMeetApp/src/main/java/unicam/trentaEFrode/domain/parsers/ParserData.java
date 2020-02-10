package unicam.trentaEFrode.domain.parsers;

import java.util.GregorianCalendar;

public class ParserData {
	
	private static ParserData instance;	
	private ParserData() {

	}
	
	public static ParserData getInstance() {
		if(instance==null) instance=new ParserData();
		return instance;
	}
	
	
	/**
	 * Consente di estrapolare la data di nascita dell'utente dal json proveninte dal server
	 * @param json
	 * @return
	 */
	public GregorianCalendar parsaDataDiNascita(String json) {
		//costruisce una istanza per la data di nascita
		json=json.substring(1, json.length()-1);
		int gg=Integer.parseInt(String.valueOf(json.split("/")[0]));
		int mm=Integer.parseInt(json.split("/")[1]);
		int aaaa=Integer.parseInt(json.split("/")[2]);
		return new GregorianCalendar(aaaa, mm, gg);
	}
	
	
	

}
