package unicam.trentaEFrode.domain.users;

import java.util.List;

import unicam.trentaEFrode.domain.mainElements.Agenda;
import unicam.trentaEFrode.domain.mainElements.ConnectBackEnd;
import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.mainElements.GestoreEventi;

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
		boolean result = Boolean.parseBoolean(ConnectBackEnd.getInstance().restRequest("/eventi/cancella/" + id, "DELETE"));
		if(result) return this.agenda.cancella(id);
		return false;
	}

	public List<Integer> modificaEvento(Evento evento) {
		List<Integer> risultato = GestoreEventi.getInstance().effettuaControlli(evento);
		if(risultato.get(0)== -1) this.agenda.modificaEvento(evento);
		return risultato;
	}

	public List<Evento> getEventi() {
		return this.agenda.getEventi();
	}

}