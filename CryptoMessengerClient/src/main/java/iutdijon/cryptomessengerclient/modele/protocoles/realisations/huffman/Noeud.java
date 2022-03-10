package iutdijon.cryptomessengerclient.modele.protocoles.realisations.huffman;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Noeud pour l'arbre d'Huffman : CLASSE A FINIR D'IMPLEMENTER
 * @author simonetma
 */
public class Noeud {

    private String nom;                     //NOM DU NOEUD (caractère(s))                          
    private int valeur;                     //VALEUR (nombre d'occurences)
    private ArrayList<Noeud> listeFils;     //Liste des noeuds fils
    private String code;                    //Code du noeud
    
    /**
     * Constructeur
     * @param nom Nom du noeuds (le(s) caractère(s))
     * @param nombreOccurences nombre d'occurences
     */
    public Noeud(String nom, int nombreOccurences) {
        this.nom = nom;
        this.valeur = nombreOccurences;
        this.listeFils = new ArrayList<>();
        this.code = "";
    }

    /**
     * Getter du nom
     * @return le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter du nombre d'occurences
     * @return le nombre d'occurences
     */
    public int getNombreOccurences() {
        return valeur;
    }
    
    /**
     * Affichage en String
     * @return nom - valeur
     */
    public String toString() {
        return nom+" - "+this.valeur;
    }
    
    /**
     * Ajouter un nouveau noeud fils
     * @param fils le nouveau filx
     */
    public void ajouterFils(Noeud fils) {
        this.listeFils.add(fils);
    }
    
    /**
     * Fonction récursive de calcul du code
     * @param dictionnaire le dictionnaire en cours de construction
     */
    public void calculCode(HashMap<Character,String> dictionnaire) {
        if(this.listeFils.size() == 0){
            dictionnaire.put(this.nom.charAt(0), this.code);
        }
        else{
            this.listeFils.get(0).code = this.code + "0";
            this.listeFils.get(0).calculCode(dictionnaire);
            this.listeFils.get(1).code = this.code + "1";
            this.listeFils.get(1).calculCode(dictionnaire);
        }
    }
}
