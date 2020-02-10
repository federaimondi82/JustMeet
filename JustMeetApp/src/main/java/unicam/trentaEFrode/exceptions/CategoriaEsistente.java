package unicam.trentaEFrode.exceptions;

public class CategoriaEsistente extends Exception {

	private static final long serialVersionUID = 1L;

	public CategoriaEsistente() {
		super("La categoria che stai tentando di inserire ha lo stesso nome di una gia' esistente.");
	}
}
