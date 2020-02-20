package unicam.trentaEFrode.domain.users;

import java.util.List;

import unicam.trentaEFrode.domain.mainElements.Agenda;
import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.mainElements.GestoreEventi;

/*
 * L'organizzatore è un ruolo che permette all'utente registrato di gestire gli eventi organizzati.
 * */
public class Organizzatore implements RuoloOrganizzatore{
	
	private Agenda agenda;
	private GestoreEventi gest;
	
	public Organizzatore() {
		this.agenda = new Agenda(this);
		this.gest = GestoreEventi.getInstance();
	}
	
	public Agenda getAgenda() {
		return this.agenda;
	}
	
	public boolean aggiungiEvento(Evento evento){
		return this.agenda.aggiungiEvento(evento);
	}

	public boolean cancellaEvento(int id) {
		boolean result = gest.cancellaEvento(id);
		if(result) return this.agenda.cancella(id);
		return false;
	}

	public List<Integer> modificaEvento(Evento evento) {
		List<Integer> risultato = gest.effettuaControlli(evento);
		if(risultato.get(0)== -1) this.agenda.modificaEvento(evento);
		return risultato;
	}

	public List<Evento> getEventi() {
		return this.agenda.getEventi();
	}

	public boolean setOrganizzatore(int idEvento, int idNuovoOrganizzatore,int idVecchioOrganizzatore) {
		return gest.cambiaOrganizzatore(idEvento, idNuovoOrganizzatore,idVecchioOrganizzatore);
	}

	@Override
	public List<String> getPartecipanti(Evento evento) {
		return this.agenda.getPartecipanti(evento);
	}

}