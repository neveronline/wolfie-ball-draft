/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static wdk.gui.wolfie_GUI.CLASS_HEADING_LABEL;
import static wdk.gui.wolfie_GUI.PRIMARY_STYLE_SHEET;
import wdk.data.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import wdk.data.ProTeam;
/**
 *
 * @author Leon
 */
public class AddPlayerDialog extends Stage
{
    Player player;
    GridPane gridPane;
    Scene dialogScene;
    Label headingLabel;
    Label firstNameLabel;
    Label lastNameLabel;
    Label proTeamLabel;
    
    TextField firstNameTextField;
    TextField lastNameTextField;
    ComboBox proTeamComboBox;
    
    CheckBox C;
    CheckBox B1;
    CheckBox B2;
    CheckBox B3;
    CheckBox SS;
    CheckBox OF;
    CheckBox P;
    
    Button completeButton;
    Button cancelButton;
    String selection;
    
    public static final String COMPLETE = "Complete";
    public static final String CANCEL = "Cancel";
    public static final String PLAYER_HEADING = "Player Details";
    public static final String ADD_PLAYER_TITLE = "Add New Player ";
    public static final String FIRSTNAME_LABEL = "First Name :";
    public static final String LASTNAME_LABEL ="Last Name: ";
    public static final String PROTEAM_LABEL = "Pro Team: ";
    public static final String EMPTY_TEXT = "";
    public AddPlayerDialog(Stage primaryStage,  MessageDialog messageDialog) throws IOException 
    {
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(15, 20, 20, 20));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        headingLabel = new Label(PLAYER_HEADING);
        headingLabel.getStyleClass().add(CLASS_HEADING_LABEL);
        firstNameLabel = new Label(FIRSTNAME_LABEL); 
        lastNameLabel = new Label(LASTNAME_LABEL);
        proTeamLabel = new Label(PROTEAM_LABEL);
        
        firstNameTextField = initGridTextField(gridPane,10,EMPTY_TEXT,true,2,1,2,1);
        firstNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            player.setFirstName(newValue);
        });
        
        lastNameTextField = initGridTextField(gridPane,10,EMPTY_TEXT,true,2,2,2,1);
        lastNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            player.setLastName(newValue);
        });
        
        proTeamComboBox = new ComboBox();
        ObservableList<String> proTeamChoices = FXCollections.observableArrayList();
        for (ProTeam s : ProTeam.values()) 
        {
            proTeamChoices.add(s.toString());
        }
        proTeamComboBox.setItems(proTeamChoices);
        proTeamComboBox.getSelectionModel().selectFirst();
        
        proTeamComboBox.setOnAction(e->{
            player.setTeam(proTeamComboBox.getSelectionModel().getSelectedItem().toString());
        });
        
        C = new CheckBox("C");
        B1 =new CheckBox("1B");
        B3 =new CheckBox("3B");
        B2=new CheckBox("2B");
        SS=new CheckBox("SS");
        OF=new CheckBox("OF");
        P=new CheckBox("P");
        
        completeButton = new Button(COMPLETE);
        cancelButton = new Button(CANCEL);
        
        // REGISTER EVENT HANDLERS FOR OUR BUTTONS
        EventHandler completeCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button)ae.getSource();
            AddPlayerDialog.this.selection = sourceButton.getText();
            AddPlayerDialog.this.hide();
            
            String pos = "";
            if(C.isSelected())
                pos+="_C";
            if(B1.isSelected())
                pos+="_1B";
            if(B3.isSelected())
                pos+="_3B";
            if(B2.isSelected())
                pos+="_2B";
            if(SS.isSelected())
                pos+="_SS";
            if(OF.isSelected())
                pos+="_OF";
            if(P.isSelected())
                pos+="_P";
            
            pos =pos.substring(1);
            player.setPosition(pos);
            
            
        };
        completeButton.setOnAction(completeCancelHandler);
        cancelButton.setOnAction(completeCancelHandler);
        
        gridPane.add(headingLabel, 0, 0,5,1);
        gridPane.add(firstNameLabel, 0, 1,2,1);
        gridPane.add(lastNameLabel, 0, 2,2,1);
        gridPane.add(proTeamLabel,0,3,2,1);
        gridPane.add(proTeamComboBox, 2,3, 2,1);
        gridPane.add(C, 0, 4,1,1);
        gridPane.add(B1, 1, 4,1,1);
        gridPane.add(B3, 2, 4,1,1);
        gridPane.add(B2, 3, 4,1,1);
        gridPane.add(SS, 4, 4,1,1);
        gridPane.add(OF, 5, 4,1,1);
        gridPane.add(P, 6, 4,1,1);
        
        gridPane.add(completeButton, 0, 5,2,1);
        gridPane.add(cancelButton, 2, 5,1,1);
        
        dialogScene = new Scene(gridPane);
        dialogScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        this.setScene(dialogScene);
        
     }
    public String getSelection() {
        return selection;
    }
    
    public Player getPlayer() { 
        return player;
    } 
    
    private TextField initGridTextField(GridPane container, int size, String initText, boolean editable, int col, int row, int colSpan, int rowSpan) {
        TextField tf = new TextField();
        tf.setPrefColumnCount(size);
        tf.setText(initText);
        tf.setEditable(editable);
        container.add(tf, col, row, colSpan, rowSpan);
        return tf;
    }
    
     public boolean wasCompleteSelected() {
        return selection.equals(COMPLETE);
    }
         
     public Player showAddPlayerDialog() 
     {
        // SET THE DIALOG TITLE
        setTitle(ADD_PLAYER_TITLE);
        
        player = new Player();
        firstNameTextField.setText(player.getFirstName());
        lastNameTextField.setText(player.getLastName());
        proTeamComboBox.setValue(player.getTeam());
        
        this.showAndWait();
        
        return player;
        
     }
     
     
}
