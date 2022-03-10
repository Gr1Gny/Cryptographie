package iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.ecrancompression;

import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.textzones.TextElement;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.textzones.TextMessage;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.labels.LabelElement;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.labels.LabelTitreProtocole;
import iutdijon.cryptomessengerclient.controleur.Controleur;
import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import iutdijon.cryptomessengerclient.network.Settings;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.boutons.Bouton;
import iutdijon.cryptomessengerclient.controleur.observation.Observateur;
import iutdijon.cryptomessengerclient.controleur.protocoles.compression.ControleurDecompression;
import iutdijon.cryptomessengerclient.controleur.protocoles.compression.ControleurReceptionCompression;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Ecran pour le protocole : Compression
 * @author simonetma
 */
public class EcranCompressionReception extends Scene implements Observateur {
    
    private NomProtocole protocole;          //Protocole utilisé
    private VBox root;                       //Panel de fond
    private Label textDestinataire;          //Zone de texte pour le destinataire
    private Label textExpediteur;            //Zone de texte pour le destinataire
    private TextField textCle;               //Zone de texte pour la clé
    private TextMessage texteMessageCompresse;   //zone de texte pour le message clair
    private TextMessage texteMessageDecompesse; //zone de texte pour le message chiffré
    private Controleur controleurReception;     //Controleur pour l'envoie du message
    private Controleur controleurDecompression;//Controleur pour la compression
    
    private Message messageCompression;             //Message compressé observé
    
    /**
     * Constructeur
     */
    public EcranCompressionReception(NomProtocole protocole) {
        this(new VBox(10),protocole);
    }
    
    private EcranCompressionReception(VBox root,NomProtocole protocole) {
        super(root,600,800);
        this.protocole = protocole;
        this.root = root;
        this.root.prefWidthProperty().bind(this.widthProperty());
        this.root.setFillWidth(false);
        this.root.setPadding(new Insets(0,0,10,0));
        
        //Observation
        this.messageCompression = new Message();
        this.messageCompression.inscrire(this);
        
        //Mise en place du controleur
        this.controleurReception = new ControleurReceptionCompression(this,protocole);
        this.controleurDecompression = new ControleurDecompression(this,protocole);
        
        //Nom du protocole
        Label labelTitreProtocole = new LabelTitreProtocole(protocole);
        labelTitreProtocole.prefWidthProperty().bind(root.widthProperty());
        labelTitreProtocole.setStyle("-fx-background-color:lightblue");
        root.getChildren().add(labelTitreProtocole);
        
        
        //HBox pour l'expediteur
        HBox hboxExpediteur = new HBox();
        hboxExpediteur.prefWidthProperty().bind(root.widthProperty());
        root.getChildren().add(hboxExpediteur);
            //Zone de texte
            this.textExpediteur = new LabelElement("");
            //Label du destinataire
            Label labelExpediteur = new LabelElement("DE :    ");
            labelExpediteur.setPadding(new Insets(0,0,0,10));
            labelExpediteur.prefHeightProperty().bind(this.textExpediteur.heightProperty());
            this.textExpediteur.prefWidthProperty().bind(hboxExpediteur.widthProperty().divide(2).subtract(labelExpediteur.widthProperty()));
            //Ajout à la hbox
            hboxExpediteur.getChildren().add(labelExpediteur);
            hboxExpediteur.getChildren().add(this.textExpediteur);
                
        //HBox pour le destinataire
        HBox hboxDestinataire = new HBox();
        hboxDestinataire.prefWidthProperty().bind(root.widthProperty());
        root.getChildren().add(hboxDestinataire);
            //Zone de texte
            this.textDestinataire = new LabelElement("");
            //Label du destinataire
            Label labelDestinataire = new LabelElement("A   :    ");
            labelDestinataire.setPadding(new Insets(0,0,0,10));
            labelDestinataire.prefHeightProperty().bind(this.textDestinataire.heightProperty());
            this.textDestinataire.prefWidthProperty().bind(hboxDestinataire.widthProperty().divide(2).subtract(labelDestinataire.widthProperty()));
            //Ajout à la hbox
            hboxDestinataire.getChildren().add(labelDestinataire);
            hboxDestinataire.getChildren().add(this.textDestinataire);
        
        //Séparateur
        Separator separator = new Separator();
        separator.setPadding(new Insets(10));
        separator.prefWidthProperty().bind(root.widthProperty());
        root.getChildren().add(separator);
        
       
        
        //Label message compressé
        LabelElement labelMC = new LabelElement("Message compressé :");
        root.getChildren().add(labelMC);
        labelMC.setPadding(new Insets(0,0,0,10));
        
        //Zone de saise de texte compressé
        this.texteMessageCompresse = new TextMessage("");
        this.texteMessageCompresse.setEditable(false);
        this.texteMessageCompresse.prefWidthProperty().bind(root.widthProperty().subtract(20));
        this.texteMessageCompresse.setTranslateX(10);
        root.getChildren().add(this.texteMessageCompresse);
       
        //Bouton de réception du message
        Bouton boutonRecevoir = new Bouton("Recevoir",this.controleurReception);
        boutonRecevoir.setPadding(new Insets(5));
        boutonRecevoir.translateXProperty().bind(root.widthProperty().subtract(boutonRecevoir.widthProperty()).subtract(10));
        root.getChildren().add(boutonRecevoir);
        
        //Séparateur
        Separator separator2 = new Separator();
        separator2.setPadding(new Insets(10));
        separator2.prefWidthProperty().bind(root.widthProperty());
        root.getChildren().add(separator2);
        
         //HBox pour la clé
        HBox hboxCle = new HBox();
        hboxCle.prefWidthProperty().bind(root.widthProperty());
        root.getChildren().add(hboxCle);
            //Zone de texte
            this.textCle = new TextElement("Entrez la clé de compression");
            //Label du destinataire
            Label labelCle = new LabelElement("CLE :    ");
            labelCle.setPadding(new Insets(0,0,0,10));
            labelCle.prefHeightProperty().bind(this.textCle.heightProperty());
            this.textCle.prefWidthProperty().bind(hboxCle.widthProperty().divide(2).subtract(labelCle.widthProperty()));
            //Ajout à la hbox
            hboxCle.getChildren().add(labelCle);
            hboxCle.getChildren().add(this.textCle);
            
        //Label message décompressé
        LabelElement labelMdC = new LabelElement("Message décompressé :");
        root.getChildren().add(labelMdC);
        labelMdC.setPadding(new Insets(0,0,0,10));
        
        //Zone deu texte déchiffré
        this.texteMessageDecompesse = new TextMessage("");
        this.texteMessageDecompesse.setEditable(false);
        this.texteMessageDecompesse.prefWidthProperty().bind(root.widthProperty().subtract(20));
        this.texteMessageDecompesse.setTranslateX(10);
        root.getChildren().add(this.texteMessageDecompesse);
        
        //Bouton de déchiffrement du message
        Bouton boutonDechiffrer = new Bouton("Décompresser",this.controleurDecompression);
        boutonDechiffrer.setPadding(new Insets(5));
        boutonDechiffrer.translateXProperty().bind(root.widthProperty().subtract(boutonDechiffrer.widthProperty()).subtract(10));
        root.getChildren().add(boutonDechiffrer);
               
        root.requestFocus();
        
    }
    
    /**
     * Renvoie le message compressé
     * @return le message compressé
     */
    public Message getMessageCompresse() {
        return this.messageCompression;
    }
    
    /**
     * Renvoie la clé au format String
     * @return la clé
     */
    public String getCle() {
        return this.textCle.getText();
    }
    
    /**
     * Renvoie le message décompressé
     * @return le message décompressé
     */
    public Message getMessageDecompresse() {
        Message message = new Message();
        message.setExpediteur(Settings.getNomUtilisateur());
        message.setDestinataire(this.textDestinataire.getText());
        message.setProtocoleUtilise(this.protocole.toString());
        message.setCorpsMessage(this.texteMessageDecompesse.getText());
        return message;
    }
    
    /**
     * Change le message décompressé
     * @param messageDecompresse le nouveau message déchiffré
     */
    public void setMessageDecompresse(Message messageDecompresse) {
        this.texteMessageDecompesse.setText(messageDecompresse.getCorpsMessage());
    }

    @Override
    public void avertir(String info) {
        switch(info) {
            case "DESTINATAIRE" : this.textDestinataire.setText(this.messageCompression.getDestinataire()); break;
            case "EXPEDITEUR" : this.textExpediteur.setText(this.messageCompression.getExpediteur()); break;
            case "CORPS" : this.texteMessageCompresse.setText(this.messageCompression.getCorpsMessage()); break;
        }
    }
    
}
