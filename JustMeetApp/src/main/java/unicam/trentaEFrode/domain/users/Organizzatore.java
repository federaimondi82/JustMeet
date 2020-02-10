package unicam.trentaEFrode.domain.users;

import unicam.trentaEFrode.domain.mainElements.Agenda;
import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.exceptions.EventoPresente;

public class Organizzatore implements Ruolo{
	
	private Agenda eventi;
	
	public Organizzatore() {
		this.eventi = new Agenda();
		
	}
	
	public void aggiungiEvento(Evento evento) throws EventoPresente {
		eventi.aggiungiEvento(evento);
	}

}