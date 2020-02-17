package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.users.Organizzatore;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;

class TestUseCaseCancellaEvento {

	/**
	 * Assegnamo l'id all'istanza UtenteRegistrato.getInstance() per evitare l'autenticazione.
	 * Per evitare la cancellazione di ogni evento dell'utente, come conseguenza della 
	 * molteplice esecuzione di questo test, l'evento cancellato viene reinserito.
	 * */
	@Test
	void testCancellaEvento() {
		UtenteRegistrato utente = UtenteRegistrato.getInstance().id(2);
		Organizzatore organizzatore = utente.getOrganizzatore();
		//Step 1 :Recupero gli eventi creati dall'utente
		List<Evento> agenda = organizzatore.getAgenda().getEventi();
		assertFalse(agenda.isEmpty());
		//Step 2 : Scelgo un evento randomico da cancellare
		Evento eventoDaCancellare =	agenda.get(new Random().nextInt(agenda.size()));
		//Step 3 : Cancello l'evento
		boolean result = organizzatore.cancellaEvento(eventoDaCancellare.id());
		assertTrue(result);
		// Step 4 : Reinserisco l'evento cancellato.		 
		assertTrue(utente.creaEvento(eventoDaCancellare).get(0) == -1);
	}
}
