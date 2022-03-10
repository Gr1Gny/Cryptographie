package iutdijon.cryptomessengerclient.controleur.observation;

/**
 *
 * @author simonetma
 */
public interface Observateur {
    /**
     * Avertir l'observateur d'une modification
     * @param message message envoyé par l'observable
     */
    public void avertir(String message);
}
