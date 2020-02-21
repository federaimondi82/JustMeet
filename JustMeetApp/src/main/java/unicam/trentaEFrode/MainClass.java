package unicam.trentaEFrode;

import com.gluonhq.cloudlink.client.user.UserClient;
import unicam.trentaEFrode.ui.AppViewManager;


import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.visual.Swatch;


public class MainClass extends MobileApplication {


    @Override
    public void init() {
        AppViewManager.registerViewsAndDrawer(this);
    }

    @Override
    public void postInit(Scene scene) {
        try{
            Swatch.BLUE.assignTo(scene);

            ((Stage) scene.getWindow()).getIcons().add(new Image(MainClass.class.getResourceAsStream("/icon.png")));

            
           // Setup.getInstance().loadCacheFile();
            
           // Setup.getInstance().check_connection();
            
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
