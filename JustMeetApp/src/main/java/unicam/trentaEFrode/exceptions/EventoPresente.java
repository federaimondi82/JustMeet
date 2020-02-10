package unicam.trentaEFrode.exceptions;

public class EventoPresente extends Exception {

	private static final long serialVersionUID = 1L;

	public EventoPresente() {
		super("L'evento che stai cercando di inserire ha lo stesso nome di uno gia' presente");
	}
}
