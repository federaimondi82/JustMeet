package trentaEFrode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ConnectException;
import java.util.List;

import org.junit.Test;

import unicam.trentaEFrode.domain.mainElements.ConnectBackEnd;
import unicam.trentaEFrode.domain.mainElements.RegistroStatico;
import unicam.trentaEFrode.domain.parsers.ParserUser;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;

public class TestStart {

	
	@Test
	public final void testConnessione(){
					
		boolean s1=false;

		String s="";
		try {
			s = ConnectBackEnd.getInstance().restRequest("/testConnessione/", "GET");
			
			s1=Boolean.parseBoolean(s);		
			
			System.out.println("connessione ok");
			
		} catch (Exception e) {
			System.out.println("nessuna connessione");
		}
		

		
	}
	
	
	@Test
	public final void testFileCacheExists() {
		//preparo le istanze per la creazione del file di cache
		String path=RegistroStatico.getInstance().getPath();
		File f= new File(path);
		try {
			f.createNewFile();
		
			//tenta di leggere il file
			FileWriter writer=new FileWriter(path);
			BufferedWriter bw=new BufferedWriter(writer);
			
			String frase="scrivi qualcosa anche tu";
			bw.write(frase);			
			bw.flush();	
			bw.close();
		
			
			//lettura del file
			boolean a=RegistroStatico.getInstance().cacheFileExists();
			if(a==true) {
				
				File f2=RegistroStatico.getInstance().getCacheFile();
				
				BufferedReader br=new BufferedReader(new FileReader(path));
				
				br.lines().forEach(line->{
					System.out.println(line.toString());
				});
				
				br.close();
				
			}else {
				System.out.println("file non esiste");
			}
		
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertTrue(!RegistroStatico.getInstance().cacheFileExists());
	}
	
	@Test
	public final void testAutenticazione() {
		try {
			String s=ConnectBackEnd.getInstance()
					.restRequest("/utenti/auth/"+"fede_7_@gmail.com"+":"+"688787D8FF144C502C7F5CFFAAFE2CC588D86079F9DE88304C26B0CB99CE91C6", "GET");
			

			ParserUser.getInstance().parseUtenteFromServer(s);
			assertTrue(UtenteRegistrato.getInstance().getCap()!=null);
			
		} catch (ConnectException e) {
			e.printStackTrace();
		}
	}

}
