package iutdijon.cryptomessengerclient.vue.ecranprincipal.panel.composants;

import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import iutdijon.cryptomessengerclient.network.Network;
import iutdijon.cryptomessengerclient.vue.ecranprincipal.panel.composants.menucontextuel.McFabrique;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Panel d'affichage d'un protocole
 * @author simonetma
 */
public class PanelProtocole extends HBox {
    
    /**
     * Constructeur
     * @param protocole nom du protocole 
     */
    public PanelProtocole(NomProtocole protocole) {
        //Icone du protocole
        ImageView icone = new ImageView(protocole.getImage());
        icone.setSmooth(true);
        icone.setPreserveRatio(true);
        icone.fitWidthProperty().bind(this.widthProperty().divide(6));
        this.getChildren().add(icone);
        //Label du nom
        Label labelNomProtocole = new LabelNomProtocole(protocole);
        labelNomProtocole.prefWidthProperty().bind(this.widthProperty().subtract(this.heightProperty()));
        labelNomProtocole.prefHeightProperty().bind(icone.fitWidthProperty());
        this.getChildren().add(labelNomProtocole);
        //fond
        this.setStyle("-fx-background-color:white;");
       
        if(Network.estConnecte()) {
            this.setOnMouseEntered((event) -> setStyle("-fx-background-color:lightblue;"));
            this.setOnMouseExited((event) -> setStyle("-fx-background-color:white;"));
            ContextMenu menu = McFabrique.create(protocole,this);
            menu.setAutoHide(true);
            //labelNomProtocole.setContextMenu(menu);
            this.setOnContextMenuRequested((event) -> menu.show(icone.getScene().getWindow(),event.getScreenX(),event.getScreenY()));
            
            //this.setOnMouseClicked((event) -> FabriqueEcranProtocole.create(protocole, this.getScene()));
        }
    }
    
    
}
