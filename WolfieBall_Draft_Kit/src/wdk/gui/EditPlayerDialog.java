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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import wdk.data.Contract;
import wdk.data.DraftDataManager;
import wdk.data.FantasyTeam;


/**
 *
 * @author Leon
 */
public class EditPlayerDialog extends Stage
{
    Player player;
    FantasyTeam fantasyTeam;
    
    GridPane gridPane;
    Scene dialogScene;
    Label headingLabel;
    Label nameLabel;
    Label QPLabel;
    Label fantasyTeamLabel;
    Label positionLabel;
    Label contractLabel;
    Label salaryLabel;
    
    ComboBox <FantasyTeam> fantasyTeamComboBox;
    ComboBox positionComboBox;
    ComboBox contractComboBox;
    TextField salaryTextField;
    
    
    Button addToTaxi;
    boolean positionChanged;
    
    Image playerPicture;
    ImageView playerView;
    Image flagPicture;
    ImageView flagView;
    Button completeButton;
    Button cancelButton;
    
   
    String selection;
    String searchName;
    String flagName;
    String []playerQP;
    ObservableList <String> avaliablePosition;
    
    public static final String TAXI = "Add to Team TaxiQuad ";
    public static final String COMPLETE = "Add to Team StartLine ";
    public static final String CANCEL = "Cancel";
    public static final String FANTASYTEAM_LABEL ="Fantasy Team:";
    public static final String POSITION_LABEL ="Position:";
    public static final String CONTRACT_LABEL ="Contract:";
    public static final String SALARY_LABEL ="Salary:";
    public static final String EMPTY_TEXT = "";
    
    public static final String PLAYER_HEADING = "Player Details";
    public static final String ADD_PLAYER_TITLE = "Add New Player ";
    public static final String EDIT_PLAYER_TITLE = "Edit Player ";
    
    public static final String PLAYER_PICTURE_PATH ="file:images/players/";
    public static final String FLAG_PICTURE_PATH ="file:images/flags/";
    public static final String PNG =".png";
    public static final String JPG =".jpg";
    public static final String NO_PICTURE = "AAA_PhotoMissing";
    
    
    public EditPlayerDialog(Stage primaryStage,  MessageDialog messageDialog,wolfie_GUI gui) throws IOException
    {
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        positionChanged = false;
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(15, 20, 20, 20));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        searchName ="";
        headingLabel = new Label(PLAYER_HEADING);
        headingLabel.getStyleClass().add(CLASS_HEADING_LABEL);
        playerPicture = new Image(PLAYER_PICTURE_PATH+searchName+JPG);
        playerView = new ImageView();
        flagPicture = new Image(FLAG_PICTURE_PATH+searchName+PNG);
        flagView = new ImageView();
        
        nameLabel = new Label("name");
        QPLabel = new Label("QP");
        
        fantasyTeamLabel = new Label(FANTASYTEAM_LABEL);
        positionLabel = new Label(POSITION_LABEL);
        contractLabel = new Label(CONTRACT_LABEL);
        salaryLabel = new Label(SALARY_LABEL);
        
        fantasyTeamComboBox = new ComboBox();
        positionComboBox = new ComboBox();
        contractComboBox = new ComboBox();
        fantasyTeamComboBox.getSelectionModel().clearSelection();
        fantasyTeamComboBox.setItems(gui.getDataManager().getDraft().getFantasyTeamList());
        fantasyTeamComboBox.getSelectionModel().selectFirst();
        salaryTextField = initGridTextField(gridPane,5,EMPTY_TEXT,true,1,7,1,1);
        fantasyTeamComboBox.setOnAction(e->{
            fantasyTeam = fantasyTeamComboBox.getSelectionModel().getSelectedItem();
            
            // reset avaliable pos
            
            if(fantasyTeamComboBox.getSelectionModel().getSelectedItem()!= null)
            {
                positionComboBox.setValue(null);
                avaliablePosition.clear();
                for (int i =0; i<playerQP.length;i++)
                {
                    if(fantasyTeam.getHashMap().get(playerQP[i])>0)
                    {
                        avaliablePosition.add(playerQP[i]);
                        
//                if(playerQP[i] == "1B")
//                {
//                    if(avaliablePosition.contains("CI")==false)
//                    {
//                        avaliablePosition.add("CI");
//                    }
//                }
                    }
                }
                
                
                
            }
            else
            {
                positionComboBox.setValue(null);
                avaliablePosition.clear();
                for (int i =0; i<playerQP.length;i++)
                {
                    
                    avaliablePosition.add(playerQP[i]);
                    
                }
            }
            if(fantasyTeamComboBox.getSelectionModel().getSelectedItem()!= null)
            {
                if(avaliablePosition.contains("1B")||avaliablePosition.contains("3B"))
                {
                    if(fantasyTeam.getHashMap().get("CI")>0)
                    {
                        avaliablePosition.add("CI");
                    }
                }
                if(avaliablePosition.contains("2B")||avaliablePosition.contains("SS"))
                {
                    if(fantasyTeam.getHashMap().get("MI")>0)
                    {
                        avaliablePosition.add("MI");
                    }
                }
                if(!avaliablePosition.contains("P"))
                {
                    if(fantasyTeam.getHashMap().get("U")>0)
                    {
                        avaliablePosition.add("U");
                    }
                }
            }
            
        });
        
        salaryTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            player.setSalary(Integer.parseInt(newValue));
        });
        
        avaliablePosition = FXCollections.observableArrayList();
        
        
        positionComboBox.getSelectionModel().clearSelection();
        positionComboBox.setItems(avaliablePosition);
        
        positionComboBox.setOnAction(e->{
            player.setActualPosition((String)positionComboBox.getSelectionModel().getSelectedItem());
            positionChanged = true;
        });
        ObservableList<String> contractChoices = FXCollections.observableArrayList();
        for (Contract s : Contract.values()) {
            contractChoices.add(s.toString());
        }
        
        contractComboBox.getSelectionModel().clearSelection();
        contractComboBox.setItems(contractChoices);
        contractComboBox.getSelectionModel().selectFirst();
        
        contractComboBox.setOnAction(e->{
            player.setContract(contractComboBox.getSelectionModel().getSelectedItem().toString());
        });
        
        completeButton = new Button(COMPLETE);
        cancelButton = new Button(CANCEL);
        addToTaxi = new Button(TAXI);
        
        // REGISTER EVENT HANDLERS FOR OUR BUTTONS
        EventHandler completeCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button)ae.getSource();
            EditPlayerDialog.this.selection = sourceButton.getText();
            EditPlayerDialog.this.hide();
            
            fantasyTeam = fantasyTeamComboBox.getSelectionModel().getSelectedItem();
        };
        completeButton.setOnAction(completeCancelHandler);
        cancelButton.setOnAction(completeCancelHandler);
        addToTaxi.setOnAction(completeCancelHandler);
        
        
        gridPane.add(headingLabel, 0, 0, 2, 1);
        gridPane.add(playerView, 0, 1, 1, 3);
        gridPane.add(flagView, 1, 1, 1, 1);
        gridPane.add(nameLabel, 1, 2, 1, 1);
        gridPane.add(QPLabel, 1, 3, 1, 1);
        gridPane.add(fantasyTeamLabel, 0, 4, 1, 1);
        gridPane.add(fantasyTeamComboBox, 1, 4,1,1);
        gridPane.add(positionLabel, 0, 5, 1, 1);
        gridPane.add(positionComboBox, 1, 5,3,1);
        gridPane.add(contractLabel, 0, 6, 1, 1);
        gridPane.add(contractComboBox, 1, 6,1,1);
        gridPane.add(salaryLabel, 0, 7, 1, 1);
        
        gridPane.add(completeButton, 0, 8,3,1);
        gridPane.add(addToTaxi, 3, 8,3,1);
        gridPane.add(cancelButton, 6, 8,1,1);
        
        dialogScene = new Scene(gridPane);
        dialogScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        this.setScene(dialogScene);
    }
    
    public String getSelection() {
        return selection;
    }
    public boolean isPositionChanged()
    {
        return positionChanged;
    }
    
    public Player getPlayer() {
        return player;
    }
    public FantasyTeam getTeam()
    {
        return fantasyTeam;
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
    
    public boolean wasAddToTaxiSelected()
    {
        return selection.equals(TAXI);
    }
    
    public void showEditPlayerDialog(wolfie_GUI gui,Player itemToEdit) {
        // SET THE DIALOG TITLE
        setTitle(EDIT_PLAYER_TITLE);
        
        // LOAD THE SCHEDULE ITEM INTO OUR LOCAL OBJECT
        positionChanged = false;
        player = new Player();
        player.setContract(itemToEdit.getContract());
        player.setSalary(itemToEdit.getSalary());
        searchName = itemToEdit.getLastName()+itemToEdit.getFirstName();
        //label cant update
        
        QPLabel.setText(itemToEdit.getPosition());
        //avaliablePosition
        nameLabel.setText(itemToEdit.getLastName()+" "+itemToEdit.getFirstName());
        playerQP = itemToEdit.getPosition().split("_");
        fantasyTeamComboBox.setValue(null);
        fantasyTeamComboBox.setItems(gui.getDataManager().getDraft().getFantasyTeamList());
        fantasyTeamComboBox.getSelectionModel().selectFirst();
        fantasyTeam = fantasyTeamComboBox.getSelectionModel().getSelectedItem();
        
        if(fantasyTeamComboBox.getSelectionModel().getSelectedItem()!= null)
        {
            positionComboBox.setValue(null);
            avaliablePosition.clear();
            for (int i =0; i<playerQP.length;i++)
            {
                if(fantasyTeam.getHashMap().get(playerQP[i])>0)
                {
                    avaliablePosition.add(playerQP[i]);
                    
//                if(playerQP[i] == "1B")
//                {
//                    if(avaliablePosition.contains("CI")==false)
//                    {
//                        avaliablePosition.add("CI");
//                    }
//                }
                }
            }
            
            
            
        }
        else
        {
            positionComboBox.setValue(null);
            avaliablePosition.clear();
            
            for (int i =0; i<playerQP.length;i++)
            {
                
                avaliablePosition.add(playerQP[i]);
                
            }
        }
        
        if(fantasyTeamComboBox.getSelectionModel().getSelectedItem()!= null)
            {
                if(avaliablePosition.contains("1B")||avaliablePosition.contains("3B"))
                {
                    if(fantasyTeam.getHashMap().get("CI")>0)
                    {
                        avaliablePosition.add("CI");
                    }
                }
                if(avaliablePosition.contains("2B")||avaliablePosition.contains("SS"))
                {
                    if(fantasyTeam.getHashMap().get("MI")>0)
                    {
                        avaliablePosition.add("MI");
                    }
                }
                if(!avaliablePosition.contains("P"))
                {
                    if(fantasyTeam.getHashMap().get("U")>0)
                    {
                        avaliablePosition.add("U");
                    }
                }
            }
        
//        gridPane.add(nameLabel, 1, 2, 1, 1);
//        gridPane.add(QPLabel, 1, 3, 1, 1);
        
        
        playerPicture = new Image(PLAYER_PICTURE_PATH+searchName+JPG);
        playerView.setImage(playerPicture);
        
        flagName = itemToEdit.getNation();
        flagPicture = new Image(FLAG_PICTURE_PATH+flagName+PNG);
        flagView.setImage(flagPicture);
        
        
        // scheduleItem.setLink(itemToEdit.getLink());
        
        // AND THEN INTO OUR GUI
        loadGUIData();
        
        // AND OPEN IT UP
        this.showAndWait();
        
    }
    
    public void loadGUIData() {
        // LOAD THE UI STUFF
        salaryTextField.setText(String.valueOf(player.getSalary()));
        contractComboBox.setValue(player.getContract());
        
//          positionComboBox
        
        
//        firstnameTextField.setText(player.getFirstName());
//        lastnameTextField.setText(player.getLastName());
//        proteamComboBox.setValue(player.getTeam());
        //   datePicker.setValue(scheduleItem.getDate());
        //  urlTextField.setText(scheduleItem.getLink());
    }
}
