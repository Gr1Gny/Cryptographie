package iutdijon.cryptomessengerclient.vue.ecranprincipal.panel.composants;

import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Label du nom de protocole
 * @author simonetma
 */
public class LabelNomProtocole extends Label {
    
    /**
     * Constructeur
     * @param protocole le protocole
     */
    public LabelNomProtocole(NomProtocole protocole) {
        super(protocole.getNom());
        this.setFont(new Font(this.getFont().getName(),20));
        this.setAlignment(Pos.CENTER);
    }
}
