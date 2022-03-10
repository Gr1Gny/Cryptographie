package iutdijon.cryptomessengerclient.modele.protocoles.realisations.transposition;

/**
 *
 * @author nm344384
 */
public class Couple {
    private char caractere;
    private int position;

    public Couple(char caractere, int position) {
        this.caractere = caractere;
        this.position = position;
    }

    public char getCaractere() {
        return caractere;
    }

    public int getPosition() {
        return position;
    }

    public void setCaractere(char caractere) {
        this.caractere = caractere;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    
}
