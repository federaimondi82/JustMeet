package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Random;
import java.net.ConnectException;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.users.Organizzatore;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;

class TestUseCaseModificaEvento {
	@Test
	void testModificaEventoData() throws ConnectException {
		Organizzatore organizzatore = UtenteRegistrato.getInstance().id(2).getOrganizzatore();
		//Step 1 : Recupero gli eventi creati dall'utente
		List<Evento> lista = organizzatore.getEventi();
		//Step 2 : Scelgo un evento randomico
		Evento e = lista.get(new Random().nextInt(lista.size()));
		//Step 3 : Creo una nuova data e la imposto come data dell'evento scelto
		GregorianCalendar nuovaData = new GregorianCalendar();
		nuovaData.add(GregorianCalendar.MONTH, 2);
		//Step 4 : Effettuo il cambiamento e confermo le modifiche
		e.cambiaDataOra(nuovaData);
		List<Integer> risposta = organizzatore.modificaEvento(e);
		assertEquals(1, risposta.size());
		assertEquals(new Integer(-1), risposta.get(0));
	}
}
