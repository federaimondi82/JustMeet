package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.mainElements.ConnectBackEnd;
import unicam.trentaEFrode.domain.mainElements.DocuDiRegis;

class TestConnectBackend {

	@Test
	public final void test_ConnectBackend() {
		
		URL url=null;
		HttpURLConnection con=null;
		BufferedReader br=null;
		try {
			//la chiamata ritorna tutti gli utenti su DB
			url = new URL(ConnectBackEnd.getInstance().getDomain() + "/utenti/"+String.valueOf((int)(Math.random()*10000))+"");
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
		DocuDiRegis docu = new DocuDiRegis("Mario", "Rossi", String.valueOf((int)(Math.random()*10000))+"@email.it", String.valueOf((int)(Math.random()*10000)), "abc", "abc", LocalDate.of(2011, Month.MARCH, 3), "06", "Roma", "RM");
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
		DocuDiRegis docu = new DocuDiRegis("Mario", "Rossi", "mariorossi@email.it", "marione", "abc", "abc", LocalDate.of(2011, Month.MARCH, 3), "06", "Roma", "");
		
		URL url=null;
		HttpURLConnection con=null;
		BufferedReader br=null;
		try {
			url = new URL("http://localhost:8080/utenti/"+docu.toString()+"");
		
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			br=new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line=br.readLine();
			assertFalse(Boolean.parseBoolean(line));
			
			con.disconnect();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
