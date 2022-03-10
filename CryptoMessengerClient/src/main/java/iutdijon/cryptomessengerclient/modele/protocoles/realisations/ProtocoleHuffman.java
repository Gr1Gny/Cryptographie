package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;
import iutdijon.cryptomessengerclient.modele.protocoles.realisations.huffman.ComparateurNoeuds;
import iutdijon.cryptomessengerclient.modele.protocoles.realisations.huffman.Noeud;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author simonetma
 */
public class ProtocoleHuffman extends Protocole {

    //ETAPE 1 -- Compter les occurences de chaque caractère
    public HashMap<Character,Integer> compterCaracteres(String corpMessage) {
        HashMap<Character,Integer> map = new HashMap<>();
        for(char c: corpMessage.toCharArray()){
            if(map.get(c) == null){
                map.put(c, 1);
            }
            else{
                map.put(c, map.get(c) + 1);
            }
        }
        return map;
    }
    
    //ETAPE 2 -- Création de l'arbre
    public PriorityQueue<Noeud> creationListeNoeuds(HashMap<Character,Integer> mapComptageCaracteres) {
        ComparateurNoeuds c = new ComparateurNoeuds();
        PriorityQueue<Noeud> p = new PriorityQueue<>(c);
        //pour chauque clé et valeur dans la hashmap
        for(Map.Entry<Character, Integer> entry : mapComptageCaracteres.entrySet()) {
            char key = entry.getKey();
            int value = entry.getValue();
            //on crée un noeud et on l'ajoute 
            Noeud n = new Noeud(String.valueOf(key),value);
            p.add(n);
        }
        return p;
    }
    
    public Noeud creationArbre(HashMap<Character,Integer> mapComptageCaracteres) {
        PriorityQueue<Noeud> p = creationListeNoeuds(mapComptageCaracteres);
        while(p.size() >= 2){
            Noeud N1 = p.poll();
            Noeud N2 = p.poll();
            //on concatène les valeurs des 2 noeuds
            Noeud concat = new Noeud(N1.getNom() + N2.getNom(),N1.getNombreOccurences() + N2.getNombreOccurences());
            concat.ajouterFils(N1);
            concat.ajouterFils(N2);
            p.add(concat);
        }
        return p.peek();
    }
    
    //ETAPE 3 -- Création du dictionnaire
    public HashMap<Character,String> creationDictionnaire(Noeud racine) {
        HashMap<Character,String> map = new HashMap<>();
        racine.calculCode(map);
        return map;
    }
    
    //ETAPE 4 - Chiffrement du message
    private String chiffrerMessage(String message,HashMap<Character,String> dictionnaire) {
        String newMsg = "";
        for(Map.Entry<Character, String> entry : dictionnaire.entrySet()) {
            //on ajoute au début du message le dictionnaire afin de pouvoir déchiffrer
            newMsg += entry.getKey() + entry.getValue();
        }
        newMsg += "/";
        //on ajouter ensuite le code correspondant a chaque char du message
        for(char c: message.toCharArray()){
            newMsg += dictionnaire.get(c);
        }
        return newMsg;
    }
    
    
    @Override
    public Message chiffrer(Message messageClair) {
        //TODO
        //On récupère le corp du message comme une chaine de caractères
        String msg = messageClair.getCorpsMessage();
        //Etape 1 - On compe les caractères
        HashMap<Character,Integer> map = this.compterCaracteres(msg);
        //Etape 2 - On crée l'arbre
        Noeud n = creationArbre(map);
        //Etape 3 - On crée le dictionnaire
        HashMap<Character,String> dico = creationDictionnaire(n);
        //Etape 4 - Encodage avec le dictionnaire
        String code = chiffrerMessage(msg, dico);
        //On renvoit le message compressé
        messageClair.setCorpsMessage(code);
        return messageClair;
    }

    @Override
    public Message dechiffrer(Message messageChiffre) {
        String msg = messageChiffre.getCorpsMessage();
        String[] code = msg.split("/");
        String dico = code[0];
        
        //partie ou on recréé le dico
        String num = "";
        char key = '#';
        HashMap<Character,String> dictionnaire = new HashMap<>();
        for(char c: dico.toCharArray()){
            //si c'est un 0 ou un 1, on stock le code dans une variable
            if(c == '0' || c == '1'){
                num += c;
            }
            //sinon, on ajoute le code dans le dictionnaire et on recommence pour le nouveau char
            else{
                if(Character.isLetter(key)){
                    dictionnaire.put(key, num);
                }
                key = c;
                num = "";
            }
        }
        dictionnaire.put(key, num);
        
        //partie ou on déchiffre grace au dico
        
        String dechiffre = "";
        
        String codeDechiffre = "";
        for(char c: code[1].toCharArray()){
            codeDechiffre += c;
            //on verifie pour chaque valeurs dans le dico si elle correspond au code qu'on stock
            for(Map.Entry<Character, String> entry : dictionnaire.entrySet()) {
                char k = entry.getKey();
                //si ca correspond, on ajoute la cle au message et le code repart de 0
                if(entry.getValue().equals(codeDechiffre)){
                    dechiffre += k;
                    codeDechiffre = "";
                }              
            }
        }      
        Message m = new Message();
        m.setDestinataire(messageChiffre.getDestinataire());
        m.setExpediteur(messageChiffre.getExpediteur());
        m.setCorpsMessage(dechiffre);
        m.setProtocoleUtilise(messageChiffre.getProtocoleUtilise().toString());
        return m;
    }
    
}
