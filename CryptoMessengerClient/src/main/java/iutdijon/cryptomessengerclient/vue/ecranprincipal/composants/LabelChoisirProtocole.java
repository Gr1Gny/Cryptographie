package iutdijon.cryptomessengerclient.vue.ecranprincipal.composants;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Label du choix de protocole
 * @author simonetma
 */
public class LabelChoisirProtocole extends Label {
    
    /**
     * Constructeur
     */
    public LabelChoisirProtocole() {
        super("Choisir le protocole de communication :");
        this.setFont(new Font(this.getFont().getName(),20));
        this.setAlignment(Pos.BASELINE_LEFT);
    }
}
