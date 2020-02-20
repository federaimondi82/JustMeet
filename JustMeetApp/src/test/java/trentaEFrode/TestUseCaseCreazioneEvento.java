package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import java.net.ConnectException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.users.UtenteRegistrato;

class TestUseCaseCreazioneEvento {

	@Test
	void testEventoValido() throws ConnectException {
		UtenteRegistrato utente = UtenteRegistrato.getInstance().id(2);
		GregorianCalendar data = new GregorianCalendar();
		data.add(GregorianCalendar.DAY_OF_MONTH, new Random().nextInt(29));
		data.add(GregorianCalendar.MONTH, new Random().nextInt(13));
		data.add(GregorianCalendar.HOUR, new Random().nextInt(24));
		data.add(GregorianCalendar.MINUTE, new Random().nextInt(60));
		List<Integer> risposta = utente.creaEvento("Pranzo di beneficenza", data, 3, 100, "I soldi ricavati andranno in beneficenza", 3, "Ristorante Casa Mia", "via della cucina", String.valueOf((int)Math.random()*10000), "63100", "Ascoli Piceno", "AP", "cibo");
		assertEquals(1, risposta.size());
		assertEquals(new Integer(-1), risposta.get(0));
	}
}
