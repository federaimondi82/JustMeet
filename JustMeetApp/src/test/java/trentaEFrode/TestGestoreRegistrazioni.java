package trentaEFrode;



import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.mainElements.ConnectBackEnd;
import unicam.trentaEFrode.domain.mainElements.DocuDiRegis;
import unicam.trentaEFrode.domain.mainElements.GestoreRegistrazioni;
import unicam.trentaEFrode.domain.mainElements.Registratore;
import unicam.trentaEFrode.domain.parsers.ParserUser;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;


class TestGestoreRegistrazioni {
	
	private GestoreRegistrazioni reg = GestoreRegistrazioni.getInstance();

	@Test 
	void testControllaCap() {
		String cap1 = "00100";
		String cap2 = "abc";
		assertTrue(reg.controllaCap(cap1));
		assertFalse(reg.controllaCap(cap2));	
	}
	
	@Test 
	void testDataNascita() {
		LocalDate maggiorenne = LocalDate.of(1994, Month.AUGUST, 21);
		LocalDate minorenne = LocalDate.of(2011, Month.MARCH, 3);
		assertTrue(reg.controllaDataNascita(maggiorenne));
		assertFalse(reg.controllaDataNascita(minorenne));
	}
	
	@Test 
	void testCodificaPassword() {
		String psw = "abc";
		String alg1 = "SHA-256";
		String alg2 = "aaa";
		assertNotNull(reg.codificaPassword(psw, alg1));		
		assertNull(reg.codificaPassword(psw, alg2));
	}
	
	@Test
	void testEffettuaControlliCampiVuoti() {
		DocuDiRegis docu = new DocuDiRegis("", "", "", "", "", "", null, "", "");
		List<Integer> errori = new ArrayList<Integer>();
		errori.add(1);
		try {
			assertEquals(errori, reg.effettuaControlli(docu));
		} catch (ConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testEffettuaControlliMoltepliciErrori() {
		DocuDiRegis docu = new DocuDiRegis("", "", "", "", "abc", "ab", null, "aaa", "");
		List<Integer> errori = new ArrayList<Integer>();
		errori.add(1);
		errori.add(6);
		errori.add(2);
		try {
			assertEquals(errori, reg.effettuaControlli(docu));
		} catch (ConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testEffettuaControlliMoltepliciErroriEUtenteMinorenne() {
		DocuDiRegis docu = new DocuDiRegis("Mario", "", "mariorossi@email.it", "", "abc", "ab", LocalDate.of(2011, Month.MARCH, 3), "aaa", "Roma");
		List<Integer> errori = new ArrayList<Integer>();
		errori.add(5);
		try {
			assertEquals(errori, reg.effettuaControlli(docu));
		} catch (ConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public final void test_restGet() {
		
		URL url=null;
		HttpURLConnection con=null;
		BufferedReader br=null;
		try {
			//la chiamata ritorna tutti gli utenti su DB
			url = new URL("http://localhost:8080/utenti/"+String.valueOf((int)(Math.random()*10000))+"");
		
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			if (con.getResponseCode() != 200) {
				throw new RuntimeException("Errore: codice di risposta "+con.getResponseCode());
			}
			
			br=new BufferedReader(new InputStreamReader(con.getInputStream()));

			assertTrue(br.lines().count()>0);
			
			con.disconnect();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public final void test_registraUtenteTrue() {
		DocuDiRegis docu = new DocuDiRegis("Mario", "Rossi", String.valueOf((int)(Math.random()*10000))+"@email.it", String.valueOf((int)(Math.random()*10000)), "abc", "abc", LocalDate.of(2011, Month.MARCH, 3), "06", "Roma");
		docu.setInteressi("1_2_");

		URL url=null;
		HttpURLConnection con=null;
		try {
			//la chiamata ritorna tutti gli utenti su DB
			url = new URL("http://localhost:8080/utenti/"+docu.toString()+"");
		
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			assertTrue(con.getResponseCode()==200);
			
			con.disconnect();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public final void test_registraUtenteFalse() {
		DocuDiRegis docu = new DocuDiRegis("Mario", "Rossi", "mariorossi@email.it", "marione", "abc", "abc", LocalDate.of(2011, Month.MARCH, 3), "06", "Roma");
		
		URL url=null;
		HttpURLConnection con=null;
		BufferedReader br=null;
		try {
			url = new URL("http://localhost:8080/utenti/"+docu.toString()+"");
		
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			br=new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line=br.readLine();
			assertEquals(false, Boolean.parseBoolean(line));
			
			con.disconnect();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void test_registrazione() {
		DocuDiRegis docu = new DocuDiRegis("Mario", "Rossi", "mariorossi2@email.it", "marione", "abc", "abc", LocalDate.of(2011, Month.MARCH, 3), "06", "Roma");
		try {
			assertTrue(Registratore.getInstance().registra(docu)==false);
		} catch (ConnectException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void test_nickExists() {
		
		String value = null;
		try {
			value = ConnectBackEnd.getInstance().restRequest("/utenti/nickname/marione", "GET");
		} catch (ConnectException e) {
			e.printStackTrace();
		}
		assertTrue(Boolean.parseBoolean(value));
	}
	
	@Test
	public final void test_autenticazione() {
		
		String value = "";
		try {
			value = ConnectBackEnd.getInstance().restRequest("/utenti/mariorossi2@email.it:abc", "GET");
		} catch (ConnectException e) {
			e.printStackTrace();
		}
		UtenteRegistrato u=ParserUser.getInstance().parseUtenteFromServer(value);
		assertTrue(u.getNome().equals("mario"));
		System.out.println(u.toString());
	}
	
}
