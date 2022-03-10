package iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.textzones;

import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

/**
 * Zone de texte pour la saise d'un message
 * @author simonetma
 */
public class TextMessage extends TextArea {
    
    /**
     * Constructeur
     * @param texteParDefaut texte par défaut 
     */
    public TextMessage(String texteParDefaut) {
        super();
        this.setPromptText(texteParDefaut);
        this.setFont(new Font(this.getFont().getName(),15));
        this.setWrapText(true);
    }
}
