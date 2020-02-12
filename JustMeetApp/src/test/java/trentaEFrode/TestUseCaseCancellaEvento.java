package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.users.UtenteRegistrato;

class TestUseCaseCancellaEvento {

	/*
	 * Precondizione: l'utente si è autenticato
	 * */
	void testCancellaEvento() throws Exception {
		UtenteRegistrato utente = UtenteRegistrato.getInstance().id(18);
		List<Evento> eventi = utente.organizzatore().eventi();
		boolean result = utente.organizzatore().cancellaEvento(10);
		assertTrue(result);
	}
	

}
