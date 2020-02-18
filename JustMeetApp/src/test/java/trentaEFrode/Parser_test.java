package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.net.ConnectException;

import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.mainElements.ConnectBackEnd;
import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.parsers.ParserEventi;
import unicam.trentaEFrode.domain.parsers.ParserUser;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;

class Parser_test {
	
	@Test
	public final void parserUser_dataFromServer() {
		String credenziali="5443@email.it:abc";
		String s = ConnectBackEnd.getInstance().restRequest("/utenti/auth/"+credenziali, "GET");
		ParserUser.getInstance().parseUtenteFromServer(s);
		assertEquals("Mario", UtenteRegistrato.getInstance().getNome());
		assertEquals("Rossi", UtenteRegistrato.getInstance().getCognome() );
	}
	
	@Test
	public void parserEventiFromServer() throws ConnectException {
		String jsonEventi = ConnectBackEnd.getInstance().restRequest("/eventi/utenti/2", "GET");
		List<Evento> lista = ParserEventi.getInstance().parseEventi(jsonEventi);
		assertTrue(lista.size()>0);
	}

}
