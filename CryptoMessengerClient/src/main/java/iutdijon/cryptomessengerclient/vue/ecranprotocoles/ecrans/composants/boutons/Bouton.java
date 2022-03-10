package iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.boutons;

import iutdijon.cryptomessengerclient.controleur.Controleur;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

/**
 *
 * @author simonetma
 */
public class Bouton extends Button {
    
    /**
     * Bouton de base
     * @param texte texte au centre du bouton
     * @param controleur controleur averti lors du click
     */
    public Bouton(String texte, Controleur controleur) {
        super(texte);
        this.setFont(new Font(this.getFont().getName(),20));
        this.setAlignment(Pos.CENTER);
        this.setOnMouseClicked((event) -> controleur.avertir());
    }
    
}
