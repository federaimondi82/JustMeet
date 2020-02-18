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
	public GregorianCalendar parsaData(String json) {
		String[] arr = json.split("-");
		int gg = Integer.parseInt(arr[2]);
		int mm = Integer.parseInt(arr[1]);
		int aaaa=Integer.parseInt(arr[0]);
		return new GregorianCalendar(aaaa, mm, gg);
	}
	
	
	

}
