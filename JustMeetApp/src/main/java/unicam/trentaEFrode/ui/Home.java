package unicam.trentaEFrode.ui;

import java.time.LocalDate;
import java.util.GregorianCalendar;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SpinnerValueFactory;
import unicam.trentaEFrode.domain.mainElements.RegistroCategorie;
import unicam.trentaEFrode.domain.mainElements.RegistroStatico;

public class Home extends Controller{

    @FXML
    private View homeView;

    @FXML
    private Label messaggio;
    
    public void initialize() {

    	homeView.showingProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue) {
				AppBar appBar = MobileApplication.getInstance().getAppBar();
				appBar.setNavIcon(MaterialDesignIcon.MENU.button(e ->
						MobileApplication.getInstance().getDrawer().open()));
				appBar.setTitleText("Benvenuto");
			}
		});

		
	}

    @Override
	public View getMainView() {
		return homeView;
	}
}
