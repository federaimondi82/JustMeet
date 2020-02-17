package unicam.trentaEFrode.domain.users;

import java.util.List;

import unicam.trentaEFrode.domain.mainElements.Agenda;
import unicam.trentaEFrode.domain.mainElements.Evento;

/*
 * L'organizzatore è un ruolo che permette all'utente registrato di gestire gli eventi organizzati.
 * */
public class Organizzatore implements Ruolo{
	
	private Agenda agenda;
	
	public Organizzatore() {
		this.agenda = new Agenda(this);
	}
	
	public Agenda getAgenda() {
		return this.agenda;
	}
	
	public boolean aggiungiEvento(Evento evento){
		return this.agenda.aggiungiEvento(evento);
	}

	public boolean cancellaEvento(int id) {
		return this.agenda.cancella(id);
	}

	public List<Integer> modificaEvento(Evento evento) {
		return this.agenda.modificaEvento(evento);
	}

	public List<Evento> getEventi() {
		return this.agenda.getEventi();
	}

}