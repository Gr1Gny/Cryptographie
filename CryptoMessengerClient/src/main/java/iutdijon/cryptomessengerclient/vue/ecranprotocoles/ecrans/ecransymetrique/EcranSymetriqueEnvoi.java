package iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.ecransymetrique;

import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.textzones.TextElement;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.textzones.TextMessage;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.labels.LabelElement;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.labels.LabelTitreProtocole;
import iutdijon.cryptomessengerclient.controleur.Controleur;
import iutdijon.cryptomessengerclient.controleur.protocoles.symetrique.ControleurChiffrementSymetrique;
import iutdijon.cryptomessengerclient.controleur.protocoles.symetrique.ControleurEnvoieSymetrique;
import iutdijon.cryptomessengerclient.controleur.protocoles.symetrique.ControleurGenerationCleSymetrique;
import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import iutdijon.cryptomessengerclient.network.Settings;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.boutons.Bouton;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Ecran pour le protocole : Texte Brut
 * @author simonetma
 */
public class EcranSymetriqueEnvoi extends Scene {
    
    private NomProtocole protocole;          //Protocole utilisé
    private VBox root;                       //Panel de fond
    private TextField textDestinataire;      //Zone de texte pour le destinataire
    private TextField textCle;               //Zone de texte pour la clé
    private TextMessage texteMessageClair;   //zone de texte pour le message clair
    private TextMessage texteMessageChiffre; //zone de texte pour le message chiffré
    private Controleur controleurEnvoie;     //Controleur pour l'envoie du message
    private Controleur controleurChiffrement;//Controleur pour le chiffrement
    private Controleur controleurGenerationCle;//Contorleur pour la génération de la clé
    
    /**
     * Constructeur
     */
    public EcranSymetriqueEnvoi(NomProtocole protocole) {
        this(new VBox(10),protocole);
    }
    
    private EcranSymetriqueEnvoi(VBox root,NomProtocole protocole) {
        super(root,600,800);
        this.protocole = protocole;
        this.root = root;
        this.root.prefWidthProperty().bind(this.widthProperty());
        this.root.setFillWidth(false);
        this.root.setPadding(new Insets(0,0,10,0));
        
        //Mise en place du controleur
        this.controleurEnvoie = new ControleurEnvoieSymetrique(this,protocole);
        this.controleurChiffrement = new ControleurChiffrementSymetrique(this,protocole);
        this.controleurGenerationCle = new ControleurGenerationCleSymetrique(this,protocole);
        
        //Nom du protocole
        Label labelTitreProtocole = new LabelTitreProtocole(protocole);
        labelTitreProtocole.prefWidthProperty().bind(root.widthProperty());
        labelTitreProtocole.setStyle("-fx-background-color:lightblue");
        root.getChildren().add(labelTitreProtocole);
        
        //Label de l'expéditeur
        Label labelExpediteur = new LabelElement("DE :    "+Settings.getNomUtilisateur());
        labelExpediteur.prefWidthProperty().bind(root.widthProperty());
        labelExpediteur.setPadding(new Insets(0,0,0,10));
        root.getChildren().add(labelExpediteur);
                
        //HBox pour le destinataire
        HBox hboxDestinataire = new HBox();
        hboxDestinataire.prefWidthProperty().bind(root.widthProperty());
        root.getChildren().add(hboxDestinataire);
            //Zone de texte
            this.textDestinataire = new TextElement("Entrez le nom du destinataire");
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
        
        //HBox pour la clé
        HBox hboxCle = new HBox(5);
        hboxCle.prefWidthProperty().bind(root.widthProperty());
        root.getChildren().add(hboxCle);
            //Zone de texte
            this.textCle = new TextElement("Entrez la clé de chiffrement");
            //Label du destinataire
            Label labelCle = new LabelElement("CLE :  ");
            labelCle.setPadding(new Insets(0,0,0,10));
            labelCle.prefHeightProperty().bind(this.textCle.heightProperty());
            this.textCle.prefWidthProperty().bind(hboxCle.widthProperty().divide(2).subtract(labelCle.widthProperty()));
            //Bouton de génération
            Bouton boutonGenerer = new Bouton("Générer",this.controleurGenerationCle);
            boutonGenerer.setFont(new Font(boutonGenerer.getFont().getName(),15));
            boutonGenerer.prefHeightProperty().bind(labelCle.heightProperty());
            //Ajout à la hbox
            hboxCle.getChildren().add(labelCle);
            hboxCle.getChildren().add(this.textCle);
            hboxCle.getChildren().add(boutonGenerer);
        
        //Label message clair
        LabelElement labelMC = new LabelElement("Message clair :");
        root.getChildren().add(labelMC);
        labelMC.setPadding(new Insets(0,0,0,10));
        
        //Zone de saise de texte clair
        this.texteMessageClair = new TextMessage("Entrez le message à envoyer");
        this.texteMessageClair.prefWidthProperty().bind(root.widthProperty().subtract(20));
        this.texteMessageClair.setTranslateX(10);
        root.getChildren().add(this.texteMessageClair);
       
        //Bouton d'envoie du message
        Bouton boutonChiffrer = new Bouton("Chiffrer",this.controleurChiffrement);
        boutonChiffrer.setPadding(new Insets(5));
        boutonChiffrer.translateXProperty().bind(root.widthProperty().subtract(boutonChiffrer.widthProperty()).subtract(10));
        root.getChildren().add(boutonChiffrer);
        
        //Séparateur
        Separator separator2 = new Separator();
        separator2.setPadding(new Insets(10));
        separator2.prefWidthProperty().bind(root.widthProperty());
        root.getChildren().add(separator2);
        
        
        //Label message clair
        LabelElement labelMCh = new LabelElement("Message chiffré :");
        root.getChildren().add(labelMCh);
        labelMCh.setPadding(new Insets(0,0,0,10));
        
        //Zone deu texte chiffré
        this.texteMessageChiffre = new TextMessage("");
        this.texteMessageChiffre.setEditable(false);
        this.texteMessageChiffre.prefWidthProperty().bind(root.widthProperty().subtract(20));
        this.texteMessageChiffre.setTranslateX(10);
        root.getChildren().add(this.texteMessageChiffre);
       
        //Bouton d'envoie du message
        Bouton boutonEnvoyer = new Bouton("Envoyer",this.controleurEnvoie);
        boutonEnvoyer.setPadding(new Insets(5));
        boutonEnvoyer.translateXProperty().bind(root.widthProperty().subtract(boutonEnvoyer.widthProperty()).subtract(10));
        root.getChildren().add(boutonEnvoyer);
        
        root.requestFocus();
        
    }
    
    /**
     * Renvoie le message clair
     * @return le message clair
     */
    public Message getMessageClair() {
        Message message = new Message();
        message.setExpediteur(Settings.getNomUtilisateur());
        message.setDestinataire(this.textDestinataire.getText());
        message.setProtocoleUtilise(NomProtocole.BRUT.toString());
        message.setCorpsMessage(this.texteMessageClair.getText());
        return message;
    }
    
    /**
     * Renvoie la clé au format String
     * @return la clé
     */
    public String getCle() {
        return this.textCle.getText();
    }
    
    /**
     * Setter de la clé
     * @param cle la nouvelle clé
     */
    public void setCle(String cle) {
        this.textCle.setText(cle);
    }
    
    /**
     * Renvoie le message chiffré
     * @return le message chiffré
     */
    public Message getMessageChiffre() {
        Message message = new Message();
        message.setExpediteur(Settings.getNomUtilisateur());
        message.setDestinataire(this.textDestinataire.getText());
        message.setProtocoleUtilise(this.protocole.toString());
        message.setCorpsMessage(this.texteMessageChiffre.getText());
        return message;
    }
    
    /**
     * Change le message chiffré
     * @param messageChiffre le nouveau message chiffré
     */
    public void setMessageChiffre(Message messageChiffre) {
        this.texteMessageChiffre.setText(messageChiffre.getCorpsMessage());
    }
}
