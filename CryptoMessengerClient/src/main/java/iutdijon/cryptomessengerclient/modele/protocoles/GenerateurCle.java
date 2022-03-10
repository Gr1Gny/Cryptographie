package iutdijon.cryptomessengerclient.modele.protocoles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author nm344384
 */
public class GenerateurCle {
    
    public static String genererCleCesar(){
        Random r = new Random();
        return String.valueOf(r.nextInt(26));
    }
    
    public static String genererCleSubstitution(){
        ArrayList<Character> chars = new ArrayList<>(Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'));
        Collections.shuffle(chars);
        String Key = "";
        for (Character c : chars){
            Key += c;
        }
        return Key;
    }
    
    public static String genererCleTransposition(){
        String Key = "";
        Random r = new Random();
        int nb =  5 + r.nextInt(16);
        for(int i = 0; i < nb; i++){
            char c = (char)(r.nextInt(26) + 'a');
            if(r.nextInt(2) == 0){
                c = Character.toUpperCase(c);
            }
            Key += c;   
        }
        return Key;
    }
    
    public static String genererCleVigenere(){
        String Key = "";
        Random r = new Random();
        int nb =  5 + r.nextInt(16);
        for(int i = 0; i < nb; i++){
            char c = (char)(r.nextInt(26) + 'A');
            Key += c;   
        }
        return Key;
    }
    
    public static String genererCleRLE(){
        Random r = new Random();
        int nb =  2 + r.nextInt(8);
        return String.valueOf(nb);
    }

}
