package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.users.Ruolo;
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
		Ruolo organizzatore = utente.setRuolo(1);
		
		// Step 1 :Recupero degli eventi creati dall'utente
		List<Evento> agenda = organizzatore.getEventi();
		assertFalse(agenda.isEmpty());
		
		// Step 2 : Scelta di un evento randomico da cancellare
		Evento eventoDaCancellare =	agenda.get(new Random().nextInt(agenda.size()));
		
		// Step 3 : Cancellare l'evento
		boolean result = organizzatore.cancellaEvento(eventoDaCancellare.id());
		assertTrue(result);
		
		// Step 4 : Reinserimento l'evento cancellato.		
		eventoDaCancellare.setId(-1);
		assertTrue(utente.creaEvento(eventoDaCancellare).get(0) == -1);
	}
}
