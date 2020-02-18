package trentaEFrode;


import static org.junit.jupiter.api.Assertions.*;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.mainElements.GestoreEventi;
import unicam.trentaEFrode.domain.mainElements.Luogo;


class TestGestoreEventi {

	GestoreEventi gest = GestoreEventi.getInstance();
	
	@Test
	void testEffettuaControlliEventoVuoto() throws ConnectException {
		Evento e = new Evento("", null, 0,0,"",0, new Luogo("","","","","",""), null);
		List<Integer> errori = new ArrayList<>();
		errori.add(1);
		assertEquals(errori, gest.effettuaControlli(e));
	}

	@Test
	void testEffettuaControlliMoltepliciErrori() throws ConnectException {
		Evento e = new Evento("", new GregorianCalendar(2016, 6, 3), 10, 5, "",0, new Luogo("","","","aaa","",""), null);
		List<Integer> errori = new ArrayList<>();
		errori.add(1);
		errori.add(3);
		errori.add(2);
		errori.add(4);
		assertEquals(errori, gest.effettuaControlli(e));		
	}
}
