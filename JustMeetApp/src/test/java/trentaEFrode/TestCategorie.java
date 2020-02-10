package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import java.net.ConnectException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.mainElements.Categoria;
import unicam.trentaEFrode.domain.mainElements.ConnectBackEnd;
import unicam.trentaEFrode.domain.parsers.Parser;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;



class TestCategorie {


	@Test
	void testLoadCategorie() {
		List<?> list = null;
		try {
			list = ConnectBackEnd.getInstance().restRequest("/cat/", "GET");
		} catch (ConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Categoria> l=Parser.getInstance().parseCategorieFromServer((String)list.get(0));
		
		l.stream().forEach(cat->System.out.println(cat.toString()));
		
		assertNotNull(list.get(0));
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
