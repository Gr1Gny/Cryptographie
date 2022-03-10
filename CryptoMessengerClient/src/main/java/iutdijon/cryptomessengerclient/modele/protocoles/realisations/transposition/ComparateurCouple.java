package iutdijon.cryptomessengerclient.modele.protocoles.realisations.transposition;

import java.util.Comparator;

/**
 *
 * @author nm344384
 */
public class ComparateurCouple implements Comparator<Couple>{

    @Override
    public int compare(Couple o1, Couple o2) {
        int ret = 1;
        if(o1.getCaractere() < o2.getCaractere() || ((o1.getCaractere() == o2.getCaractere()) && o1.getPosition() < o2.getPosition())){
            ret = -1;
        }
        return ret;
    }
    
}
