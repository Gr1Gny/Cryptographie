package iutdijon.cryptomessengerclient.vue.ecranconnexion.composants.labels;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Label simple pour un élément écrit dans un écran de protocole
 * @author simonetma
 */
public class LabelElementConnexion extends Label {
    
    public LabelElementConnexion(String texte) {
        super(texte);
        this.setFont(new Font(this.getFont().getName(),20));
        this.setAlignment(Pos.CENTER_LEFT);
    }
}
