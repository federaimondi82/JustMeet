package unicam.trentaEFrode.ui;

import java.net.ConnectException;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import unicam.trentaEFrode.Setup;
import unicam.trentaEFrode.domain.mainElements.Categoria;
import unicam.trentaEFrode.domain.mainElements.ConnectBackEnd;
import unicam.trentaEFrode.domain.mainElements.DocuDiRegis;
import unicam.trentaEFrode.domain.mainElements.GestoreRegistrazioni;
import unicam.trentaEFrode.domain.mainElements.RegistroCategorie;
import unicam.trentaEFrode.domain.parsers.ParserCategorie;

/**
 * Rappresenta il controller (patter MVC) della pagina di registrazione.
 * 
 * Inizializza i componenti della View, richiama i possibili interessi che scegliera' l'utente, utilizza<br>
 * il singleton {@link ConnectBackEnd} per inviare/ricevere i dati al backend
 * @author feder
 *
 */
public class Registrazione extends Controller {

    @FXML
    private View viewRegistrazione;

    @FXML
    private Button btn_registrazione;

    @FXML
    private TextField campoNome,campoCognome,campoEmail,campoNickname,campocitta,campoCap;

    @FXML
    private PasswordField campoPassword, campoRipetiPassword;

    @FXML
    private Label messaggio;

    @FXML
    private DatePicker campoData;
    
    @FXML
    private ListView<Categoria> listCaratteristiche,listInteressi;  

    public void initialize(){
        viewRegistrazione.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e ->
                        MobileApplication.getInstance().getDrawer().open()));
                appBar.setTitleText("Registrazione");
            }
        });
        
      
        listCaratteristiche.setItems(caricaPossibiliInteressi());
      		
    }

    
    /**
     * Si connette al database,carica le caratteristiche per gli eventi e li inserisce nella listView
     */
    private ObservableList<Categoria> caricaPossibiliInteressi() {
    	
		try {
			//prepara l'observer per la listView
      		ObservableList<Categoria> items= FXCollections.observableArrayList();
      		
      		//richia le categorie dal server	
			for(Categoria cat : RegistroCategorie.getInstance().caricaCategorie()) {
				items.add(cat);
			}
			return items;     		
		} catch (ConnectException e) {
			e.printStackTrace();
		}
		return null;		
	}

	/**
     * Funzione avviata al click dell'utente sul @btn_registrazione.
     * Crea un oggetto di tipo @DocuDiRegis, lo passa al registratore per i controlli e viene visualizzato
     * il risultato di questi controlli.
     * @throws ConnectException 
     * */
    @FXML
    private void confermaRegistrazione(ActionEvent event) throws ConnectException {
    	DocuDiRegis docu=new DocuDiRegis(
                campoNome.getText(),
                campoCognome.getText(),
                campoEmail.getText(),
                campoNickname.getText(),
                campoPassword.getText(),
                campoRipetiPassword.getText(),
                campoData.getValue(),
                campoCap.getText(),
                campocitta.getText());
    	
    	
        docu.setInteressi(ParserCategorie.getInstance().parseCategorieToServer(listInteressi.getItems()));        
        
        messaggio.setText(visualizzaRisposta(GestoreRegistrazioni.getInstance().effettuaControlli(docu)));
        
        //TODO chiedere a Veronica per migliorare
        if(messaggio.getText().equals("Operazione andata a buon fine!")) {
        	try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	Setup.getInstance().loadCacheFile();
        }
        
    }
    
    /**
     * Consente di aggiungere alcuni interessi alla lista prima della registrazione
     * @param event
     */
    @FXML
    private void aggiungiInteresse(ActionEvent event) {
    	Categoria c=listCaratteristiche.getSelectionModel().getSelectedItem();
		
		if(c!=null) {
			listCaratteristiche.getItems().remove(c);
			listInteressi.getItems().add(c);
		}
    }


    /**
     * Consente di togliere gli interessi dalla lsta prima della registrazione
     * @param event
     */
    @FXML
    private void togliInteresse(ActionEvent event) {
    	Categoria c=listInteressi.getSelectionModel().getSelectedItem();
		
    	if(c!=null) {
    		listInteressi.getItems().remove(c);
    		listCaratteristiche.getItems().add(c);
    	}
    }

	@Override
	public View getMainView() {
		return viewRegistrazione;
	}

	
}
