package unicam.trentaEFrode.exceptions;

import java.util.Map;
import java.util.TreeMap;

public class CodiciRisposte {

	protected Map<Integer, String> codici; 
	
	public CodiciRisposte() {
		
		this.codici = new TreeMap<>();
		codici.put(-1, "Operazione andata a buon fine!");
		codici.put( 0, "Si e' verificato un errore! Riprova piu' tardi o contatta l'amministrazione");
		codici.put( 1, "Compila tutti i campi!");
		codici.put( 2, "Cap non valido!");
		codici.put( 3, "Data non valida!");
		codici.put( 4, "Minimo e massimo non validi!");
		codici.put( 5, "Devi essere maggiorenne per iscriverti alla piattaforma!");
		codici.put( 6, "Le password inserite non coincidono");
		codici.put( 7, "Email gia' esistente!");
		codici.put( 8, "Provincia non valida.");
	}
	
	public String getMessageOf(int n) {
		return codici.get(n);
	}
}
