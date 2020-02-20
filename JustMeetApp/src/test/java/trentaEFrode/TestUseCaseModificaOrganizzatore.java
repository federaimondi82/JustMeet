package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.mainElements.Registratore;
import unicam.trentaEFrode.domain.users.RuoloOrganizzatore;
import unicam.trentaEFrode.domain.users.RuoloPartecipante;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;

class TestUseCaseModificaOrganizzatore {

	@Test
	public void test_modificaOrganizzatore() {
		// Step 1 : Un utente crea un evento
		UtenteRegistrato utente = UtenteRegistrato.getInstance().id(2);		
		RuoloOrganizzatore organizzatore = (RuoloOrganizzatore)utente.setRuolo(1);
		RuoloPartecipante partecipante = (RuoloPartecipante)utente.setRuolo(2);
		Evento nuovoEvento = nuovoEvento();
		
		int nEventiOrganizzatiInizio = organizzatore.getEventi().size();
		int nEventiPartecipatiInizio = partecipante.getEventi().size();
		
		//Step 2 : Tre utenti confermano la partecipazione al nuovo evento creato dall'utente.
		Registratore.getInstance().registraPartecipazione(nuovoEvento.id(), 7);
		Registratore.getInstance().registraPartecipazione(nuovoEvento.id(), 8);
		Registratore.getInstance().registraPartecipazione(nuovoEvento.id(), 9);
		
		int nPartecipantiNuovoEventoInizio = organizzatore.getPartecipanti(nuovoEvento).size();
		
		assertTrue(nPartecipantiNuovoEventoInizio == 3);
		
		// Step 3 : L'utente sceglie di cambiare l'organizzatore all'evento appena creato, selezionandone uno tra i partecipanti		
		assertTrue(organizzatore.setOrganizzatore(nuovoEvento.id(), 7,utente.getId()));
		
		// Step 4 : A questo punto, l'evento modificato è stato spostato nell'agenda del partecipante. 
		int nEventiOrganizzatiFine = organizzatore.getEventi().size();
		int nEventiPartecipatiFine = partecipante.getEventi().size();
		int nPartecipantiNuovoEventoFine = organizzatore.getPartecipanti(nuovoEvento).size();

		assertTrue(nEventiOrganizzatiInizio > nEventiOrganizzatiFine);
		assertTrue(nEventiPartecipatiInizio < nEventiPartecipatiFine);
		assertEquals(nPartecipantiNuovoEventoInizio, nPartecipantiNuovoEventoFine);
	}
	
	private Evento nuovoEvento() {
		
		UtenteRegistrato utente = UtenteRegistrato.getInstance().id(2);
		GregorianCalendar data = new GregorianCalendar();
		data.add(GregorianCalendar.DAY_OF_MONTH, new Random().nextInt(29));
		data.add(GregorianCalendar.MONTH, new Random().nextInt(12));
		data.add(GregorianCalendar.HOUR, new Random().nextInt(24));
		data.add(GregorianCalendar.MINUTE, new Random().nextInt(60));
		
		List<Evento> list=utente.setRuolo(1).getEventi();
		list.sort((a,b)->{
			return a.id()>b.id()?
					-1:
					(a.id()==b.id()?0:1);
			});
		return list.get(0);
	}
}
