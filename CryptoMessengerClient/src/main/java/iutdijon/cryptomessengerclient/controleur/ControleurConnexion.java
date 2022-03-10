package iutdijon.cryptomessengerclient.controleur;

import iutdijon.cryptomessengerclient.App;
import iutdijon.cryptomessengerclient.network.Network;
import iutdijon.cryptomessengerclient.network.Settings;
import iutdijon.cryptomessengerclient.vue.ecranconnexion.EcranConnexion;

/**
 * Controleur pour la connexion au serveur
 * @author simonetma
 */
public class ControleurConnexion extends Controleur {

    private EcranConnexion ecran;       //Ecran porteur
    
    /**
     * Constructeur
     * @param ecran écran porteur 
     */
    public ControleurConnexion(EcranConnexion ecran) {
        this.ecran = ecran;
    }
    
    @Override
    public void avertir() {
        //On change les settings
        Settings.setNomUtilisateur(ecran.getNomUtilisateur());
        Settings.setIpServeur(ecran.getIpServeur());
        //Connection au serveur
        try {
            Network.connexion();
            App.switchScene();
        } catch (Exception ex) {
            System.err.println("ERREUR : Connexion impossible");
        }
    }
    
}
