package iutdijon.cryptomessengerclient.vue.ecranconnexion.composants.labels;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Label de titre d'un écran de protocole
 * @author simonetma
 */
public class LabelTitre extends Label {
    
    public LabelTitre(String titre) {
        super(titre);
        this.setFont(new Font(this.getFont().getName(),40));
        this.setAlignment(Pos.CENTER);
    }
    
}
