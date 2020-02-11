package unicam.trentaEFrode.domain.mainElements;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import unicam.trentaEFrode.domain.parsers.Parser;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;

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
	 * @throws ConnectException
	 */
	public List<Integer> effettuaControlli(Evento evento) throws ConnectException {
		String nome = evento.nome();
		GregorianCalendar dataOra = evento.dataOra();
		Integer min = evento.minPartecipanti();
		Integer max = evento.maxPartecipanti();
		String descrizione = evento.descrizione();
		Luogo luogo = evento.luogo();
		Categoria categoria = evento.categoria();
		List<Integer> risposta = new ArrayList<>();
		if (nome == "" || descrizione == "" || categoria == null || dataOra == null)
			risposta.add(1);
		if (dataOra != null)
			controllaDataOra(dataOra, risposta);
		controllaLuogo(luogo, risposta);
		controllaMinMax(min, max, risposta);
		if (risposta.size() != 0)
			return risposta;
		risposta.add(this.registratore.registra(evento) ? -1 : 0);
		return risposta;
	}

	/**
	 * Controlla la validita' dell'oggetto dataOra, il quale deve corrispondere ad
	 * un tempo futuro. Nel caso di non validita', aggiunge alla lista passata, il
	 * codice corrispondene a tale errore.
	 * 
	 * @param dataOra: l'oggetto da controllare
	 * @param lista: la lista su cui aggiungere l'eventuale codice dell'errore
	 *        verificato.
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
	 * Controlla la validitï¿½ dell'oggetto luogo, il quale deve avere tutti i campi
	 * validi. Nel caso di non validitï¿½, aggiunge alla lista passata, il codice
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
	 * Controlla, tramite l'ordinamento naturale numerico, la validitï¿½ dei numeri
	 * min e max. Nel caso di non validitï¿½, aggiunge alla lista passata, il codice
	 * corrispondene a tale errore. Il numero 0 corrisponde alla volontï¿½ da parte
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

	public List<Integer> modificaFederico(Evento evento) throws ConnectException {
		List<Integer> lista = new ArrayList<>();
		lista.add(this.registratore.registra(evento) ? -1 : 0);
		return lista;
	}

	// TODO FEDERICO, ti ho lasciato il metodo che avevo pensato io. forse ti puï¿½
	// essere utile
	/**
	 * Modifica {@code evento} con i campi di {@code nuovo} solo se i campi di
	 * quest'ultimo sono validi. Se i campi non sono validi, vengono ignorati
	 * 
	 * @throws ConnectException
	 */
	public List<Integer> modificaVeronica(Evento evento, Evento nuovo) throws ConnectException {
		Map<String, String> campiModificati = new TreeMap<>();
		List<Integer> lista = new ArrayList<>();
		if (!evento.nome().equals(nuovo.nome()) & nuovo.nome() != "")
			campiModificati.put("nome", nuovo.nome());
		if (!evento.descrizione().equals(nuovo.descrizione()) & nuovo.descrizione() != "")
			campiModificati.put("descrizione", nuovo.descrizione());
		if (!evento.categoria().equals(nuovo.categoria()) & nuovo.categoria() != null)
			campiModificati.put("categoria", "" + nuovo.categoria().getId() + "");
		if (!evento.dataOra().equals(nuovo.dataOra()) & nuovo.dataOra() != null) {
			controllaDataOra(nuovo.dataOra(), lista);
			if (lista.size() == 0)
				campiModificati.put("dataOra", nuovo.dataOra().toString());
			else
				lista.clear();
		}
		if (!evento.luogo().equals(nuovo.luogo()) & nuovo.luogo() != null) {
			controllaLuogo(nuovo.luogo(), lista);
			if (lista.size() == 0)
				campiModificati.put("luogo", nuovo.luogo().toString());
			else
				lista.clear();
		}
		if (evento.minPartecipanti() != nuovo.minPartecipanti()
				|| evento.maxPartecipanti() != nuovo.maxPartecipanti()) {
			controllaLuogo(nuovo.luogo(), lista);
			if (lista.size() != 0)
				lista.clear();
			else {
				campiModificati.put("minPartecipanti", nuovo.minPartecipanti().toString());
				campiModificati.put("maxPartecipanti", nuovo.maxPartecipanti().toString());
			}
		}
		if (campiModificati.size() != 0)
			lista.add(this.registratore.modificaVeronica(nuovo.id(), campiModificati) ? -1 : 0);
		return lista;
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

	public List<Integer> confermaEvento(Evento evento) throws ConnectException {
		evento.setConfermato(true);
		return modificaFederico(evento);
	}

	public List<Evento> cerca(boolean primoAccesso, String parola, String categoria, GregorianCalendar giorno,
			String citta, String provincia) {
		String listaEventi;
		if (primoAccesso) { // se è il primo accesso mi baso sulle informazioni dell’utente: interessi,
							// città, provincia
			UtenteRegistrato u = UtenteRegistrato.getInstance();
			listaEventi = ConnectBackEnd.getInstance().restRequest("/cerca/null:{" + u.toStringInteressi()+ "}:{null}:{" + u.getCitta() + "}:{" + u.getProvincia() + "}", "GET");
		} else {
			if (parola == "") parola = "null";
			if (categoria == "") categoria = "null";
			if (citta == "") citta = "null";
			if (provincia == "") provincia = "null";
			// il seguente controllo evita di effettuare una ricerca senza che l'utente
			// abbia inserito filtri.
			if (!(giorno == GregorianCalendar.getInstance() & parola == "null" & categoria == "null" & citta == "null"
					& provincia == "null")) {
				listaEventi = ConnectBackEnd.getInstance().restRequest("/cerca/{" + parola + "}:{" + categoria + "}:{"+ giorno + "}:{" + citta + "}:{" + provincia + "}", "GET");
			} else cerca(true, "", "", null, "", ""); // l’utente ha cliccato cerca senza specificare i filtri. si									
			// considera il gesto come un refresh della pagina.
		}
		return Parser.getInstance().parseEventi(listaEventi);
	}
}