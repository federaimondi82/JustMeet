package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.net.ConnectException;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.mainElements.Categoria;
import unicam.trentaEFrode.domain.mainElements.ConnectBackEnd;
import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.mainElements.GestoreEventi;
import unicam.trentaEFrode.domain.mainElements.Luogo;
import unicam.trentaEFrode.domain.parsers.Parser;
import unicam.trentaEFrode.domain.parsers.ParserEventi;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;

class TestUseCaseModificaEvento {

	@Test
	void testModificaEventoData() throws ConnectException {
		UtenteRegistrato organizzatore = UtenteRegistrato.getInstance().id(18);
		//Recupero gli eventi creati dall'utente
		String eventi = ConnectBackEnd.getInstance().restRequest("/eventi/utenti/" + organizzatore.getId(), "GET");
		System.out.println("eventi = " + eventi);
		List<Evento> lista = ParserEventi.getInstance().parseEventi(eventi);
		
		/*List<Evento> lista = Pars
		GregorianCalendar data = new GregorianCalendar();
		data.add(GregorianCalendar.MONTH, 2);
		Evento e = new Evento("Pranzo di beneficenza", data , 3, 100, "I soldi ricavati andranno in beneficenza", 3, new Luogo("Ristorante Casa Mia", "via della cucina", "15/A", "63100", "Ascoli Piceno", "AP"), new Categoria(4, "Cibo", "Per le persone più golose."));
		e = e.setOrganizzatore(organizzatore.getId());
		e.dataOra().add(GregorianCalendar.MONTH, 3);
		List<Integer> risposta = GestoreEventi.getInstance().effettuaControlli(e);
		assertEquals(1, risposta.size());
		assertEquals(new Integer(-1), risposta.get(0));
		String json = ConnectBackEnd.getInstance().restRequest("/eventi/"  + 11 , "GET");
		assertEquals(e, Parser.getInstance().parseEventi(json));*/
	}
}
