package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.users.Ruolo;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;

class TestUseCaseDisdiciPartecipazione {

	@Test
	void testDisdiciPartecipazione() {
		// Step 1 : il partecipante sceglie un evento dalla lista degli eventi a cui partecipa
		UtenteRegistrato utente = UtenteRegistrato.getInstance().id(2);
		Ruolo partecipante = utente.setRuolo(2);
		List<Evento> eventi1 = new ArrayList<Evento>();
		eventi1.addAll(partecipante.getEventi());
		int size1 = eventi1.size();
		Evento evento = eventi1.get(new Random().nextInt(size1));
		// Step 2 : il partecipante disdice la partecipazione all'evento scelto
		assertTrue(partecipante.cancellaEvento(evento.id()));
		List<Evento> eventi2 = new ArrayList<Evento>();
		eventi2.addAll(partecipante.getEventi());
		int size2 = eventi2.size();
		assertTrue(size1 > size2);
		// Step 3: per correttezza, confermo la partecipazione all'evento scelto
		assertTrue(utente.partecipa(evento.id()));
		List<Evento> eventi3 = new ArrayList<Evento>();
		eventi3.addAll(partecipante.getEventi());
		assertTrue(size1 == eventi3.size());
		assertTrue(eventi1.equals(eventi3));
	}

}
