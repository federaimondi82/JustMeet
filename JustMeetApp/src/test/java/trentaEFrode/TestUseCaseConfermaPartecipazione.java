package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.mainElements.ConnectBackEnd;
import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.parsers.ParserEventi;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;

class TestUseCaseConfermaPartecipazione {

	
	//TODO IMPLEMENTARE ANCHE DIsdii partecipazione????
	@Test
	void testConfermaPartecipazione() {
		// Per semplicità scegliamo un evento casuale presente nel database.
		List<Evento> eventi = ParserEventi.getInstance().parseEventi(ConnectBackEnd.getInstance().restRequest("/eventi", "GET"));
		int idEventoScelto = eventi.get(new Random().nextInt(eventi.size())).id();
		UtenteRegistrato utente = UtenteRegistrato.getInstance().id(2);
		assertTrue(utente.partecipa(idEventoScelto));
	}

}
