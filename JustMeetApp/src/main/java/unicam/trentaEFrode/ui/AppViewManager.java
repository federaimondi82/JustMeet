package unicam.trentaEFrode.ui;

import com.gluonhq.charm.glisten.afterburner.AppView;
import com.gluonhq.charm.glisten.afterburner.AppViewRegistry;
import com.gluonhq.charm.glisten.afterburner.GluonPresenter;
import com.gluonhq.charm.glisten.afterburner.Utils;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import unicam.trentaEFrode.MainClass;
import javafx.scene.image.Image;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

import static com.gluonhq.charm.glisten.afterburner.AppView.Flag.HOME_VIEW;
import static com.gluonhq.charm.glisten.afterburner.AppView.Flag.SHOW_IN_DRAWER;
import static com.gluonhq.charm.glisten.afterburner.AppView.Flag.SKIP_VIEW_STACK;

public class AppViewManager {

    public static final AppViewRegistry REGISTRY = new AppViewRegistry();

    public static final AppView HOME_PAGE = view("Home", Home.class, MaterialDesignIcon.HOME,SHOW_IN_DRAWER,HOME_VIEW);
    public static final AppView CREA_EVENTO = view("Crea un evento", CreazioneEvento.class, MaterialDesignIcon.HOME,SHOW_IN_DRAWER);
    public static final AppView REGISTRAZIONE = view("Registrati", Registrazione.class, MaterialDesignIcon.HOME);
    public static final AppView MODIFICA_EVENTO = view("Modifica evento", ModificaEvento.class, MaterialDesignIcon.HOME);
    public static final AppView AUTENTICAZIONE = view("Autenticazione", Autenticazione.class, MaterialDesignIcon.HOME);

    private static AppView view(String title, Class<? extends GluonPresenter<?>> presenterClass, MaterialDesignIcon menuIcon, AppView.Flag... flags ) {
        return REGISTRY.createView(name(presenterClass), title, presenterClass, menuIcon, flags);
    }

    private static String name(Class<? extends GluonPresenter<?>> presenterClass) {
        return presenterClass.getSimpleName().toUpperCase(Locale.ROOT).replace("PRESENTER", "");
    }
    
    public static void registerViewsAndDrawer(MobileApplication app) {
        for (AppView view : REGISTRY.getViews()) {
            view.registerView(app);
        }

        NavigationDrawer.Header header = new NavigationDrawer.Header("Just meet",
                "Multi View Project",
                new Avatar(21, new Image(MainClass.class.getResourceAsStream("/icon.png"))));

        Utils.buildDrawer(app.getDrawer(), header, REGISTRY.getViews()); 
    }
    
    public static Collection<AppView> getView() {
    	return REGISTRY.getViews();
    }
    
}
