package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.users.UtenteRegistrato;

class TestUtenteRegistrato {

	/*
	 * L'utente con id 1 non ha organizzato nessun evento e non ha dato conferma di partecipazione ad 
	 * alcun evento.
	 * */
	@Test
	void testUtenteRegistrato() {
		UtenteRegistrato utente = UtenteRegistrato.getInstance().id(1);
		assertFalse(utente.isOrganizzatore());
		assertFalse(utente.isPartecipante());
	}

	/*
	 * L'utente con id 3 ha organizzato eventi, ma non ha dato conferma di partecipazione ad 
	 * alcun evento.
	 * */
	@Test
	void testOrganizzatore() {
		UtenteRegistrato utente = UtenteRegistrato.getInstance().id(3);
		assertTrue(utente.isOrganizzatore());
		assertFalse(utente.isPartecipante());
	}
	
	/*
	 * L'utente con id 4 non ha organizzato nessun evento, ma ha dato conferma di partecipazione a 
	 * qualche evento.
	 * */
	@Test
	void testPartecipante() {
		UtenteRegistrato utente = UtenteRegistrato.getInstance().id(4);
		assertFalse(utente.isOrganizzatore());
		assertTrue(utente.isPartecipante());
	}
	
	/*
	 * L'utente con id 2 ha organizzato eventi e ha dato conferma di partecipazione a 
	 * qualche evento.
	 * */
	@Test
	void testOrganizzatoreEPartecipante() {
		UtenteRegistrato utente = UtenteRegistrato.getInstance().id(2);
		assertTrue(utente.isOrganizzatore());
		assertTrue(utente.isPartecipante());
	}
}
