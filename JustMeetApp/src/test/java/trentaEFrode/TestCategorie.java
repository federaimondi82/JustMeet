package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import java.net.ConnectException;
import java.util.List;
import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.mainElements.Categoria;
import unicam.trentaEFrode.domain.mainElements.ConnectBackEnd;
import unicam.trentaEFrode.domain.parsers.ParserCategorie;



class TestCategorie {


	@Test
	void testLoadCategorie() {
		String s="";
		try {
			s = ConnectBackEnd.getInstance().restRequest("/cat/", "GET");
		} catch (ConnectException e) {
			e.printStackTrace();
		}
		
		List<Categoria> l=ParserCategorie.getInstance().parseCategorieFromServer(s);
		
		l.stream().forEach(cat->System.out.println(cat.toString()));
		
		assertNotNull(s);
	}
	
	/*
	@Test
	void testMostraCategorie() {
		//UtenteRegistrato u=new UtenteRegistrato();
		/*u.getMieCategorie().retriveCatFromServer().stream().forEach(ele->{
			System.out.println(ele.toString());
		});
		
				
		assertNotNull(UtenteRegistrato.getInstance().getMieCategorie().retriveCatFromServer().get(0));
	}
	*/

}
