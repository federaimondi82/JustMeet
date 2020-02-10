package unicam.trentaEFrode.domain.mainElements;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import unicam.trentaEFrode.domain.parsers.ParserUser;
import unicam.trentaEFrode.domain.parsers.ParserCategorie;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;

/**
 * Rappresentazione virtuale del file di archivio;
 * 
 * All'avvio  viene controllato il file di cache con i dati dell'utente;<br>
 *  nel caso carica i dati, altrimenti si richiede l'autenticazione o la registrazione;
 *  
 *  i dati da caricare sono: generalita',gli eventi creati e gli eventi a cui partecipa e 
 *  la data dell'ultimo accesso(serve per far aggiornare gli eventi)
 *  
 * */
public class RegistroStatico {
	
	//TODO terminare classe

	private static RegistroStatico instance;
	private Evento lastClicked;
	
	
	private File cacheFile;
	private String path;
	private String fileName;
	
	private List<String> fileStatico;
	

	/**
	 * metodo di accesso al Singleton
	 * @return ritorna l'unica istanza del registro
	 */
	public static RegistroStatico getInstance() {
		if(instance == null) instance = new RegistroStatico();
		return instance;
	}
	
	/**
	 * Construttore
	 */
	private RegistroStatico() {
		this.path=System.getProperty("user.dir")+"\\plugin\\cacheFile.txt";
		this.fileStatico=new ArrayList<String>();
		this.setLastClicked(null);
	}

	/**
	 * @return the lastClicked
	 */
	public Evento getLastClicked() {
		return lastClicked;
	}

	/**
	 * @param lastClicked the lastClicked to set
	 */
	public void setLastClicked(Evento lastClicked) {
		this.lastClicked = lastClicked;
	}

	/**
	 * Viene controllato nel file di cache se l'utente si era precedentemente autenticato<br>
	 * L'utente al primo avvio deve effettuare la registrazione, poi l'autenticazione;
	 * se l'autenticazione e' andata a buon il file della cache sare' pieno di dati a lui relativo.
	 * Questo emtodo consente, quindi, di sapese se l'utente e' un utente 
	 * 
	 * @return true se l'utente e' autenticato, false altrimenti.
	 */
	public boolean userIsPresent() {
		// TODO Auto-generated method stub
		return UtenteRegistrato.getInstance().getId()<0;
	}


	public String getPath() {
		return this.path;
	}
	
	/**
	 * Consente di restituire l'oggetto File del file di cache
	 * @return il file di cache
	 */
	public File getCacheFile() {
		File f=null;
		if(this.cacheFile==null) {
			f=new File(this.path);
		}
		return this.cacheFile;
	}
	
	/**
	 * Controlla se il file di cache dell'utente e' stato creato 
	 * @return
	 */
	public boolean cacheFileExists() {
		File f= new File(this.path);
		return f.exists();
	}
	
	/**
	 * Consente di creare il file di cache dove verranno memorizzati tutti i dati<br>
	 * dell'utente registrato
	 * @return true se la creazione e' andata a buon fine, false alteimenti
	 */
	public boolean fileCreator() {
		try {
			
			File f= new File(this.path);
			return f.createNewFile();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Metodo per la lettura del file di cache:<br>
	 * legge il file di cache e compone i dati l'UtenteRegistato
	 * @return l'utente Registrato (singoletto)
	 */
	public void fileReader() {
		try {
			
			BufferedReader br=new BufferedReader(new FileReader(this.path));					
			//i dati vengono collezionati in una lista
			//tipicamente la prima linea riguarda le generalita' dell'utente
			this.fileStatico= br.lines().collect(Collectors.toList());
			
			br.close();
		}catch(IndexOutOfBoundsException | IOException e) {
			//e.printStackTrace();
		}

	}

	/**
	 * Consente di scrivere dei dati sul file di cache<br>
	 * Prima riga per le generalita'
	 * seconda riga per gli interessi
	 * Terza riga un gruppo di eventi da lui creato
	 * quarta riga un gruppo di eventi a cui partecipa
	 * @param dati
	 */
	public void fileWriter(String dati,boolean append) {
		try {
			
			FileWriter writer=new FileWriter(this.path,append);
			BufferedWriter bw=new BufferedWriter(writer);
			
			bw.write(dati+"\n");			
			bw.flush();	
			bw.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}		
	}

	
	/**
	 * Legge i dati dell'utente sul file di cache
	 */
	public void leggiUtente() {
		String generalita=this.fileStatico.get(0);
		
		//prima istanziazione di UtenteRegistarto restituita dal Parser
		ParserUser.getInstance().parseUtenteFromFile(generalita);
	}
	
	/**
	 * Legge gli interessi dell'utente sul file di cache
	 */
	public List<Categoria> leggiInteressi() {
		String json=this.fileStatico.get(1);
		return ParserCategorie.getInstance().parseCategorieFromServer(json);
	}


}
