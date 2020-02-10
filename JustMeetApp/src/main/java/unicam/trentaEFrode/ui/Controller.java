package unicam.trentaEFrode.ui;

import com.gluonhq.charm.down.Platform;
import com.gluonhq.charm.glisten.afterburner.GluonPresenter;
import com.gluonhq.charm.glisten.mvc.View;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import unicam.trentaEFrode.MainClass;
import unicam.trentaEFrode.exceptions.CodiciRisposte;

import java.util.List;



public abstract class Controller extends GluonPresenter<MainClass> {
	
	
	/**
	 * Visualizza i messaggi corrispondenti ai codici contenuti nella lista passata, considerandone la 
	 * priorita'.
	 * @param lista: la lista dei codici associati ai messaggi.
	 * */
	protected String visualizzaRisposta(List<Integer> lista){
		String str= "";
		CodiciRisposte cr = new CodiciRisposte();
		if(lista.indexOf(-1)!=-1) str = cr.getMessageOf(-1);
		else if(lista.indexOf(0)!=-1) str = cr.getMessageOf(0);
		else if(lista.indexOf(5)!=-1) str = cr.getMessageOf(5);
		else for(Integer n: lista) str += cr.getMessageOf(n) + "\n";
		return str;
	}
	
	public void addAlert() {
		Pane pane=new Pane();
		pane.setLayoutX(100);
		pane.setLayoutY(100);
		
		Label label=new Label();
		label.setText("Nessuna connessione,controllare");
		label.setFont(new Font(java.awt.Font.SANS_SERIF,20));
		pane.getChildren().add(label);
		
		javafx.application.Platform.runLater(()->{
			getMainView().getChildren().add(pane);
		});
		
	}
	
	/**
	 * @return
	 */
	public abstract View getMainView();

}
