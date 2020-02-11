package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import java.net.ConnectException;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.mainElements.ConnectBackEnd;
import unicam.trentaEFrode.domain.mainElements.GestoreRegistrazioni;
import unicam.trentaEFrode.domain.parsers.ParserUser;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;

class Parser_test {
	
	@Test
	public final void parserUser_dataFromServer() {
		String credenziali="3517@email.it:abc";
		String s="";
		try {
			s = ConnectBackEnd.getInstance().restRequest("/utenti/auth/"+credenziali, "GET");
				
			//parsing della stringa di ritorno,va a creare l'utente Registrato e i sui interessi
			ParserUser.getInstance().parseUtenteFromServer(s);
		} catch (ConnectException e) {
			e.printStackTrace();
		}
		assertEquals("Mario", UtenteRegistrato.getInstance().getNome());
		assertEquals(UtenteRegistrato.getInstance().getCognome(), "Rossi");
		assertTrue(UtenteRegistrato.getInstance().getDataDiNascita().equals(new GregorianCalendar(2000, 20, 20)));
	}

}
