package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;

class TestUseCaseConfermaPartecipazione {

	@Test
	void testConfermaPartecipazione() {
		// Step 1 : l'utente effettua una ricerca (per semplicità vengono inseriti i parametri di default)
		UtenteRegistrato utente = UtenteRegistrato.getInstance().id(2);
		List<Evento> eventi = utente.cerca(true, "null", "null", "null", "null", null, null);
		// Step 2 : l'utente sceglie l'evento a cui partecipare
		int idEventoScelto = eventi.get(new Random().nextInt(eventi.size())).id();
		assertTrue(utente.partecipa(idEventoScelto));
		// Step 3 : Per correttezza, disdico la partecipazione all'evento scelto.
		assertTrue(utente.getPartecipante().disdiciPartecipazione(idEventoScelto));
	}
}