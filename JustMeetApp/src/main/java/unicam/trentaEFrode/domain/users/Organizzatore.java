package unicam.trentaEFrode.domain.users;

import java.net.ConnectException;

import unicam.trentaEFrode.domain.mainElements.Agenda;
import unicam.trentaEFrode.domain.mainElements.ConnectBackEnd;
import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.parsers.Parser;
import unicam.trentaEFrode.domain.parsers.ParserEventi;
import unicam.trentaEFrode.exceptions.EventoPresente;

public class Organizzatore implements Ruolo{
	
	private Agenda agenda;
	
	public Organizzatore() {
		this.agenda = new Agenda();
	}
	
	public Agenda getAgenda() {
		if(agenda == null) {
			agenda = new Agenda();
			agenda.carica(ParserEventi.getInstance().parseEventi(ConnectBackEnd.getInstance().restRequest("/eventi/utenti/" + UtenteRegistrato.getInstance().getId(), "GET")));
			
		} 
		return agenda;
	}
	
	public boolean aggiungiEvento(Evento evento){
		return agenda.aggiungiEvento(evento);
	}

	public boolean cancellaEvento(int id) {
		return agenda.cancella(id);
	}

}