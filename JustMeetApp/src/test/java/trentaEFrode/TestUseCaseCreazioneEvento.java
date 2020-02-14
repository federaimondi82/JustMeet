package trentaEFrode;

//import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

import java.net.ConnectException;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.mainElements.Categoria;
import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.mainElements.GestoreEventi;
import unicam.trentaEFrode.domain.mainElements.Luogo;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;

class TestUseCaseCreazioneEvento {

	@Test
	void testEventoValido() throws ConnectException {
		UtenteRegistrato utente = UtenteRegistrato.getInstance().id(2);
		GregorianCalendar data = new GregorianCalendar();
		data.add(GregorianCalendar.MONTH, 2);
		Evento e = new Evento("Pranzo di beneficenza", data, 3, 100, "I soldi ricavati andranno in beneficenza", 3, new Luogo("Ristorante Casa Mia", "via della cucina", String.valueOf((int)(Math.random()*10000)), "63100", "Ascoli Piceno", "AP"), new Categoria(4, "Cibo", "Per le persone piu golose."));
		e = e.setOrganizzatore(utente.getId());
		List<Integer> risposta = utente.creaEvento(e);
		assertEquals(1, risposta.size());
		assertEquals(new Integer(-1), risposta.get(0));
		assertNotNull(utente.getOrganizzatore());
	}
}
