package unicam.trentaEFrode.ui;

import java.net.ConnectException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import unicam.trentaEFrode.domain.mainElements.*;
import unicam.trentaEFrode.exceptions.CategoriaInesistente;

public class CreazioneEvento  extends Controller{

	@FXML
	private View creaEventoView;

	@FXML
	private TextField campoNome,campocitta,campoIndirizzo,campoNumeroCivico,campoCap,campoProvincia,campoNomeLuogo;

	@FXML
	private TextArea campoDescrizione;

	@FXML
	private Spinner<Integer> campoMin,campoMax,campoOra,campoMinuto,campoDurata,campoGiorno,campoMese,campoAnno;

	@FXML
	private ListView<Categoria> campoCategoria;

	@FXML
	private Label messaggio;

	@FXML
	private Button btn_creaEvento;

	/*
	@FXML
	private VBox verticalBox;

	private ScrollPane scroll;
	private VBox vBox;
	private ListView<String> campoCategoria;
	private Button btn_creaEvento;
	private Label messaggio;
	*/

	/**
	 *
	 */
	public void initialize() {
			try{
			creaEventoView.showingProperty().addListener((obs, oldValue, newValue) -> {
				if (newValue) {
					AppBar appBar = MobileApplication.getInstance().getAppBar();
					appBar.setNavIcon(MaterialDesignIcon.MENU.button(e ->
							MobileApplication.getInstance().getDrawer().open()));
					appBar.setTitleText("crea Evento");
				}
			});

			SpinnerValueFactory<Integer> valoriOra = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
			SpinnerValueFactory<Integer> valoriMinuti = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
			SpinnerValueFactory<Integer> valoriMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 500, 0);
			SpinnerValueFactory<Integer> valoriMax = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 3000, 0);
			SpinnerValueFactory<Integer> valoriDurata = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, (7*24), 0);
			SpinnerValueFactory<Integer> valoriGiorno = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 31, LocalDate.now().getDayOfMonth());
			SpinnerValueFactory<Integer> valoriMese = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, LocalDate.now().getMonthValue());
			SpinnerValueFactory<Integer> valoriAnno = new SpinnerValueFactory.IntegerSpinnerValueFactory(LocalDate.now().getYear(), LocalDate.now().getYear() + 5 , LocalDate.now().getYear());

			campoOra.setValueFactory(valoriOra);
			campoMinuto.setValueFactory(valoriMinuti);
			campoMin.setValueFactory(valoriMin);
			campoMax.setValueFactory(valoriMax);
			campoDurata.setValueFactory(valoriDurata);
			campoGiorno.setValueFactory(valoriGiorno);
			campoMese.setValueFactory(valoriMese);
			campoAnno.setValueFactory(valoriAnno);
		
		//richiamo le categorie dal server
		List<Categoria> categorie = RegistroCategorie.getInstance().getInteressi();
		//preparo l'observer per la listView
		ObservableList<Categoria> items= FXCollections.observableArrayList();
		items.addAll(categorie);
		campoCategoria.setItems(items);
		
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public void confermaCreaEvento() throws ConnectException {
		//try {
			GregorianCalendar data = new GregorianCalendar(campoAnno.getValue(), campoMese.getValue(), campoGiorno.getValue(), campoOra.getValue(), campoMinuto.getValue());
			Luogo luogo = new Luogo(campoNomeLuogo.getText(), campoIndirizzo.getText(), campoNumeroCivico.getText(), campoCap.getText(), campocitta.getText(), campoProvincia.getText());
			Categoria category=campoCategoria.getSelectionModel().getSelectedItem();
			Evento evento = new Evento()
					.setNome(campoNome.getText())
					.setDataOra(data)
					.setMinPartecipanti(campoMin.getValue())
					.setMaxPartecipanti(campoMax.getValue())
					.setDescrizione(campoDescrizione.getText())
					.setLuogo(luogo)
					.setDurata(campoDurata.getValue())
					.setCategoria(category);

			/*
			Evento evento= new Evento(campoNome.getText(),new GregorianCalendar(campoAnno.getValue(), campoMese.getValue(), campoGiorno.getValue(), campoOra.getValue(), campoMinuto.getValue()),
					campoMin.getValue(),campoMax.getValue(),campoDescrizione.getText(),campoDurata.getValue(),
					new Luogo(campoNomeLuogo.getText(), campoIndirizzo.getText(), campoNumeroCivico.getText(), campoCap.getText(), campocitta.getText(), campoProvincia.getText()),
					RegistroCategorie.getInstance().getCategoria(campoCategoria.getSelectionModel().getSelectedItem()));
			System.out.println("evento"+evento.toString());
*/
		messaggio.setText(visualizzaRisposta(GestoreEventi.getInstance().effettuaControlli(evento)));
//		} catch (CategoriaInesistente e) {
//			e.printStackTrace();
//		}
	}

	
	@Override
	public View getMainView() {
		return creaEventoView;
	}



}