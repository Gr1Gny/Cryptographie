package iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.labels;

import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Label de titre d'un écran de protocole
 * @author simonetma
 */
public class LabelTitreProtocole extends Label {
    
    public LabelTitreProtocole(NomProtocole protocole) {
        super(protocole.getNom());
        this.setFont(new Font(this.getFont().getName(),40));
        this.setAlignment(Pos.CENTER);
    }
    
}
