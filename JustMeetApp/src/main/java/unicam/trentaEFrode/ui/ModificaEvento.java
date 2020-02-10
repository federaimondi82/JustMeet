package unicam.trentaEFrode.ui;

import java.net.ConnectException;
import java.net.URL;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.mainElements.GestoreEventi;
import unicam.trentaEFrode.domain.mainElements.Luogo;
import unicam.trentaEFrode.domain.mainElements.RegistroCategorie;
import unicam.trentaEFrode.domain.mainElements.RegistroStatico;
import unicam.trentaEFrode.exceptions.CategoriaInesistente;

public class ModificaEvento extends Controller{

	@FXML
	private View viewModificaEvento;
	
	@FXML
	private TextField campoNome, campoNomeLuogo,campoIndirizzo,campoNumeroCivico,campoCap, campocitta,campoProvincia;
	
	@FXML
	private Spinner<Integer> campoOra, campoMinuto, campoGiorno, campoMese, campoAnno, campoMin,campoMax,campoDurata;
	
	@FXML
	private TextArea campoDescrizione;
	
	@FXML
	private ListView<String> campoCategoria;
				
	@FXML
	private Label messaggio;

	private RegistroCategorie registro;

	private Evento evento;
	
	public void initialize() throws ConnectException {

		viewModificaEvento.showingProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue) {
				AppBar appBar = MobileApplication.getInstance().getAppBar();
				appBar.setNavIcon(MaterialDesignIcon.MENU.button(e ->
						MobileApplication.getInstance().getDrawer().open()));
				appBar.setTitleText("Modifica Evento");
			}
		});

		evento = RegistroStatico.getInstance().getLastClicked();
		SpinnerValueFactory<Integer> valoriOra = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, evento.dataOra().get(GregorianCalendar.HOUR));
		SpinnerValueFactory<Integer> valoriMinuti = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, evento.dataOra().get(GregorianCalendar.MINUTE));
		SpinnerValueFactory<Integer> valoriMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 500, evento.minPartecipanti());
		SpinnerValueFactory<Integer> valoriMax = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 3000, evento.maxPartecipanti());
		SpinnerValueFactory<Integer> valoriDurata = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, (7*24), evento.durata());
		SpinnerValueFactory<Integer> valoriGiorno = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 31, evento.dataOra().get(GregorianCalendar.DAY_OF_MONTH));
		SpinnerValueFactory<Integer> valoriMese = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, evento.dataOra().get(GregorianCalendar.MONTH));
		SpinnerValueFactory<Integer> valoriAnno = new SpinnerValueFactory.IntegerSpinnerValueFactory(LocalDate.now().getYear(), LocalDate.now().getYear() + 5 , evento.dataOra().get(GregorianCalendar.YEAR));

		campoOra.setValueFactory(valoriOra);
		campoMinuto.setValueFactory(valoriMinuti);
		campoMin.setValueFactory(valoriMin);
		campoMax.setValueFactory(valoriMax);
		campoDurata.setValueFactory(valoriDurata);
		campoGiorno.setValueFactory(valoriGiorno);
		campoMese.setValueFactory(valoriMese);
		campoAnno.setValueFactory(valoriAnno);
		registro = RegistroCategorie.getInstance();
		caricaCategorie();
		
	}
	
	private void caricaCategorie() {
		List<String> list = registro.listaNomiCategorie();
		campoCategoria.setItems((ObservableList<String>)list);
		campoCategoria.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}

	@FXML
	public void confermaModificaEvento(ActionEvent event) throws ConnectException {
		Evento nuovo = null;
		GestoreEventi g = GestoreEventi.getInstance();
		try {
			GregorianCalendar data=new GregorianCalendar(campoAnno.getValue(), campoMese.getValue(), campoGiorno.getValue(), campoOra.getValue(), campoMinuto.getValue());
			Luogo luogo= new Luogo(campoNomeLuogo.getText(), campoIndirizzo.getText(), campoNumeroCivico.getText(), campoCap.getText(), campocitta.getText(), campoProvincia.getText());

			nuovo = new Evento(evento.id())
					.setNome(campoNome.getText())
					.setDataOra(data)
					.setMinPartecipanti(campoMin.getValue())
					.setMaxPartecipanti(campoMax.getValue())
					.setDescrizione(campoDescrizione.getText())
					.setDurata(campoDurata.getValue())
					.setLuogo(luogo)
					.setCategoria(registro.getCategoria(campoCategoria.getSelectionModel().getSelectedItem()));

		} catch (CategoriaInesistente e) {}
		
		//TODO FEDERICO c'e' il backend?
		
		//METODO VERONICA
		//List<Integer> risposta = GestoreEventi.getInstance().modificaVeronica(evento, nuovo);
		// METODO FEDERICO
		List<Integer> risposta = g.effettuaControlli(nuovo);
		if(risposta.indexOf(-1)!=-1) {
			String testo = "L'evento " + evento.nome() + "e' stato cambiato da: \n " + evento.stringaCompleta() + "\n in: \n" + nuovo.stringaCompleta();
			risposta = g.notificaPartecipanti(nuovo, testo);
		}
		messaggio.setText(visualizzaRisposta(risposta));
	}

	@Override
	public View getMainView() {
		return viewModificaEvento;
	}
}