package iutdijon.cryptomessengerclient;

import iutdijon.cryptomessengerclient.vue.ImagesManager;
import iutdijon.cryptomessengerclient.vue.ecranconnexion.EcranConnexion;
import iutdijon.cryptomessengerclient.vue.ecranprincipal.EcranPrincipal;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage stage;
    
    @Override
    public void start(Stage stage) {
        App.stage = stage;
        //Démarrage de l'IHM
        stage.setTitle("Crypto Messenger - Client");
        stage.setScene(new EcranConnexion());
        stage.setResizable(false);
        stage.getIcons().add(ImagesManager.getImage("Icon"));
        stage.show();
        
    }

    public static void demarre(String[] args) {
        launch();
    }
    
    /**
     * Switch la scène vers l'écran principal après la connexion
     */
    public static void switchScene() {
        if(App.stage != null) {
            App.stage.setScene(new EcranPrincipal());
            App.stage.centerOnScreen();
        }
    }
    
}