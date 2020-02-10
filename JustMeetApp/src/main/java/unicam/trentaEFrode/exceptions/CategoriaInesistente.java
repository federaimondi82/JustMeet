package unicam.trentaEFrode.exceptions;

public class CategoriaInesistente extends Exception {

	private static final long serialVersionUID = 1L;

	public CategoriaInesistente() {
		super("La categoria ricercata e' inesistente.");
	}
}
