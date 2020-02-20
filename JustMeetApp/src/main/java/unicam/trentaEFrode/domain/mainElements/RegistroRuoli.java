package unicam.trentaEFrode.domain.mainElements;

import java.util.Map;
import java.util.TreeMap;

import unicam.trentaEFrode.domain.users.Organizzatore;
import unicam.trentaEFrode.domain.users.Partecipante;
import unicam.trentaEFrode.domain.users.Ruolo;

public class RegistroRuoli {

	private static RegistroRuoli instance;

	Map<Integer, Ruolo> registro;

	public static RegistroRuoli getInstance() {
		if(instance ==  null) instance = new RegistroRuoli();
		return instance;
	}
	
	private RegistroRuoli() {
		registro = new TreeMap<Integer, Ruolo>();
		registro.put(1, new Organizzatore());
		registro.put(2, new Partecipante());
	}

	public Ruolo getRuolo(int idRuolo) {
		return registro.get(idRuolo);
	}
	
	
	
}
