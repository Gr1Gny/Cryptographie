package iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.textzones;

import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * Zone de saisie de texte pour un élément
 * @author simonetma
 */
public class TextElement extends TextField {
    
    public TextElement(String texteParDefaut) {
        super();
        this.setPromptText(texteParDefaut);
        this.setFont(new Font(this.getFont().getName(),15));
    }
}
