package iutdijon.cryptomessengerclient.vue.ecranprincipal.panel;

import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import iutdijon.cryptomessengerclient.vue.ecranprincipal.panel.composants.PanelProtocole;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * ScrollPanel contenant la liste des protocoles
 * @author simonetma
 */
public class ListeProtocoles extends ScrollPane {
    
    private VBox contenu;       //Panel interieur
    
    public ListeProtocoles() {
        //Gestion des scrollbar
        this.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        this.setHbarPolicy(ScrollBarPolicy.NEVER);
        
        //Panel content
        this.contenu = new VBox(5);
        this.contenu.prefWidthProperty().bind(this.widthProperty());
        this.contenu.minHeightProperty().bind(this.heightProperty());
        this.contenu.setStyle("-fx-background-color:white");
        this.contenu.setPadding(new Insets(5,20,5,5));
        this.setContent(this.contenu);
        
        //Chargement des protocoles
        for(NomProtocole protocole : NomProtocole.values()) {
            this.ajouterProtocole(protocole);
        }
    }
    
    /**
     * Ajooute un protocole à la liste
     * @param protocole le protocole à ajouter
     */
    private void ajouterProtocole(NomProtocole protocole) {
        PanelProtocole panel = new PanelProtocole(protocole);
        panel.prefWidthProperty().bind(this.widthProperty());
        panel.prefHeightProperty().bind(this.widthProperty().divide(6));
        this.contenu.getChildren().add(panel);
    }
    
}
