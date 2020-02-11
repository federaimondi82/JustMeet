package trentaEFrode;


import static org.junit.jupiter.api.Assertions.*;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.mainElements.Categoria;
import unicam.trentaEFrode.domain.mainElements.ConnectBackEnd;
import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.mainElements.GestoreEventi;
import unicam.trentaEFrode.domain.mainElements.Luogo;
import unicam.trentaEFrode.domain.mainElements.RegistroCategorie;


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
	
	@Test
	public final void sendNewEvento() throws ConnectException {
		//istanze per la creazione dell'evento
		Luogo luogo = new Luogo("bar", "viaTuring", String.valueOf((int)(Math.random()*10000)), "63100", "Ascoli", "AP");		
		Categoria cat = RegistroCategorie.getInstance().categorie().get(1);//2:sport:pallavolo
		GregorianCalendar data = new GregorianCalendar(2020, 1, 1, 11, 11);
		
		//istanza dell'evento da salvare
		Evento evento = new Evento(String.valueOf((int)(Math.random()*10000)),data,10, 100,"festa",5,luogo,cat);
		System.out.println(evento.toString()+":"+"18");
		
		boolean b=ConnectBackEnd.getInstance().restRequest("/eventi/nuovo/", "POST",(evento.toString()+":"+"18"));
		assertTrue(b);
	}
}
