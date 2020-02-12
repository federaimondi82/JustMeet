package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import java.net.ConnectException;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.mainElements.Categoria;
import unicam.trentaEFrode.domain.mainElements.ConnectBackEnd;
import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.mainElements.Luogo;
import unicam.trentaEFrode.domain.mainElements.RegistroCategorie;
import unicam.trentaEFrode.domain.users.Partecipante;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;
import unicam.trentaEFrode.exceptions.EventoPresente;

class Agenda_Test {

	@Test
	public final void parteciante_partecipa_RightSize() throws ConnectException, EventoPresente {
		Partecipante p=new Partecipante();
		assertEquals(0, p.mostraAgenda().eventi().size());
		
		for(int i=0;i<10;i++) {
			Luogo luogo = new Luogo("bar", "viaTuring", String.valueOf((int)(Math.random()*10000)), "63100", "Ascoli", "AP");		
			Categoria cat = RegistroCategorie.getInstance().categorie().get(1);//2:sport:pallavolo
			GregorianCalendar data = new GregorianCalendar(2020, 1, 1, 11, 11);
			Evento evento = new Evento(String.valueOf((int)(Math.random()*10000)),data,10, 100,"festa",5,luogo,cat);
			
			p.partecipa(evento);
		}
		assertEquals(10, p.mostraAgenda().eventi().size());		
	}
	
	@Test
	public final void parteciante_disdici_RightSize() throws ConnectException, EventoPresente {
		Partecipante p=new Partecipante();
		
		for(int i=0;i<10;i++) {
			Luogo luogo = new Luogo("bar", "viaTuring", String.valueOf((int)(Math.random()*10000)), "63100", "Ascoli", "AP");		
			Categoria cat = RegistroCategorie.getInstance().categorie().get(1);
			GregorianCalendar data = new GregorianCalendar(2020, 1, 1, 11, 11);
			Evento evento = new Evento(String.valueOf((int)(Math.random()*10000)),data,10, 100,"festa",5,luogo,cat);
			p.partecipa(evento);
		}
		p.mostraAgenda().eventi().forEach(el->{
			ConnectBackEnd.getInstance().restRequest("eventi", method)
		});
		
	}

}
