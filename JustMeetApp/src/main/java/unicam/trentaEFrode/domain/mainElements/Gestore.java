package unicam.trentaEFrode.domain.mainElements;

public abstract class Gestore {

	/**
	 * Ritorna true se il cap inserito e' un numero, false altrimenti.
	 * @param cap: il cap da controllare.
	 * @return true se il cap inserito e' un numero.
	 * @return false il cap inserito non e' un numero.
	 * */
	public boolean controllaCap(String cap) {
		try {
			Integer.parseInt(cap);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
}
