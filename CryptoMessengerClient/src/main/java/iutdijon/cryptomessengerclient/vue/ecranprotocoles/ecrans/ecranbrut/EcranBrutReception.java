package iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.ecranbrut;

import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.textzones.TextMessage;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.labels.LabelElement;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.labels.LabelTitreProtocole;
import iutdijon.cryptomessengerclient.controleur.Controleur;
import iutdijon.cryptomessengerclient.controleur.protocoles.brut.ControleurReceptionBrut;
import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.boutons.Bouton;
import iutdijon.cryptomessengerclient.controleur.observation.Observateur;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Ecran pour le protocole : Texte Brut (Reception)
 * @author simonetma
 */
public class EcranBrutReception extends Scene implements Observateur {
    
    private VBox root;                      //Panel de fond
    private Label labelExpediteur;          //Zone pour l'expéditeur
    private Label labelDestinataire;        //Zone pour le destinataire
    private TextMessage texteMessage;       //zone de texte pour le message
    private Controleur controleurReception; //Controleur pour la reception du message
    
    private Message message;                //Message observé
    /**
     * Constructeur
     */
    public EcranBrutReception() {
        this(new VBox(10));
    }
    
    private EcranBrutReception(VBox root) {
        super(root,600,400);
        this.root = root;
        this.root.prefWidthProperty().bind(this.widthProperty());
        this.root.setFillWidth(false);
        this.root.setPadding(new Insets(0,0,10,0));
        
        //Observation
        this.message = new Message();
        this.message.inscrire(this);
        
        //Mise en place du controleur
        this.controleurReception = new ControleurReceptionBrut(this);
        
        
        //Nom du protocole
        Label labelTitreProtocole = new LabelTitreProtocole(NomProtocole.BRUT);
        labelTitreProtocole.prefWidthProperty().bind(root.widthProperty());
        labelTitreProtocole.setStyle("-fx-background-color:lightblue");
        root.getChildren().add(labelTitreProtocole);
        
        //Label de l'expéditeur
        this.labelExpediteur = new LabelElement("DE :    ");
        labelExpediteur.prefWidthProperty().bind(root.widthProperty());
        labelExpediteur.setPadding(new Insets(0,0,0,10));
        root.getChildren().add(labelExpediteur);
        
        //HBox pour le destinataire
        this.labelDestinataire = new LabelElement("A   :    ");
        this.labelDestinataire.prefWidthProperty().bind(root.widthProperty());
        this.labelDestinataire.setPadding(new Insets(0,0,0,10));
        root.getChildren().add(this.labelDestinataire);
                
        //Zone de saise de texte
        this.texteMessage = new TextMessage("");
        this.texteMessage.setDisable(true);
        this.texteMessage.prefWidthProperty().bind(root.widthProperty().subtract(20));
        this.texteMessage.setTranslateX(10);
        root.getChildren().add(this.texteMessage);
       
        //Bouton d'envoie du message
        Bouton bouton = new Bouton("Recevoir",this.controleurReception);
        bouton.setPadding(new Insets(5));
        bouton.translateXProperty().bind(root.widthProperty().subtract(bouton.widthProperty()).subtract(10));
        root.getChildren().add(bouton);
        
        root.requestFocus();
        
    }

    @Override
    public void avertir(String info) {
        switch(info) {
            case "DESTINATAIRE" : this.labelDestinataire.setText("A   :    "+this.message.getDestinataire()); break;
            case "EXPEDITEUR" : this.labelExpediteur.setText("DE :    "+this.message.getExpediteur()); break;
            case "CORPS" : this.texteMessage.setText(this.message.getCorpsMessage()); break;
        }
    }
    
    /**
     * Message reçu
     * @return message observé
     */
    public Message getMessage() {
        return this.message;
    }
    
    
    
}
