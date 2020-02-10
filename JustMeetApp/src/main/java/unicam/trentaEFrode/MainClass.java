package unicam.trentaEFrode;

import com.gluonhq.cloudlink.client.user.UserClient;
import unicam.trentaEFrode.ui.AppViewManager;


import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.visual.Swatch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainClass extends MobileApplication {

    private UserClient authenticationClient;

    @Override
    public void init() {
        //authenticationClient = new UserClient();
        AppViewManager.registerViewsAndDrawer(this);
    }

    @Override
    public void postInit(Scene scene) {
        try{
            Swatch.BLUE.assignTo(scene);

            ((Stage) scene.getWindow()).getIcons().add(new Image(MainClass.class.getResourceAsStream("/icon.png")));

            //AppViewManager.AUTH.switchView();
            
           // Setup.getInstance().loadCacheFile();
            
           // Setup.getInstance().check_connection();
            
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public UserClient getUserClient() {
        return authenticationClient;
    }
}
