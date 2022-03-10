package iutdijon.cryptomessengerclient.vue.ecranprincipal.composants;

import iutdijon.cryptomessengerclient.network.Network;
import iutdijon.cryptomessengerclient.network.Settings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Label du nom de l'utilisateur
 * @author simonetma
 */
public class LabelNomUtilisateur extends Label {
    
    /**
     * Constructeur
     */
    public LabelNomUtilisateur() {
        super(Settings.getNomUtilisateur());
        if(!Network.estConnecte()) {
            this.setText("NON CONNECTE !");
        }
        this.setFont(new Font(this.getFont().getName(),40));
        this.setAlignment(Pos.CENTER);
    }
}
