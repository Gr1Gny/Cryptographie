package iutdijon.cryptomessengerclient.vue.ecranprincipal.panel.composants.menucontextuel;

import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.FabriqueEcranProtocole;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

/**
 * Menu contextuel pour le protocole Texte Brut
 * @author simonetma
 */
public class McBrut extends ContextMenu {
    
    /**
     * COnstructeur
     * @param node origine du menu
     */
    public McBrut(Node node) {
        //Création des items
        MenuItem itemEnvoi = new MenuItem("Envoyer un message");
        MenuItem itemReception = new MenuItem("Recevoir un message");
        this.getItems().addAll(itemEnvoi,itemReception);
        
        //Actions
        itemEnvoi.setOnAction((event) -> FabriqueEcranProtocole.create(NomProtocole.BRUT, "ENVOI", node.getScene()));
        itemReception.setOnAction((event) -> FabriqueEcranProtocole.create(NomProtocole.BRUT, "RECEPTION", node.getScene()));
    }
    
}
