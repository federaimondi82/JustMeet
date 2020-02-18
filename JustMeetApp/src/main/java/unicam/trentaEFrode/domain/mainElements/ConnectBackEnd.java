package unicam.trentaEFrode.domain.mainElements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import unicam.trentaEFrode.Setup;

/**
 * Mette a dispsizione dei metodi per l'invio di chimate REST verso la parte backend
 * E' interfaccia verso l'esterno per la parte front end
 * @author feder
 *
 */
public class ConnectBackEnd {
	
	private static ConnectBackEnd instance;
	private String domain;
	private int port;
	
	
	private URL url;
	private HttpURLConnection con;
	private BufferedReader br;
	
	
	private ConnectBackEnd() {
		this.domain="http://localhost";
		this.port=8080;
	}
	
	public static ConnectBackEnd getInstance() {
		if(instance==null) instance=new ConnectBackEnd();
		return instance;
	}
	
	public String getDomain() {
		return this.domain+":"+String.valueOf(this.port);
	}
	
	/**Consente di effettuare una chiamata http usando i metodi post e put;
	 * @param path il path univoco per fare la chiamata rest
	 * @param method il metodo http da applicare
	 * @param obj un elemento da inserire nel database
	 * @return ritorna la risposta dal server
	 */
	public boolean restRequest(String path,String method, String obj) {
		
		//prima della chiamata REST al server viene testata la connessione
		boolean result = Setup.getInstance().check_connection();

		if(result) {
			String list="";
			try {
				this.url = new URL(getDomain()+path+obj);	
				
				this.con = (HttpURLConnection) url.openConnection();
				this.con.setRequestMethod(method);
				
				this.br=new BufferedReader(new InputStreamReader(this.con.getInputStream()));
				
				list = this.br.readLine();
				result = Boolean.parseBoolean(list);
				
				this.con.disconnect();
				
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return result;
		
	}
	
	/**Consente di effettuare una chiamata http usando i metodi get;
	 * 
	 * @param path il path univoco per fare la chiamata rest
	 * @param method il metodo http da applicare
	 * @return ritorna la risposta dal server
	 */
	public String restRequest(String path, String method){
			
		String s="";
		
		try {
			this.url = new URL(getDomain()+path);
		
			this.con = (HttpURLConnection) url.openConnection();
			this.con.setRequestMethod(method);
			this.br=new BufferedReader(new InputStreamReader(this.con.getInputStream()));
			s=this.br.readLine();
			this.con.disconnect();
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return s;
		
	}
	

}
