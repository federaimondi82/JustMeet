package unicam.trentaEFrode.domain.mainElements;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import unicam.trentaEFrode.domain.parsers.ParserEventi;

/**
 * Classe Singleton che effettua operazioni in relazione agli eventi.
 * */
public class GestoreEventi extends Gestore {

	private static GestoreEventi instance = null;

	private Registratore registratore = null;

	public static GestoreEventi getInstance() {
		if (instance == null)
			instance = new GestoreEventi();
		return instance;
	}

	private GestoreEventi() {
		this.registratore = Registratore.getInstance();
	}

	/**
	 * Effettua i controlli necessari sull'evento passato e restituisce la lista dei
	 * codici corrispondenti alle risposte dei controlli.
	 * 
	 * @param evento: l'evento su cui effettuare i controlli.
	 * @return la lista dei codici corrispondenti alle risposte dei controlli.
	 */
	public List<Integer> effettuaControlli(Evento evento) {
		String nome = evento.nome();
		GregorianCalendar dataOra = evento.dataOra();
		Integer min = evento.minPartecipanti();
		Integer max = evento.maxPartecipanti();
		String descrizione = evento.descrizione();
		Luogo luogo = evento.luogo();
		Categoria categoria = evento.categoria();
		List<Integer> risposta = new ArrayList<>();
		if (nome == "" || descrizione == "" || categoria == null || dataOra == null) risposta.add(1);
		if (dataOra != null) controllaDataOra(dataOra, risposta);
		controllaLuogo(luogo, risposta);
		controllaMinMax(min, max, risposta);
		if (risposta.size() != 0) return risposta;
		risposta.add(this.registratore.registra(evento) ? -1 : 0);
		return risposta;
	}

	/**
	 * Controlla la validita' dell'oggetto dataOra, il quale deve corrispondere ad
	 * un tempo futuro. Nel caso di non validita', aggiunge alla lista passata, il
	 * codice corrispondene a tale errore.
	 * 
	 * @param dataOra: l'oggetto da controllare
	 * @param lista: la lista su cui aggiungere l'eventuale codice dell'errore verificato.
	 */
	private void controllaDataOra(GregorianCalendar dataOra, List<Integer> lista) {
		try {
			dataOra.setLenient(false);
			dataOra.get(Calendar.DATE);
			if (dataOra.getTime().before(GregorianCalendar.getInstance().getTime()))
				throw new IllegalArgumentException();
		} catch (IllegalArgumentException e) {
			lista.add(3);
		}
	}

	/**
	 * Controlla la validit� dell'oggetto luogo, il quale deve avere tutti i campi
	 * validi. Nel caso di non validit�, aggiunge alla lista passata, il codice
	 * corrispondene a tale errore.
	 * 
	 * @param luogo: l'oggetto da controllare
	 * @param lista: la lista su cui aggiungere l'eventuale codice dell'errore
	 *        verificato.
	 */
	private void controllaLuogo(Luogo luogo, List<Integer> lista) {
		String indirizzo = luogo.indirizzo();
		String numeroCivico = luogo.numeroCivico();
		String cap = luogo.cap();
		String citta = luogo.citta();
		String provincia = luogo.provincia();
		if (indirizzo == "" || numeroCivico == "" || cap == "" || citta == "" || provincia == "")
			if (lista.indexOf(1) == -1)
				lista.add(1);
			else if (cap != "" && !controllaCap(cap))
				lista.add(2);
	}

	/**
	 * Controlla, tramite l'ordinamento naturale numerico, la validit� dei numeri
	 * min e max. Nel caso di non validit�, aggiunge alla lista passata, il codice
	 * corrispondene a tale errore. Il numero 0 corrisponde alla volont� da parte
	 * dell'utente di non specificare tale informazione.
	 * 
	 * @param min: numero minimo di partecipanti inserito.
	 * @param max: numero massimo di partecipanti inserito.
	 * @param lista: la lista su cui aggiungere l'eventuale codice dell'errore
	 *        verificato.
	 */
	private void controllaMinMax(Integer min, Integer max, List<Integer> lista) {
		if (min != 0 && max != 0 && min > max)
			lista.add(4);
	}

	/**
	 * Incarica il backend ad inviare una notifica a tutti i partecipanti
	 * dell'evento passato, contenente il testo passato
	 */
	public List<Integer> notificaPartecipanti(Evento evento, String testo) {
		// TODO DIRE AL BACKEND DI INVIARE LE NOTIFICHE AI PARTECIPANTI
		// RICORDA CHE NELL'EVENTO e' INCLUSA LA LISTA DEGLI ID DEI PARTECIPANTI.
		return null;
	}

	/**
	 * Effettua una ricerca degli eventi aventi i parametri passati.
	 * @param parola : una parola che deve essere contenuta nel nome dell'evento
	 * @param categoria : la categoria che devono acere gli eventi cercati
	 * @param citta : la citta' dove si svolge l'evento 
	 * @param provincia : la provincia dove di svolge l'evento
	 * @param da : la data di inizio del range di svolgimento degli eventi.
	 * @param a : la data di fine del range di svolgimento degli eventi.
	 * @param idUtente : id dell'utente che effettua la ricerca.
	 * @return la lista degli eventi trovati.
	 */
	public List<Evento> cerca(String parola, String categoria, String citta, String provincia, 
			GregorianCalendar da, GregorianCalendar a, int idUtente) {
		String date = controllaDate(da, a);
		String listaEventi = ConnectBackEnd.getInstance().restRequest("/cerca/" + parola + ":" + categoria + ":" + citta + ":" + provincia + ":" + date + ":" + idUtente + "", "GET");
		return ParserEventi.getInstance().parseEventiFromServerToClient(listaEventi);
	}

	/**
	 * Controlla la validit� delle date, se non valide vengono sostituite con i valori di default 
	 * (da = "adesso" a = "esattamente tra una settimana").
	 * @param da : la data di inizio del range di svolgimento degli eventi.
	 * @param a : la data di fine del range di svolgimento degli eventi.
	 * @return le date risultanti in formato stringa.
	 */
	private String controllaDate(GregorianCalendar da, GregorianCalendar a) {
		GregorianCalendar oggi = new GregorianCalendar();
		if(a.before(da)) {
			GregorianCalendar app = a;
			a = da;
			da = app;
		}
		if(da.before(oggi)) da = oggi;
		if(a.before(oggi)) {
			a = oggi;
			a.add(Calendar.DAY_OF_MONTH, 7);
		}  
		return ""+ da.get(Calendar.YEAR) + ":" +da.get(Calendar.MONTH)+1 + ":" + da.get(Calendar.DAY_OF_MONTH) + ":" +
				a.get(Calendar.YEAR) + ":" +a.get(Calendar.MONTH)+1 + ":" + a.get(Calendar.DAY_OF_MONTH) ;
	}

	
	/**
	 * Disdice la partecipazione dell'utente avente come id idUtente all'evento avente come id idEvento
	 * @param idEvento : l'id dell'evento da disdire.
	 * @param idUtente : l'id dell'utente che vuole disdire la partecipazione.
	 * @return true se l'operazione � andata a buon fine, false altrimenti.
	 */
	public boolean disdiciPartecipazione(int idEvento, int idUtente) {
		return ConnectBackEnd.getInstance().restRequest("/partecipa/disdici/", "DELETE", idEvento + ":" + idUtente);
	}

	/**
	 * Cambia l'organizzatore dell'evento con id idEvento da idVecchioOrganizzatore a idNuovoOrganizzatore.
	 * @param idEvento : l'id dell'evento da cambiare.
	 * @param idNuovoOrganizzatore : l'id del nuovo organizzatore.
	 * @param idVecchioOrganizzatore : l'id del vecchio organizzatore.
	 * @return true se l'operazione � andata a buon fine, false altrimenti.
	 */
	public boolean cambiaOrganizzatore(int idEvento, int idNuovoOrganizzatore,int idVecchioOrganizzatore) {
		return ConnectBackEnd.getInstance().restRequest("/evento/cambiaorganizzatore/", "POST", idEvento + ":" + idNuovoOrganizzatore+":"+idVecchioOrganizzatore );
	}

	/**
	 * Cancella l'evento con id idEvento.
	 * @param idEvento : l'id dell'evento da cancellare.
	 * @return true se l'operazione � andata a buon fine, false altrimenti.
	 */
	public boolean cancellaEvento(int idEvento) {
		return ConnectBackEnd.getInstance().restRequest("/eventi/cancella/", "DELETE", String.valueOf(idEvento));
	}

}