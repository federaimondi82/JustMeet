package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import java.net.ConnectException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.users.UtenteRegistrato;
import unicam.trentaEFrode.domain.mainElements.Evento;


class TestUseCaseRicerca {

	UtenteRegistrato utente = UtenteRegistrato.getInstance().id(2);

	/*
	 * L'utente che accede, visualizza gli eventi nelle vicinanze che corrispondono ai suoi 
	 * interessi.
	 * */
	@Test
	void testPrimaRicerca() {
		List<Evento> eventiValoriDefault = utente.cerca(true, "null", "null", "null", "null", null, null);
		List<Evento> eventiValoriCasuali = utente.cerca(true, "pizza", "cibo", "Roma", "RM", new GregorianCalendar(), null);
		assertTrue(eventiValoriDefault.size() == eventiValoriCasuali.size());
		System.out.println("---------------------------TEST PRIMA RICERCA-----------------------------------");
		System.out.println("\n eventiValoriDefault : \n");
		for(Evento e : eventiValoriDefault) System.out.println(e.toString());
		System.out.println("\n eventiValoriCasuali : \n");
		for(Evento e : eventiValoriCasuali) System.out.println(e.toString());
		int indice1 = new Random().nextInt(eventiValoriDefault.size());
		int indice2 = new Random().nextInt(eventiValoriDefault.size());
		int indice3 = new Random().nextInt(eventiValoriDefault.size());
		assertTrue(eventiValoriDefault.get(indice1).equals(eventiValoriCasuali.get(indice1)));
		assertTrue(eventiValoriDefault.get(indice2).equals(eventiValoriCasuali.get(indice2)));
		assertTrue(eventiValoriDefault.get(indice3).equals(eventiValoriCasuali.get(indice3)));
	}
	
	/*
	 * Se l'utente effettua una ricerca senza inserire i parametri, il sistema restituisce gli 
	 * eventi nelle vicinanze che corrispondono ai suoi interessi. Si considera il gesto come un
	 * aggiornamento della pagina.
	 */
	@Test
	void testRicercaSenzaParametri() {		
		List<Evento> eventiSenzaParametri = utente.cerca(false, "null", "null", "null", "null", null, null);
		List<Evento> eventiValoriDefault = utente.cerca(true, "null", "null", "null", "null", null, null);
		assertTrue(eventiValoriDefault.size() == eventiSenzaParametri.size());
		assertEquals(eventiValoriDefault, eventiSenzaParametri);
		System.out.println("---------------------------TEST RICERCA SENZA PARAMETRI-----------------------------------");
		System.out.println("\n eventiValoriDefault : \n");
		for(Evento e : eventiValoriDefault) System.out.println(e.toString());
		System.out.println("\n eventiValoriCasuali : \n");
		for(Evento e : eventiSenzaParametri) System.out.println(e.toString());
		int indice1 = new Random().nextInt(eventiValoriDefault.size());
		int indice2 = new Random().nextInt(eventiValoriDefault.size());
		int indice3 = new Random().nextInt(eventiValoriDefault.size());
		assertTrue(eventiValoriDefault.get(indice1).equals(eventiSenzaParametri.get(indice1)));
		assertTrue(eventiValoriDefault.get(indice2).equals(eventiSenzaParametri.get(indice2)));
		assertTrue(eventiValoriDefault.get(indice3).equals(eventiSenzaParametri.get(indice3)));
	}
	
	@Test
	void testRicercaConParametri1() throws ConnectException {
		new TestUseCaseCreazioneEvento().testEventoValido();
		List<Evento> eventiTrovati = utente.cerca(false, "Pranzo", "cibo", "Ascoli", "AP", new GregorianCalendar(), null );
		assertFalse(eventiTrovati.isEmpty());
		System.out.println("---------------------------TEST RICERCA CON PARAMETRI 1-----------------------------------");
		for(Evento e : eventiTrovati) System.out.println(e.toString());		
	}	
	
	@Test
	void testRicercaConParametri2() throws ConnectException {
		//new TestUseCaseCreazioneEvento().testEventoValido();
		List<Evento> eventiTrovati = utente.cerca(false, "null", "cibo", "null", "null", new GregorianCalendar(), null );
		assertFalse(eventiTrovati.isEmpty());
		System.out.println("---------------------------TEST RICERCA CON PARAMETRI 2-----------------------------------");
		for(Evento e : eventiTrovati) System.out.println(e.toString());		
	}

	@Test
	void testRicercaConParametri3() throws ConnectException {
		//new TestUseCaseCreazioneEvento().testEventoValido();
		List<Evento> eventiTrovati = utente.cerca(false, "Pranzo", "null", "null", "null", null, new GregorianCalendar() );
		assertFalse(eventiTrovati.isEmpty());
		System.out.println("---------------------------TEST RICERCA CON PARAMETRI 3-----------------------------------");
		for(Evento e : eventiTrovati) System.out.println(e.toString());		
	}	
}
