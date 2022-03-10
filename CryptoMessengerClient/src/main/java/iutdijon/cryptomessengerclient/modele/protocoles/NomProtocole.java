package iutdijon.cryptomessengerclient.modele.protocoles;

import iutdijon.cryptomessengerclient.vue.ImagesManager;
import javafx.scene.image.Image;

/**
 * Enumération des différents protocoles utilisables
 * @author simonetma
 */
public enum NomProtocole {
    //ENUMERATION DES PROTOCOLES
    
    BRUT("Texte brut","Brut"),
    CESAR("Cryptogramme de César","Sym"),
    SUBSTITUTION("Cryptogramme de Substitution","Sym"),
    TRANSPOSITION("Cryptogramme de Transposition","Sym"),
    VIGENERE("Cryptogramme de Vigenere","Sym"),
    FEISTEL("Cryptogramme de Feistel","Sym"),
    RLE("Cryptogramme RLE","Comp"),
    HUFFMAN("Cryptogramme de Huffman","Comp");
    
    //--------------------------
    
    
    private final String nom;           //Nom du protocole (pour affichage)
    private final String nomImage;      //Nom de l'image à afficher (pour l'ImagesManager)
    
    /**
     * Constructeur
     * @param nom nom du protocole
     * @param nomImage nom de l'image pour l'icon d'affichage
     */
    private NomProtocole(String nom,String nomImage) {
        this.nom = nom;
        this.nomImage = nomImage;
    }
    
    /**
     * Renvoie le nom du protocole
     * @return le nom du protocole
     */
    public String getNom() {
        return this.nom;
    }
    
    /**
     * Renvoie l'image pour l'affichage du protocole
     * @return l'image à afficher
     */
    public Image getImage() {
        return ImagesManager.getImage(nomImage);
    }
    
}
