package iutdijon.cryptomessengerclient.controleur.observation;

import java.util.ArrayList;

/**
 * Objet Observable
 * @author simonetma
 */
public class Observable {
    private ArrayList<Observateur> observateurs = new ArrayList<>();;    //Liste des observateurs
    
    /**
     * Ajoute un nouvel observateur à la liste
     * @param obs le nouvel observateur
     */
    public void inscrire(Observateur obs) {
        this.observateurs.add(obs);
    }
    
    /**
     * Retire un observateur de la liste
     * @param obs l'observateur à retirer
     */
    public void desinscrire(Observateur obs) {
        this.observateurs.remove(obs);
    }
    
    /**
     * Avertir tous les observateurs
     * @param message message à transmettre aux observateurs
     */
    public void avertir(String message) {
        for(Observateur obs : this.observateurs) {
            obs.avertir(message);
        }
    }
    
}
