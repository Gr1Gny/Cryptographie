package iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.ecransymetrique;

import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.textzones.TextElement;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.textzones.TextMessage;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.labels.LabelElement;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.labels.LabelTitreProtocole;
import iutdijon.cryptomessengerclient.controleur.Controleur;
import iutdijon.cryptomessengerclient.controleur.protocoles.symetrique.ControleurDechiffrementSymetrique;
import iutdijon.cryptomessengerclient.controleur.protocoles.symetrique.ControleurReceptionSymetrique;
import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import iutdijon.cryptomessengerclient.network.Settings;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.boutons.Bouton;
import iutdijon.cryptomessengerclient.controleur.observation.Observateur;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Ecran pour le protocole : Texte Brut
 * @author simonetma
 */
public class EcranSymetriqueReception extends Scene implements Observateur {
    
    private NomProtocole protocole;          //Protocole utilisé
    private VBox root;                       //Panel de fond
    private Label textDestinataire;          //Zone de texte pour le destinataire
    private Label textExpediteur;            //Zone de texte pour le destinataire
    private TextField textCle;               //Zone de texte pour la clé
    private TextMessage texteMessageChiffre;   //zone de texte pour le message clair
    private TextMessage texteMessageDechiffre; //zone de texte pour le message chiffré
    private Controleur controleurReception;     //Controleur pour l'envoie du message
    private Controleur controleurDechiffrement;//Controleur pour le chiffrement
    
    private Message messageChiffre;             //Message chiffre observe
    
    /**
     * Constructeur
     */
    public EcranSymetriqueReception(NomProtocole protocole) {
        this(new VBox(10),protocole);
    }
    
    private EcranSymetriqueReception(VBox root,NomProtocole protocole) {
        super(root,600,800);
        this.protocole = protocole;
        this.root = root;
        this.root.prefWidthProperty().bind(this.widthProperty());
        this.root.setFillWidth(false);
        this.root.setPadding(new Insets(0,0,10,0));
        
        //Observation
        this.messageChiffre = new Message();
        this.messageChiffre.inscrire(this);
        
        //Mise en place du controleur
        this.controleurReception = new ControleurReceptionSymetrique(this);
        this.controleurDechiffrement = new ControleurDechiffrementSymetrique(this,protocole);
        
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
        
       
        
        //Label message clair
        LabelElement labelMC = new LabelElement("Message chiffré :");
        root.getChildren().add(labelMC);
        labelMC.setPadding(new Insets(0,0,0,10));
        
        //Zone de saise de texte clair
        this.texteMessageChiffre = new TextMessage("");
        this.texteMessageChiffre.setEditable(false);
        this.texteMessageChiffre.prefWidthProperty().bind(root.widthProperty().subtract(20));
        this.texteMessageChiffre.setTranslateX(10);
        root.getChildren().add(this.texteMessageChiffre);
       
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
            this.textCle = new TextElement("Entrez la clé de chiffrement");
            //Label du destinataire
            Label labelCle = new LabelElement("CLE :    ");
            labelCle.setPadding(new Insets(0,0,0,10));
            labelCle.prefHeightProperty().bind(this.textCle.heightProperty());
            this.textCle.prefWidthProperty().bind(hboxCle.widthProperty().divide(2).subtract(labelCle.widthProperty()));
            //Ajout à la hbox
            hboxCle.getChildren().add(labelCle);
            hboxCle.getChildren().add(this.textCle);
            
        //Label message déchiffré
        LabelElement labelMdC = new LabelElement("Message déchiffré :");
        root.getChildren().add(labelMdC);
        labelMdC.setPadding(new Insets(0,0,0,10));
        
        //Zone deu texte déchiffré
        this.texteMessageDechiffre = new TextMessage("");
        this.texteMessageDechiffre.setEditable(false);
        this.texteMessageDechiffre.prefWidthProperty().bind(root.widthProperty().subtract(20));
        this.texteMessageDechiffre.setTranslateX(10);
        root.getChildren().add(this.texteMessageDechiffre);
        
        //Bouton de déchiffrement du message
        Bouton boutonDechiffrer = new Bouton("Déchiffrer",this.controleurDechiffrement);
        boutonDechiffrer.setPadding(new Insets(5));
        boutonDechiffrer.translateXProperty().bind(root.widthProperty().subtract(boutonDechiffrer.widthProperty()).subtract(10));
        root.getChildren().add(boutonDechiffrer);
               
        root.requestFocus();
        
    }
    
    /**
     * Renvoie le message chiffré
     * @return le message chiffré
     */
    public Message getMessageChiffre() {
        return this.messageChiffre;
    }
    
    /**
     * Renvoie la clé au format String
     * @return la clé
     */
    public String getCle() {
        return this.textCle.getText();
    }
    
    /**
     * Renvoie le message déchiffré
     * @return le message déchiffré
     */
    public Message getMessageDechiffre() {
        Message message = new Message();
        message.setExpediteur(Settings.getNomUtilisateur());
        message.setDestinataire(this.textDestinataire.getText());
        message.setProtocoleUtilise(this.protocole.toString());
        message.setCorpsMessage(this.texteMessageDechiffre.getText());
        return message;
    }
    
    /**
     * Change le message dechiffré
     * @param messageDechiffre le nouveau message déchiffré
     */
    public void setMessageDechiffre(Message messageDechiffre) {
        this.texteMessageDechiffre.setText(messageDechiffre.getCorpsMessage());
    }

    @Override
    public void avertir(String info) {
        switch(info) {
            case "DESTINATAIRE" : this.textDestinataire.setText(this.messageChiffre.getDestinataire()); break;
            case "EXPEDITEUR" : this.textExpediteur.setText(this.messageChiffre.getExpediteur()); break;
            case "CORPS" : this.texteMessageChiffre.setText(this.messageChiffre.getCorpsMessage()); break;
        }
    }
    
}
