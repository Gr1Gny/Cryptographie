package iutdijon.cryptomessengerclient.vue.ecranprincipal;

import iutdijon.cryptomessengerclient.vue.ecranprincipal.composants.*;
import iutdijon.cryptomessengerclient.vue.ecranprincipal.panel.ListeProtocoles;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

/**
 * Scene principal du programme
 * @author simonetma
 */
public class EcranPrincipal extends Scene {
    
    private VBox root;      //Panel principal
    
    /**
     * Constructeurs
     */
    public EcranPrincipal() {
        this(new VBox(5));
    }
    
    private EcranPrincipal(VBox root) {
        super(root,500,800);
        //Panel de fond
        this.root = root;
        this.root.prefWidthProperty().bind(this.widthProperty());
        this.root.prefHeightProperty().bind(this.heightProperty());
        this.root.setPadding(new Insets(5,5,5,5));
        
        //Création des composants
            //Nom de l'utilisateur
        Label labelNomUtilisateur = new LabelNomUtilisateur();
        labelNomUtilisateur.prefWidthProperty().bind(root.widthProperty());
        root.getChildren().add(labelNomUtilisateur);
            //Séparateur
        Separator separator = new Separator();
        root.getChildren().add(separator);
            //Choix du protocole
        Label labelChoixProtocole = new LabelChoisirProtocole();
        labelChoixProtocole.prefWidthProperty().bind(root.widthProperty());
        labelChoixProtocole.prefHeightProperty().bind(labelNomUtilisateur.heightProperty());
        root.getChildren().add(labelChoixProtocole);
            //Liste des protocoles
        ListeProtocoles listeProtocole = new ListeProtocoles();
        listeProtocole.prefWidthProperty().bind(root.widthProperty());
        listeProtocole.prefHeightProperty().bind(root.heightProperty().subtract(labelChoixProtocole.heightProperty()).subtract(separator.heightProperty()).subtract(labelNomUtilisateur.heightProperty()));
        root.getChildren().add(listeProtocole);
    }
    
}
