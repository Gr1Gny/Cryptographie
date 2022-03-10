package iutdijon.cryptomessengerclient.vue.ecranprincipal.panel.composants.menucontextuel;

import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;

/**
 * Fabrique des menus contextuels
 * @author simonetma
 */
public class McFabrique {
    
    /**
     * Création du menu contextuel
     * @param protocole protocole sélectionné par l'utilisateur
     * @param node origine du menu
     * @return le menu contextuel pour ce protocole
     */
    public static ContextMenu create(NomProtocole protocole,Node node) {
        ContextMenu menu = null;
        switch(protocole) {
            case BRUT: menu = new McBrut(node);  break;
            case CESAR: case SUBSTITUTION: case TRANSPOSITION : case VIGENERE: case FEISTEL: menu = new McSymetrique(node,protocole); break;
            case RLE: case HUFFMAN: menu = new McSymetrique(node,protocole); break;
        }
        return menu;
    }
    
}
