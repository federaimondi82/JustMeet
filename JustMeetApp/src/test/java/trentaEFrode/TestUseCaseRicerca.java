package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.users.UtenteRegistrato;
import unicam.trentaEFrode.domain.mainElements.Categoria;
import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.mainElements.RegistroCategorie;


class TestUseCaseRicerca {

	UtenteRegistrato utente = UtenteRegistrato.getInstance().id(2);

	/*
	 * L'utente che accede, visualizza gli eventi nelle vicinanze che corrispondono ai suoi 
	 * interessi. Ovviamente i filtri per la ricerca sono assenti.
	 * */
	@Test
	void testPrimaRicerca() {
		List<Evento> eventiTrovati = utente.cerca(true, "", new ArrayList<Categoria>(), "", "", null);
	}
	
	/*
	 * Se l'utente effettua una ricerca senza inserire i parametri, il sistema restituisce gli 
	 * eventi nelle vicinanze che corrispondono ai suoi interessi. Si considera il gesto come un
	 * aggiornamento della pagina.
	 */
	@Test
	void testRicercaSenzaParametri() {
		List<Evento> eventiTrovati = utente.cerca(false, "", new ArrayList<Categoria>(), "", "", null);
		// parola chiave, categoria, città, provincia, giorno 
	}
	
	@Test
	void testRicercaConParametri1() {
		List<Categorie> categorie = new ArrayList<>();
		categorie.add(RegistroCategorie.getInstance().getCategoria("cibo"));
		List<Evento> eventiTrovati = utente.cerca(false, "", categorie, "", "", null);
		// parola chiave, categoria, città, provincia, giorno 
	}	
	
	@Test
	void testRicercaConParametri2() {
		
		List<Evento> eventiTrovati = utente.cerca(false, "", new ArrayList<Categoria>(), "", "", null);
		// parola chiave, categoria, città, provincia, giorno 
	}	

	@Test
	void testRicercaConParametri3() {
		
		List<Evento> eventiTrovati = utente.cerca(false, "", new ArrayList<Categoria>(), "", "", null);
		// parola chiave, categoria, città, provincia, giorno 
	}	
}
