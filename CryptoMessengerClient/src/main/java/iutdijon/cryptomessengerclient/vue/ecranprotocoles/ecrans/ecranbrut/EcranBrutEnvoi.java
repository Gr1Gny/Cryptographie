package iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.ecranbrut;

import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.textzones.TextElement;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.textzones.TextMessage;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.labels.LabelElement;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.composants.labels.LabelTitreProtocole;
import iutdijon.cryptomessengerclient.controleur.Controleur;
import iutdijon.cryptomessengerclient.controleur.protocoles.brut.ControleurEnvoieBrut;
import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import iutdijon.cryptomessengerclient.network.Settings;
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
public class EcranBrutEnvoi extends Scene {
    
    private VBox root;                      //Panel de fond
    private TextField textDestinataire;     //Zone de texte pour le destinataire
    private TextMessage texteMessage;       //zone de texte pour le message
    private Controleur controleurEnvoie;    //Controleur pour l'envoie du message
    
    /**
     * Constructeur
     */
    public EcranBrutEnvoi() {
        this(new VBox(10));
    }
    
    private EcranBrutEnvoi(VBox root) {
        super(root,600,400);
        this.root = root;
        this.root.prefWidthProperty().bind(this.widthProperty());
        this.root.setFillWidth(false);
        this.root.setPadding(new Insets(0,0,10,0));
        
        //Mise en place du controleur
        this.controleurEnvoie = new ControleurEnvoieBrut(this);
        
        
        //Nom du protocole
        Label labelTitreProtocole = new LabelTitreProtocole(NomProtocole.BRUT);
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
        
        //Zone de saise de texte
        this.texteMessage = new TextMessage("Entrez le message à envoyer");
        this.texteMessage.prefWidthProperty().bind(root.widthProperty().subtract(20));
        this.texteMessage.setTranslateX(10);
        root.getChildren().add(this.texteMessage);
       
        //Bouton d'envoie du message
        Bouton bouton = new Bouton("Envoyer",this.controleurEnvoie);
        bouton.setPadding(new Insets(5));
        bouton.translateXProperty().bind(root.widthProperty().subtract(bouton.widthProperty()).subtract(10));
        root.getChildren().add(bouton);
        
        root.requestFocus();
        
    }
    
    public Message getMessage() {
        Message message = new Message();
        message.setExpediteur(Settings.getNomUtilisateur());
        message.setDestinataire(this.textDestinataire.getText());
        message.setProtocoleUtilise(NomProtocole.BRUT.toString());
        message.setCorpsMessage(this.texteMessage.getText());
        return message;
    }
    
}
