package iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.labels;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Label simple pour un élément écrit dans un écran de protocole
 * @author simonetma
 */
public class LabelElement extends Label {
    
    public LabelElement(String texte) {
        super(texte);
        this.setFont(new Font(this.getFont().getName(),20));
        this.setAlignment(Pos.CENTER_LEFT);
    }
}
