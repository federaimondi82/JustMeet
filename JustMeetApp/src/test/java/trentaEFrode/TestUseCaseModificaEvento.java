package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Random;
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
		UtenteRegistrato organizzatore = UtenteRegistrato.getInstance().id(2);
		//Step 1 :Recupero gli eventi creati dall'utente
		String eventi = ConnectBackEnd.getInstance().restRequest("/eventi/utenti/" + organizzatore.getId(), "GET");
		System.out.println("eventi = " + eventi);
		List<Evento> lista = ParserEventi.getInstance().parseEventi(eventi);
		//Step 2 : scelgo un evento randomico
		Evento e = lista.get(new Random().nextInt(lista.size()));
		//Step 3 : creo una nuova data e la imposto come data dell'evento scelto
		GregorianCalendar nuovaData = new GregorianCalendar();
		nuovaData.add(GregorianCalendar.MONTH, 2);
		e.cambiaDataOra(nuovaData);
		//Step 4: confermo le modifiche
		//TODO Partire da UtenteRegistrato
		List<Integer> risposta = GestoreEventi.getInstance().effettuaControlli(e);
		assertEquals(1, risposta.size());
		assertEquals(new Integer(-1), risposta.get(0));
	}
}
