package iutdijon.cryptomessengerclient.vue.ecranconnexion;

import iutdijon.cryptomessengerclient.controleur.ControleurConnexion;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.textzones.TextElement;
import iutdijon.cryptomessengerclient.network.Settings;
import iutdijon.cryptomessengerclient.vue.ecranconnexion.composants.labels.LabelElementConnexion;
import iutdijon.cryptomessengerclient.vue.ecranconnexion.composants.labels.LabelTitre;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.boutons.Bouton;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Ecran pour le protocole : Texte Brut
 * @author simonetma
 */
public class EcranConnexion extends Scene {
    
    private VBox root;                       //Panel de fond
    private TextField textUtilisateur;       //Zone de texte pour le nom de l'utilisateur
    private TextField textIP;                //Zone de texte pour l'IP
    
    /**
     * Constructeur
     */
    public EcranConnexion() {
        this(new VBox(20));
    }
    
    private EcranConnexion(VBox root) {
        super(root,600,250);
        this.root = root;
        this.root.prefWidthProperty().bind(this.widthProperty());
        this.root.setFillWidth(false);
        this.root.setPadding(new Insets(0,0,10,0));
        
        
        
        //Nom du protocole
        Label labelTitre = new LabelTitre("Connexion");
        labelTitre.prefWidthProperty().bind(root.widthProperty());
        labelTitre.setStyle("-fx-background-color:lightblue");
        root.getChildren().add(labelTitre);
        
        //HBox pour lle nom de l'utilisateur
        HBox hboxUtilisateur = new HBox();
        hboxUtilisateur.prefWidthProperty().bind(root.widthProperty());
        root.getChildren().add(hboxUtilisateur);
            //Zone de texte
            this.textUtilisateur = new TextElement(Settings.getNomUtilisateur());
            this.textUtilisateur.setText(Settings.getNomUtilisateur());
            //Label
            Label labelUtilisateur = new LabelElementConnexion("Utilisateur : ");
            labelUtilisateur.setPadding(new Insets(0,0,0,10));
            labelUtilisateur.prefHeightProperty().bind(this.textUtilisateur.heightProperty());
            this.textUtilisateur.setPrefWidth(300);
            //Ajout à la hbox
            hboxUtilisateur.getChildren().add(labelUtilisateur);
            hboxUtilisateur.getChildren().add(this.textUtilisateur);
       
        //HBox pour l'IP
        HBox hboxIP = new HBox();
        hboxIP.prefWidthProperty().bind(root.widthProperty());
        root.getChildren().add(hboxIP);
            //Zone de texte
            this.textIP = new TextElement(Settings.getIpServeur());
            this.textIP.setText(Settings.getIpServeur());
            //Label
            Label labelIP = new LabelElementConnexion("IP du serveur :    ");
            labelIP.setPadding(new Insets(0,0,0,10));
            labelIP.prefHeightProperty().bind(this.textIP.heightProperty());
            this.textIP.setPrefWidth(300);
            //Ajout à la hbox
            hboxIP.getChildren().add(labelIP);
            hboxIP.getChildren().add(this.textIP);
        
        labelUtilisateur.prefWidthProperty().bind(labelIP.widthProperty());
        textIP.prefWidthProperty().bind(textUtilisateur.widthProperty());
            
        Bouton bouton = new Bouton("Se connecter",new ControleurConnexion(this));
        bouton.translateXProperty().bind(root.widthProperty().subtract(bouton.widthProperty()).divide(2));
        root.getChildren().add(bouton);
    }

    /**
     * Renvoie le nom de l'utilisateur
     * @return le nom de l'utilisateur saisi par l'utilisateur
     */
    public String getNomUtilisateur() {
        return this.textUtilisateur.getText();
    }

    /**
     * Renvoie l'IP du serveur 
     * @return l'IP du serveur saisie par l'utilisateur
     */
    public String getIpServeur() {
       return this.textIP.getText();
    }
   
    
}
