package unicam.trentaEFrode.domain.parsers;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import unicam.trentaEFrode.domain.mainElements.Categoria;
import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.mainElements.Luogo;

public class ParserEventi {

	private static ParserEventi instance = null;

	public static ParserEventi getInstance() {
		if (instance == null) instance = new ParserEventi();
		return instance;
	}

	public List<Evento> parseEventi(String newJson) {

		if (newJson == "" | newJson == "null" | newJson == null) return new ArrayList<>();

		String[] agenda = newJson.split(";");
		String[] evento;
		List<Evento> eventi = new ArrayList<Evento>();
		for (int i = 0; i < agenda.length; i++) {
			agenda[i] = agenda[i].substring(1, agenda[i].length() - 1);
			evento = agenda[i].split("-");			
			eventi.add(new Evento(evento[1], // nome
					new GregorianCalendar(Integer.parseInt(evento[2].split("/")[0]),
							Integer.parseInt(evento[2].split("/")[1]), Integer.parseInt(evento[2].split("/")[2]),
							Integer.parseInt(evento[3].split(":")[0]), Integer.parseInt(evento[3].split(":")[0])), // dataora
					Integer.parseInt(evento[4]), Integer.parseInt(evento[5]), // min max
					evento[6], // descr
					Integer.parseInt(evento[7]), // durata
					new Luogo(evento[9], evento[10], evento[11], evento[12], evento[13], evento[14]),
					new Categoria(Integer.parseInt(evento[15]), evento[16], evento[17]))
							.setId(Integer.parseInt(evento[0])).setOrganizzatore(Integer.parseInt(evento[8])));
		}
		return eventi;
	}
}
