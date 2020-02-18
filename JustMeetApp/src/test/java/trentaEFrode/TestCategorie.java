package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.mainElements.Categoria;
import unicam.trentaEFrode.domain.mainElements.ConnectBackEnd;
import unicam.trentaEFrode.domain.parsers.ParserCategorie;



class TestCategorie {


	@Test
	void testLoadCategorie() {
		String s="";
		s = ConnectBackEnd.getInstance().restRequest("/cat/", "GET");		
		List<Categoria> l=ParserCategorie.getInstance().parseCategorieFromServer(s);
		assertNotNull(s);
	}
}
