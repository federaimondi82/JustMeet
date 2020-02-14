package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.users.Organizzatore;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;

class TestUseCaseCancellaEvento {

	/*
	 * Assegnamo l'id all'istanza UtenteRegistrato.getInstance() per evitare l'autenticazione.
	 * */
	@Test
	void testCancellaEvento() {
		UtenteRegistrato utente = UtenteRegistrato.getInstance().id(2);
		Organizzatore organizzatore = utente.getOrganizzatore();
		//Step 1 :Recupero gli eventi creati dall'utente
		List<Evento> agenda = organizzatore.getAgenda().getEventi();
		//Step 2 : Scelgo un evento randomico da cancellare
		Evento eventoDaCancellare =	agenda.get(new Random().nextInt(agenda.size()));
		//Step 3 : Cancello l'evento
		boolean result = organizzatore.cancellaEvento(eventoDaCancellare.id());
		assertTrue(result);
		assertTrue(utente.creaEvento(eventoDaCancellare).get(0) == new Integer(-1));
	}
}
