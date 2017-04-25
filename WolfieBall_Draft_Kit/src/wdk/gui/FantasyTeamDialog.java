/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wdk.WDK_PropertyType;
import static wdk.gui.wolfie_GUI.CLASS_HEADING_LABEL;
import static wdk.gui.wolfie_GUI.CLASS_PROMPT_LABEL;
import static wdk.gui.wolfie_GUI.PRIMARY_STYLE_SHEET;
import wdk.data.Player;
import wdk.data.Draft;
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
import properties_manager.PropertiesManager;
import wdk.data.Contract;
import wdk.data.FantasyTeam;
/**
 *
 * @author Leon
 */
public class FantasyTeamDialog extends Stage
{
    FantasyTeam fantasyTeam;
    GridPane gridPane;
    Scene dialogScene;
    Label headingLabel;
    
    Label name;
    Label owner;
    
    TextField nameTextField;
    TextField ownerTextField;
    
    Button completeButton;
    Button cancelButton;
    String selection;
    
    public static final String COMPLETE = "Complete";
    public static final String CANCEL = "Cancel";
    public static final String FANTASY_TEAM_HEADING = "Fantasy Team Details";
    public static final String NAME_LABEL = "Name :";
    public static final String OWNERNAME_LABEL ="Owner: ";
    public static final String EMPTY_TEXT = "";
    public static final String ADD_TEAM_TITLE = "Add New Team ";
    public static final String EDIT_TEAM_TITLE = "Edit Team";
    
     public FantasyTeamDialog(Stage primaryStage,  MessageDialog messageDialog) throws IOException 
    {
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        
         gridPane = new GridPane();
        gridPane.setPadding(new Insets(15, 20, 20, 20));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        headingLabel = new Label(FANTASY_TEAM_HEADING);
        headingLabel.getStyleClass().add(CLASS_HEADING_LABEL);
        name = new Label(NAME_LABEL);
        owner = new Label(OWNERNAME_LABEL);
        
        nameTextField = initGridTextField(gridPane,10,EMPTY_TEXT,true,1,1,2,1);
        nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            fantasyTeam.setTeamName(newValue);
        });
        
        ownerTextField = initGridTextField(gridPane,10,EMPTY_TEXT,true,1,2,2,1);
        ownerTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            fantasyTeam.setOwner(newValue);
        });
        completeButton = new Button(COMPLETE);
        cancelButton = new Button(CANCEL);
        
        EventHandler completeCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button)ae.getSource();
            FantasyTeamDialog.this.selection = sourceButton.getText();
            FantasyTeamDialog.this.hide();
            
            
        };
        completeButton.setOnAction(completeCancelHandler);
        cancelButton.setOnAction(completeCancelHandler);
        
        gridPane.add(headingLabel, 0, 0,5,1);
        gridPane.add(name, 0, 1,1,1);
        gridPane.add(owner, 0, 2,1,1);
        gridPane.add(completeButton, 0, 3,1,1);
        gridPane.add(cancelButton, 1, 3,1,1);
        
        dialogScene = new Scene(gridPane);
        dialogScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        this.setScene(dialogScene);
    }
     
     private TextField initGridTextField(GridPane container, int size, String initText, boolean editable, int col, int row, int colSpan, int rowSpan) {
        TextField tf = new TextField();
        tf.setPrefColumnCount(size);
        tf.setText(initText);
        tf.setEditable(editable);
        container.add(tf, col, row, colSpan, rowSpan);
        return tf;
    }
     
     public String getSelection() {
        return selection;
    }
    
    public FantasyTeam getFantasyTeam() { 
        return fantasyTeam;
    } 
    public void loadGUIData() 
    {
        // LOAD THE UI STUFF
        nameTextField.setText(fantasyTeam.getTeamName());
        ownerTextField.setText(fantasyTeam.getOwner());      
    }
    public boolean wasCompleteSelected() {
        return selection.equals(COMPLETE);
    }
    
    public FantasyTeam showAddFantasyTeamDialog() 
     {
        setTitle(ADD_TEAM_TITLE);
        fantasyTeam = new FantasyTeam();
        nameTextField.setText(fantasyTeam.getTeamName());
        ownerTextField.setText(fantasyTeam.getOwner());
        this.showAndWait();
        
        return fantasyTeam;
     }
    
    public void showEditFantasyTeamDialog(FantasyTeam itemToEdit) 
    {
        setTitle(EDIT_TEAM_TITLE);
        fantasyTeam = new FantasyTeam();
        fantasyTeam.setTeamName(itemToEdit.getTeamName());
        fantasyTeam.setOwner(itemToEdit.getOwner());
        
         // AND THEN INTO OUR GUI
        loadGUIData();
               
        // AND OPEN IT UP
        this.showAndWait();
    }
    
    
}
