package iutdijon.cryptomessengerclient.vue;

import java.util.HashMap;
import javafx.scene.image.Image;

/**
 * Manager des images
 * @author simonetma
 */
public class ImagesManager {
    
    private static ImagesManager instance;  //Instance du singleton
    public HashMap<String,Image> images;
    
    /**
     * Constructeur privé, initialise les images
     */
    private ImagesManager() {
        this.images = new HashMap<>();
        this.ajouterImage("Asym", "/images/asymetric.png");
        this.ajouterImage("Sym", "/images/symetric.png");
        this.ajouterImage("Brut", "/images/brut.png");
        this.ajouterImage("Icon", "/icones/user.png");
        this.ajouterImage("Comp","/images/compression.png");
    }
    
    private void ajouterImage(String nom, String path) {
        Image image = new Image(ImagesManager.class.getResourceAsStream(path));
        this.images.put(nom,image);
    }
    
    /**
     * Getter de l'instance
     * @return l'instance du singleton
     */
    private static ImagesManager get() {
        if(instance == null) instance = new ImagesManager();
        return instance;
    }
    
    /**
     * Renvoie l'image demandée
     * @param nom ID de l'image
     * @return l'image
     */
    public static Image getImage(String nom) {
        return get().images.get(nom);
    }
}
