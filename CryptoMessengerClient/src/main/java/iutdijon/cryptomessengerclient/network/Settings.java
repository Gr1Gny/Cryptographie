package iutdijon.cryptomessengerclient.network;

/**
 * TODO : classe a coder par l'étudiant
 * Settings de la connextion réseau
 * @author simonetma
 */
public class Settings {
    private static String nomUtilisateur = "Bob";
    private static String ipServeur = "127.0.0.1";
    private static final int PORT_SERVEUR = 1234;

    /**
     * Renvoie le nom de l'utilisateur
     * @return le nom de l'tutilisateur
     */
    public static String getNomUtilisateur() {
        return nomUtilisateur;
    }

    /**
     * Renvoie l'IP du serveur
     * @return l'IP du serveur
     */
    public static String getIpServeur() {
        return ipServeur;
    }

    /**
     * Renvoie le port utilisé
     * @return le port utilisé
     */
    public static int getPortServeur() {
        return PORT_SERVEUR;
    }
    
    /**
     * Setteur du nom de l'utilisateur
     * @param value le nouveau nom de l'utilisateur
     */
    public static void setNomUtilisateur(String value) {
        nomUtilisateur = value;
    }
    
    /**
     * Setteur de l'IP du serveur
     * @param value la nouvelle IP
     */
    public static void setIpServeur(String value) {
        ipServeur = value;
    }
    
    
}
