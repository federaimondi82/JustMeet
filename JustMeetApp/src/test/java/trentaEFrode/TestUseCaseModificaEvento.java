package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Random;
import java.net.ConnectException;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.users.Organizzatore;
import unicam.trentaEFrode.domain.users.Ruolo;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;

class TestUseCaseModificaEvento {
	@Test
	void testModificaEventoData() throws ConnectException {
		Ruolo organizzatore = UtenteRegistrato.getInstance().id(2).setRuolo(1);
		//Step 1 : Recupero gli eventi creati dall'utente
		List<Evento> lista = organizzatore.getEventi();
		
		//lista.stream().forEach(e -> System.out.println(e.toString()));
		//Step 2 : Scelgo un evento randomico
		Evento e = lista.get(new Random().nextInt(lista.size()));
		//Step 3 : Creo una nuova data e la imposto come data dell'evento scelto
		GregorianCalendar nuovaData = new GregorianCalendar();
		nuovaData.add(GregorianCalendar.MONTH, new Random().nextInt(13));
		nuovaData.add(GregorianCalendar.HOUR, new Random().nextInt(24));
		//Step 4 : Effettuo il cambiamento e confermo le modifiche
		e.cambiaDataOra(nuovaData);
		
		//System.out.println(e.toString());
		
		List<Integer> risposta = ((Organizzatore)organizzatore).modificaEvento(e);
		assertEquals(1, risposta.size());
		assertEquals(new Integer(-1), risposta.get(0));
	}
}
