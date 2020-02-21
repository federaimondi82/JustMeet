package unicam.trentaEFrode.ui;

import java.net.ConnectException;
import java.time.LocalDate;
import java.util.List;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.SpinnerValueFactory;
//import javafx.scene.layout.VBox;
import unicam.trentaEFrode.domain.mainElements.ConnectBackEnd;
import unicam.trentaEFrode.domain.mainElements.GestoreRegistrazioni;
import unicam.trentaEFrode.domain.mainElements.RegistroCategorie;
import unicam.trentaEFrode.domain.mainElements.RegistroStatico;
import unicam.trentaEFrode.domain.parsers.ParserUser;
import unicam.trentaEFrode.domain.users.UtenteRegistrato;

public class Autenticazione extends Controller{

	 @FXML
    private View autenticazioneView;

    @FXML
    private VBox vBox;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPass;

    @FXML
    private Label messaggio;
    
    @FXML
    private Button btn_Signin;

    

    
    public void initialize() {

		autenticazioneView.showingProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue) {
				AppBar appBar = MobileApplication.getInstance().getAppBar();
				appBar.setNavIcon(MaterialDesignIcon.MENU.button(e ->
						MobileApplication.getInstance().getDrawer().open()));
				appBar.setTitleText("Autenticazione");
			}
		});		
    }
    
    @FXML
    void signin(ActionEvent event) {
    	try {
    		//preparazione della stringa da inviare
    		String credenziali=txtEmail.getText()+":"+GestoreRegistrazioni.getInstance().codificaPassword(txtPass.getText(), "SHA-256");
    		
    		String nomeUtente=resultFromServer(credenziali);
			
			messaggio.setText("Bentornato "+nomeUtente);
			
			memorizzaUtenteRegistrato();
			memorizzaInteressi();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			AppViewManager.HOME_PAGE.switchView();
		} catch (NumberFormatException e) {
			messaggio.setText("qualcosa e' andato storto,riprova");
			e.printStackTrace();
		}
    }
    
    
	private void memorizzaInteressi() {
		RegistroStatico.getInstance().fileWriter(UtenteRegistrato.getInstance().toStringInteressi(),true);
		
	}

	/**
	 * Memorizza sul file di cache le creadenziali dell'utente che si e' appena autenticato
	 */
	private void memorizzaUtenteRegistrato() {
		RegistroStatico.getInstance().fileWriter(UtenteRegistrato.getInstance().toString(),false);
		
	}

	/**
	 * Invia la request al server il quale risponde con le credenziali dell'utente;<br>
	 * Viene cosi' creato L'utenteRegistrato
	 * @param credenziali l'email e la password scritte sulla gui
	 * @return il nome dell'utenteregistrato
	 */
	private String resultFromServer(String credenziali) {
		//invio e ritorno della request
		String s="";
		try {
			s = ConnectBackEnd.getInstance().restRequest("/utenti/auth/"+credenziali, "GET");
				
			//parsing della stringa di ritorno,va a creare l'utente Registrato e i sui interessi
			ParserUser.getInstance().parseUtenteFromServer(s);
		} catch (ConnectException e) {
			e.printStackTrace();
		}
		return UtenteRegistrato.getInstance().getNome();
	}

	@Override
	public View getMainView() {
		// TODO Auto-generated method stub
		return null;
	}

}
