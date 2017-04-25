/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui;

/**
 *
 * @author Leon
 */

import static wdk.WDK_StartupConstants.*;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import properties_manager.PropertiesManager;
import wdk.WDK_PropertyType;
import wdk.controller.FantasyTeamController;
import wdk.controller.FileController;
import wdk.controller.PlayerEditController;
import wdk.data.Draft;
import wdk.data.DraftDataManager;
import wdk.data.DraftDataView;
import wdk.data.FantasyTeam;
import wdk.data.Player;
import wdk.data.ProTeam;
import wdk.file.DraftFileManager;
public class wolfie_GUI implements DraftDataView
{
    static final String PRIMARY_STYLE_SHEET = PATH_CSS + "csb_style.css";
    static final String CLASS_BORDERED_PANE = "bordered_pane";
    static final String CLASS_SMALL_BORDER = "small_border";
    static final String CLASS_SUBJECT_PANE = "subject_pane";
    static final String CLASS_HEADING_LABEL = "heading_label";
    static final String CLASS_SUBHEADING_LABEL = "subheading_label";
    static final String CLASS_PROMPT_LABEL = "prompt_label";
    static final String EMPTY_TEXT = "";
    static final int LARGE_TEXT_FIELD_LENGTH = 20;
    static final int SMALL_TEXT_FIELD_LENGTH = 5;
    
    
    DraftFileManager draftFileManager;
    FileController fileController;
    DraftDataManager dataManager;
    PlayerEditController playerController;
    FantasyTeamController teamController;
    VBox topWorkspacePane;
   
    SplitPane topWorkspaceSplitPane;
    ScrollPane workspaceScrollPane;
    
    GridPane playerPane;
    GridPane HomePane;
    GridPane DraftPane;
    GridPane MLBTeamPane;
    GridPane StandingPane;
    
    HBox switchPaneToolbar;
    Label playerInfoHeadingLabel;
    Label HomeInfoHeadingLabel;
    Label DraftInfoHeadingLabel;
    Label MLBTeamInfoHeadingLabel;
    Label StandingInfoHeadingLabel;
    
    // THINGS NEED FOR PLAYER PANE
    ObservableList <Player> playerTableList;
    
    Label SearchLabel;
    TextField searchTextField;
    GridPane radioButtonPane;
    RadioButton All;
    RadioButton C;
    RadioButton B1;
    RadioButton CI;
    RadioButton B3;
    RadioButton B2;
    RadioButton MI;
    RadioButton SS;
    RadioButton OF;
    RadioButton U;
    RadioButton P;
    Button addPlayerButton;
    Button removePlayerButton;
    TableView<Player> playerTable;
    TableColumn firstnameColumn;
    TableColumn lastnameColumn;
    TableColumn teamColumn;
    TableColumn positionColumn;
    TableColumn birthColumn;
    TableColumn RWColumn;
    TableColumn HRSVColumn;
    TableColumn RBIKColumn;
    TableColumn SBERAColumn;
    TableColumn BAWHIPColumn;
    TableColumn estimateValueColumn;
    TableColumn noteColumn;
    
    VBox playerVBox;
    
    //THINGS IN FANTASY TEAM PANE(HOME PANE)
    Label draftName;
    TextField draftNameTextField;
    Button addFantasyTeamButton;
    Button removeFantasyTeamButton;
    Button editFantasyTeamButton;
    Button movePlayerButton;
    Label selectTeamLabel;
    ComboBox selectTeamComboBox;
    ObservableList<String> teamNameList;
   
    VBox fantasyTeamBox;
    VBox startLineBox;
    VBox taxiQuadBox;
    
    ObservableList<FantasyTeam> fantasyTeamList;
    //STARTLINE TABLE
    Label startLineLabel;
    TableView<Player> startLineTable;
    TableColumn startFirstnameColumn;
    TableColumn startLastnameColumn;
    TableColumn startTeamColumn;
    TableColumn startPositionColumn;  //player qualify position
    TableColumn startGivePositionColumn; // team assigned position for this player
    TableColumn startRWColumn;
    TableColumn startHRSVColumn;
    TableColumn startRBIKColumn;
    TableColumn startSBERAColumn;
    TableColumn startBAWHIPColumn;
    TableColumn startEstimateValueColumn;
    TableColumn startcontractColumn;
    TableColumn startSalaryColumn;
    
    
    //TAXI QUAD TABLE
    Label taxiQuadLabel;
    TableView<Player> taxiQuadTable;
    TableColumn taxiFirstnameColumn;
    TableColumn taxiLastnameColumn;
    TableColumn taxiTeamColumn;
    TableColumn taxiPositionColumn;  //player qualify position
    TableColumn taxiGivePositionColumn; // team assigned position for this player
    TableColumn taxiRWColumn;
    TableColumn taxiHRSVColumn;
    TableColumn taxiRBIKColumn;
    TableColumn taxiSBERAColumn;
    TableColumn taxiBAWHIPColumn;
    TableColumn taxiEstimateValueColumn;
    TableColumn taxicontractColumn;
    TableColumn taxiSalaryColumn;
    
    //MLB TEAM SCREEN STAFF
    VBox MLBTeamBox;
    Label MLBTeam;
    ComboBox ProTeamComboBox;
    TableView <Player> MLBTeamTable;
    ObservableList <Player> MLBPlayer;
    TableColumn MLBFirstName;
    TableColumn MLBLastName;
    TableColumn MLBPosition;
    
    //FANTASY STANDING SCREEN
    VBox fantasyStandingBox;
    TableView <FantasyTeam> fantasyTeamTable;
    ObservableList <FantasyTeam> standingTeamList;
    TableColumn teamName;
    TableColumn playerNeeded;
    TableColumn moneyLeft;
    TableColumn moneyPerPerson;
    TableColumn R;
    TableColumn HR;
    TableColumn RBI;
    TableColumn SB;
    TableColumn BA;
    TableColumn W;
    TableColumn SV;
    TableColumn K;
    TableColumn ERA;
    TableColumn WHIP;
    TableColumn totalPoint;
    
    
    
    //DRAFT SCREEN
    VBox draftBox;
    Button draftPlayer;
    Button autoDraft;
    Button pause;
    TableView draftTable;
    ObservableList<Player> draftList;
    TableColumn draftPickNumber;
    TableColumn draftFirstName;
    TableColumn draftLastName;
    TableColumn draftFantasyTeam;
    TableColumn draftContract;
    TableColumn draftSalary;
    
    
    
    
     Stage primaryStage;
     
     Scene primaryScene;
     
     BorderPane wdkPane;
     
    FlowPane fileToolbarPane;
    Button newDraftButton;
    Button loadDraftButton;
    Button saveDraftButton;
    Button exportDraftButton;
    Button exitButton;

   
    
    
    FlowPane draftToolbarPane;
    Button playerButton;
    Button homeButton;
    Button draftButton;
    Button MLBTeamButton;
    Button StandingButton;
    
    
    // WE'LL ORGANIZE OUR WORKSPACE COMPONENTS USING A BORDER PANE
    BorderPane workspacePane;
    boolean workspaceActivated;
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    
    
    public wolfie_GUI(Stage initPrimaryStage) 
    {
        primaryStage = initPrimaryStage;
    }
    
    public ComboBox getComboBoxTeam()
    {
        return selectTeamComboBox;
    }
    
     public Stage getWindow() {
        return primaryStage;
    }
    
    public MessageDialog getMessageDialog() {
        return messageDialog;
    }
    
    public DraftDataManager getDataManager() {
        return dataManager;
    }
    
    public ObservableList <Player> getPlayerTableList()
    {
        return playerTableList;
    }
    
    public void initGUI(String windowTitle) throws IOException 
    {
        
        initDialogs() ;
        // INIT THE TOOLBAR
        initFileToolbar();

        // INIT THE CENTER WORKSPACE CONTROLS BUT DON'T ADD THEM
        // TO THE WINDOW YET
        initWorkspace();

        // NOW SETUP THE EVENT HANDLERS
        initEventHandlers();

        // AND FINALLY START UP THE WINDOW (WITHOUT THE WORKSPACE)
        initWindow(windowTitle);
    }
    
    
     public void activateWorkspace() {
        if (!workspaceActivated) {
            // PUT THE WORKSPACE IN THE GUI
            wdkPane.setCenter(workspacePane);
            workspaceActivated = true;
        }
    }
     
       public void reloadDraft(Draft draftToReload) {
        // FIRST ACTIVATE THE WORKSPACE IF NECESSARY
        if (!workspaceActivated) {
            activateWorkspace();
        }
       }
     
    private void initWorkspace() throws IOException 
    {
        initPlayerPane();
        initDraftToolbar();
        initHomePane();
        initDraftPane();
        initMLBTeamPane();
        initStandingPane();
//        initTopWorkspace();
        workspacePane = new BorderPane();
//        workspacePane.setTop(topWorkspacePane);
        workspacePane.setCenter(fantasyTeamBox);
        workspacePane.setBottom(draftToolbarPane);
        workspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
        
        workspaceScrollPane = new ScrollPane();
        workspaceScrollPane.setContent(workspacePane);
        workspaceScrollPane.setFitToWidth(true);
        
        workspaceActivated = false;
    }
    

    
    private void initWindow(String windowTitle) {
        // SET THE WINDOW TITLE
        primaryStage.setTitle(windowTitle);

        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        // ADD THE TOOLBAR ONLY, NOTE THAT THE WORKSPACE
        // HAS BEEN CONSTRUCTED, BUT WON'T BE ADDED UNTIL
        // THE USER STARTS EDITING A COURSE
        wdkPane = new BorderPane();
        wdkPane.setTop(fileToolbarPane);
        primaryScene = new Scene(wdkPane);

        // NOW TIE THE SCENE TO THE WINDOW, SELECT THE STYLESHEET
        // WE'LL USE TO STYLIZE OUR GUI CONTROLS, AND OPEN THE WINDOW
        primaryScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    private void initFileToolbar() 
    {
        fileToolbarPane = new FlowPane();

        // HERE ARE OUR FILE TOOLBAR BUTTONS, NOTE THAT SOME WILL
        // START AS ENABLED (false), WHILE OTHERS DISABLED (true)
        newDraftButton = initChildButton(fileToolbarPane, WDK_PropertyType.NEW_COURSE_ICON, WDK_PropertyType.NEW_COURSE_TOOLTIP, false);
        loadDraftButton = initChildButton(fileToolbarPane, WDK_PropertyType.LOAD_COURSE_ICON, WDK_PropertyType.LOAD_COURSE_TOOLTIP, false);
        saveDraftButton = initChildButton(fileToolbarPane, WDK_PropertyType.SAVE_COURSE_ICON, WDK_PropertyType.SAVE_COURSE_TOOLTIP, true);
        exportDraftButton = initChildButton(fileToolbarPane, WDK_PropertyType.EXPORT_PAGE_ICON, WDK_PropertyType.EXPORT_PAGE_TOOLTIP, true);
        exitButton = initChildButton(fileToolbarPane, WDK_PropertyType.EXIT_ICON, WDK_PropertyType.EXIT_TOOLTIP, false);
    }
    
    private void initDraftToolbar()
    {
        draftToolbarPane = new FlowPane();
        homeButton = initChildButton(draftToolbarPane, WDK_PropertyType.HOME_ICON, WDK_PropertyType.HOME_TOOLTIP, false);
        playerButton = initChildButton(draftToolbarPane, WDK_PropertyType.PLAYER_ICON, WDK_PropertyType.PLAYER_TOOLTIP, false);
        draftButton = initChildButton(draftToolbarPane, WDK_PropertyType.DRAFT_ICON, WDK_PropertyType.DRAFT_TOOLTIP, false);
        MLBTeamButton = initChildButton(draftToolbarPane, WDK_PropertyType.MLB_TEAM_ICON, WDK_PropertyType.MLB_TEAM_TOOLTIP, false);
        StandingButton = initChildButton(draftToolbarPane, WDK_PropertyType.FANTASY_STANDING_ICON, WDK_PropertyType.FANTASY_STANDING_TOOLTIP, false);
    }
    
    private  void initRadioButtonPane()throws IOException 
    {
        radioButtonPane = new GridPane();
        final ToggleGroup toogleGroup = new ToggleGroup();
        radioButtonPane.getStyleClass().add(CLASS_BORDERED_PANE);
        All= initGridRadioButton(radioButtonPane,"All",toogleGroup,0,0,4,4);
        All.setSelected(true);
        C =initGridRadioButton(radioButtonPane,"C",toogleGroup,5,0,4,4);
        B1=initGridRadioButton(radioButtonPane,"1B",toogleGroup,10,0,4,4);
        CI=initGridRadioButton(radioButtonPane,"CI",toogleGroup,15,0,4,4);
        B3=initGridRadioButton(radioButtonPane,"3B",toogleGroup,20,0,4,4);
        B2=initGridRadioButton(radioButtonPane,"2B",toogleGroup,25,0,4,4);
        MI=initGridRadioButton(radioButtonPane,"MI",toogleGroup,30,0,4,4);
        SS=initGridRadioButton(radioButtonPane,"SS",toogleGroup,35,0,4,4);
        OF=initGridRadioButton(radioButtonPane,"OF",toogleGroup,40,0,4,4);
        U=initGridRadioButton(radioButtonPane,"U",toogleGroup,45,0,4,4);
        P=initGridRadioButton(radioButtonPane,"P",toogleGroup,50,0,4,4);
    }
    private void initPlayerPane()throws IOException 
    {
        playerPane = new GridPane();
        playerInfoHeadingLabel = initGridLabel(playerPane, WDK_PropertyType.PLAYER_HEADING_LABEL, CLASS_HEADING_LABEL, 0, 0, 5, 1);
        playerPane.getStyleClass().add(CLASS_SMALL_BORDER);
        addPlayerButton = initGridButton(playerPane,WDK_PropertyType.ADD_ICON,WDK_PropertyType.ADD_ITEM_TOOLTIP,false,0,1,1,1);
        removePlayerButton =initGridButton(playerPane,WDK_PropertyType.MINUS_ICON,WDK_PropertyType.DELETE_TOOLTIP,false,1,1,1,1);
        SearchLabel = initGridLabel(playerPane,WDK_PropertyType.SEARCH_LABEL,CLASS_PROMPT_LABEL,2,1,1,1);
        searchTextField = initGridTextField(playerPane, 20, EMPTY_TEXT, true, 3, 1, 1, 1);
        
        initRadioButtonPane();
      // playerPane.addRow(3, radioButtonPane);
       
    playerTable = new TableView();
    //playerPane.addRow(4, playerTable);
    playerTable.setEditable(true);
    firstnameColumn =new TableColumn("First");
    lastnameColumn =new TableColumn("Last");
    teamColumn =new TableColumn("Pro Team");
    positionColumn =new TableColumn("Positions");
    birthColumn =new TableColumn("Year of Birth");
    RWColumn =new TableColumn("R/W");
    HRSVColumn =new TableColumn("HR/SV");
    RBIKColumn =new TableColumn("RBI/K");
    SBERAColumn =new TableColumn("SB/ERA");
    BAWHIPColumn =new TableColumn("BA/WHIP");
    estimateValueColumn =new TableColumn("Estimated Value");
    noteColumn =new TableColumn("Notes");
    
    lastnameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));
    firstnameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("firstName"));
    teamColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("team"));
    positionColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
    birthColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("birth"));
    RWColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("rw"));
    HRSVColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("hrsv"));
    RBIKColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("rbik"));
    SBERAColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("sbera"));
    BAWHIPColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("bawhip"));
   // estimateValueColumn =new TableColumn("Estimated Value");
    noteColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    noteColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("notes"));

    
    playerTable.getColumns().add(firstnameColumn);
    playerTable.getColumns().add(lastnameColumn);
    playerTable.getColumns().add(teamColumn);
    playerTable.getColumns().add(positionColumn);
    playerTable.getColumns().add(birthColumn);
    playerTable.getColumns().add(RWColumn);
    playerTable.getColumns().add(HRSVColumn);
    playerTable.getColumns().add(RBIKColumn);
    playerTable.getColumns().add(SBERAColumn);
    playerTable.getColumns().add(BAWHIPColumn);
    playerTable.getColumns().add(estimateValueColumn);
    playerTable.getColumns().add(noteColumn);
    
    playerVBox = new VBox();
        playerVBox.getChildren().add(playerPane);
        playerVBox.getChildren().add(radioButtonPane);
        playerVBox.getChildren().add(playerTable);
        playerTableList = dataManager.getDraft().getDisplayList();
    playerTable.setItems(playerTableList);
        
    }
    private void initHomePane()throws IOException
    {
        HomePane = new GridPane();
        HomeInfoHeadingLabel =initGridLabel(HomePane, WDK_PropertyType.HOME_HEADING_LABEL, CLASS_HEADING_LABEL, 0, 0, 4, 1);
        HomePane.getStyleClass().add(CLASS_SMALL_BORDER);
        draftName = initGridLabel(HomePane,WDK_PropertyType.DRAFT_LABEL,CLASS_PROMPT_LABEL,0,1,2,1);
        draftNameTextField =initGridTextField(HomePane, 20, EMPTY_TEXT, true, 2, 1, 4, 1);
        draftNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
           dataManager.getDraft().setDraftName(newValue);
        });
        addFantasyTeamButton = initGridButton(HomePane,WDK_PropertyType.ADD_ICON,WDK_PropertyType.ADD_ITEM_TOOLTIP,false,0,2,1,1);
        removeFantasyTeamButton = initGridButton(HomePane,WDK_PropertyType.MINUS_ICON,WDK_PropertyType.DELETE_TOOLTIP,false,1,2,1,1);
        editFantasyTeamButton =  initGridButton(HomePane,WDK_PropertyType.EDIT_ICON,WDK_PropertyType.EDIT_TOOLTIP,false,2,2,1,1);
        movePlayerButton =  initGridButton(HomePane,WDK_PropertyType.MOVE_PLAYER_ICON,WDK_PropertyType.EDIT_TOOLTIP,false,3,2,1,1);
        selectTeamLabel = initGridLabel(HomePane,WDK_PropertyType.SELECT_TEAM_LABEL,CLASS_PROMPT_LABEL,4,2,1,1);
        selectTeamComboBox = initGridComboBox(HomePane,5,2,1,1);
        
        /// wrong place maybe?
        teamNameList = FXCollections.observableArrayList();
        
        selectTeamComboBox.setItems(dataManager.getDraft().getFantasyTeamList());
//        selectTeamComboBox.setItems(teamNameList);
//        selectTeamComboBox.getSelectionModel().selectFirst();
        
        selectTeamComboBox.setOnAction(e->{
            if(selectTeamComboBox.getSelectionModel().getSelectedItem()!=null)
            {
            updateStartLineTable((FantasyTeam)selectTeamComboBox.getSelectionModel().getSelectedItem());
            updateTaxiQuadTable((FantasyTeam)selectTeamComboBox.getSelectionModel().getSelectedItem());
            }
            else
            {
                startLineTable.setItems(null);
                taxiQuadTable.setItems(null);
            }
        });
        
        
        startLineBox = new VBox();
        
        
     
        startLineLabel = initChildLabel(startLineBox,WDK_PropertyType.START_LINE_LABEL,CLASS_HEADING_LABEL);
        startLineTable = new TableView();
        startFirstnameColumn =new TableColumn("First");
        startLastnameColumn =new TableColumn("Last");
        startTeamColumn =new TableColumn("Pro Team");
        startPositionColumn = new TableColumn("QP");   
        startGivePositionColumn = new TableColumn("Position");
        startRWColumn =new TableColumn("R/W");
        startHRSVColumn =new TableColumn("HR/SV");
        startRBIKColumn =new TableColumn("RBI/K");
        startSBERAColumn =new TableColumn("SB/ERA");
        startBAWHIPColumn =new TableColumn("BA/WHIP");
        startEstimateValueColumn = new TableColumn("Estimate Value");
        startcontractColumn = new TableColumn("Contract");
        startSalaryColumn = new TableColumn("Salary");
        
        
        startLineTable.getColumns().add(startGivePositionColumn);
        startLineTable.getColumns().add(startFirstnameColumn);
        startLineTable.getColumns().add(startLastnameColumn);
        startLineTable.getColumns().add(startTeamColumn);
        startLineTable.getColumns().add(startPositionColumn);
        startLineTable.getColumns().add(startRWColumn);
        startLineTable.getColumns().add(startHRSVColumn);
        startLineTable.getColumns().add(startRBIKColumn);
        startLineTable.getColumns().add(startSBERAColumn);
        startLineTable.getColumns().add(startBAWHIPColumn);
        startLineTable.getColumns().add(startEstimateValueColumn);
        startLineTable.getColumns().add(startcontractColumn);
        startLineTable.getColumns().add(startSalaryColumn);
        
        startGivePositionColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("actualPosition"));
        startFirstnameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("firstName"));
        startLastnameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName")); 
        startTeamColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("team"));
        startPositionColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
        startRWColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("rw"));
        startHRSVColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("hrsv"));
        startRBIKColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("rbik"));
        startSBERAColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("sbera"));
        startBAWHIPColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("bawhip"));
        //startEstimateValueColumn.setCellValueFactory(new PropertyValueFactory<Player, String>);
        startcontractColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("contract"));
        startSalaryColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("salary"));
        
        
        
        startLineBox.getChildren().add(startLineTable);
        
        taxiQuadBox = new VBox();
        taxiQuadLabel = initChildLabel(taxiQuadBox,WDK_PropertyType.TAXI_QUAD_LABEL,CLASS_HEADING_LABEL);
        taxiQuadTable = new TableView();
        
        
        taxiFirstnameColumn =new TableColumn("First");
        taxiLastnameColumn =new TableColumn("Last");
        taxiTeamColumn =new TableColumn("Pro Team");
        taxiPositionColumn = new TableColumn("QP");   
        taxiGivePositionColumn = new TableColumn("Position");
        taxiRWColumn =new TableColumn("R/W");
        taxiHRSVColumn =new TableColumn("HR/SV");
        taxiRBIKColumn =new TableColumn("RBI/K");
        taxiSBERAColumn =new TableColumn("SB/ERA");
        taxiBAWHIPColumn =new TableColumn("BA/WHIP");
        taxiEstimateValueColumn = new TableColumn("Estimate Value");
        taxicontractColumn = new TableColumn("Contract");
        taxiSalaryColumn = new TableColumn("Salary");
        
        
        
        taxiQuadTable.getColumns().add(taxiFirstnameColumn);
        taxiQuadTable.getColumns().add(taxiLastnameColumn);
        taxiQuadTable.getColumns().add(taxiTeamColumn);
        taxiQuadTable.getColumns().add(taxiPositionColumn);
        taxiQuadTable.getColumns().add(taxiRWColumn);
        taxiQuadTable.getColumns().add(taxiHRSVColumn);
        taxiQuadTable.getColumns().add(taxiRBIKColumn);
        taxiQuadTable.getColumns().add(taxiSBERAColumn);
        taxiQuadTable.getColumns().add(taxiBAWHIPColumn);
        taxiQuadTable.getColumns().add(taxiEstimateValueColumn);
        taxiQuadTable.getColumns().add(taxicontractColumn);
        taxiQuadTable.getColumns().add(taxiSalaryColumn);
        
        
        taxiFirstnameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("firstName"));
        taxiLastnameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName")); 
        taxiTeamColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("team"));
        taxiPositionColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
        taxiRWColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("rw"));
        taxiHRSVColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("hrsv"));
        taxiRBIKColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("rbik"));
        taxiSBERAColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("sbera"));
        taxiBAWHIPColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("bawhip"));
        //startEstimateValueColumn.setCellValueFactory(new PropertyValueFactory<Player, String>);
        taxicontractColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("contract"));
        taxiSalaryColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("salary"));
        
        taxiQuadTable.setItems(MLBPlayer);
        
        taxiQuadBox.getChildren().add(taxiQuadTable);
        
        fantasyTeamBox = new VBox();
        fantasyTeamBox.getChildren().add(HomePane);
        fantasyTeamBox.getChildren().add(startLineBox);
        fantasyTeamBox.getChildren().add(taxiQuadBox);
        
    }
    
    private void initDraftPane()throws IOException
    {
        DraftPane = new GridPane();
        DraftInfoHeadingLabel =initGridLabel(DraftPane, WDK_PropertyType.DRAFT_HEADING_LABEL, CLASS_HEADING_LABEL, 0, 0, 4, 1);
        DraftPane.getStyleClass().add(CLASS_SMALL_BORDER);
        
        draftPlayer = initGridButton(DraftPane,WDK_PropertyType.STAR_ICON,WDK_PropertyType.DELETE_TOOLTIP,false,0,1,1,1);
        autoDraft = initGridButton(DraftPane,WDK_PropertyType.PLAY_ICON,WDK_PropertyType.DELETE_TOOLTIP,false,1,1,1,1);
        pause = initGridButton(DraftPane,WDK_PropertyType.PAUSE_ICON,WDK_PropertyType.DELETE_TOOLTIP,false,2,1,1,1);
        draftTable = new TableView();
        draftList = FXCollections.observableArrayList();
        
        
        draftPickNumber = new TableColumn("Pick #");
        draftFirstName = new TableColumn("First");
        draftLastName = new TableColumn("Last");
        draftFantasyTeam = new TableColumn("Team");
        draftContract = new TableColumn("Contract");
        draftSalary = new TableColumn("Salary");
        
        draftTable.getColumns().add(draftPickNumber);
        draftTable.getColumns().add(draftFirstName);
        draftTable.getColumns().add(draftLastName);
        draftTable.getColumns().add(draftFantasyTeam);
        draftTable.getColumns().add(draftContract);
        draftTable.getColumns().add(draftSalary);
        
        draftPickNumber.setCellValueFactory(new PropertyValueFactory<Player, Integer>("pickNum"));
        draftFirstName.setCellValueFactory(new PropertyValueFactory<Player, String>("firstName"));
        draftLastName.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));
        draftFantasyTeam.setCellValueFactory(new PropertyValueFactory<Player, String>("assignTeam"));
        draftContract.setCellValueFactory(new PropertyValueFactory<Player, String>("contract"));
        draftSalary.setCellValueFactory(new PropertyValueFactory<Player, Integer>("salary"));
        
        draftTable.setItems(dataManager.getDraft().getDraftList());
        
        draftBox = new VBox();
        draftBox.getChildren().add(DraftPane);
        draftBox.getChildren().add(draftTable);
        
        
    }
    
    
    private void initMLBTeamPane()throws IOException
    {
        MLBTeamPane = new GridPane();
        MLBTeamInfoHeadingLabel =initGridLabel(MLBTeamPane, WDK_PropertyType.MLB_TEAM_HEADING_LABEL, CLASS_HEADING_LABEL, 0, 0, 4, 1);
        MLBTeamPane.getStyleClass().add(CLASS_SMALL_BORDER);
        
        MLBTeam = initGridLabel(MLBTeamPane,WDK_PropertyType.MLBTEAM_LABEL,CLASS_PROMPT_LABEL,0,1,1,1);
        
        ProTeamComboBox = initGridComboBox(MLBTeamPane,1,1,2,1);
        ObservableList<String> proTeamChoices = FXCollections.observableArrayList();
        for (ProTeam s : ProTeam.values()) 
        {
            proTeamChoices.add(s.toString());
        }
        ProTeamComboBox.setItems(proTeamChoices);
        
//        ProTeamComboBox.getSelectionModel().selectFirst();
        ProTeamComboBox.setOnAction(e->{ 
            updateMLBTeamTable((String)ProTeamComboBox.getSelectionModel().getSelectedItem());
        });
        
        MLBPlayer = FXCollections.observableArrayList();
        
        MLBTeamTable = new TableView();
        MLBFirstName =new TableColumn("First");
        MLBLastName =new TableColumn("Last");
        MLBPosition =new TableColumn("Positions");
        
        
        MLBTeamTable.getColumns().add(MLBLastName);
        MLBTeamTable.getColumns().add(MLBFirstName);
        MLBTeamTable.getColumns().add(MLBPosition);
        
        MLBFirstName.setCellValueFactory(new PropertyValueFactory<Player, String>("firstName"));
        MLBLastName.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName")); 
        MLBPosition.setCellValueFactory(new PropertyValueFactory<Player, String>("position")); 
        
        MLBTeamTable.setItems(MLBPlayer);
        
        MLBTeamBox = new VBox();
        MLBTeamBox.getChildren().add(MLBTeamPane);
        MLBTeamBox.getChildren().add(MLBTeamTable);
        
    }
    
    private void initStandingPane()throws IOException
    {
        StandingPane = new GridPane();
        StandingInfoHeadingLabel =initGridLabel(StandingPane, WDK_PropertyType.FANTASY_STANDING_HEADING_LABEL, CLASS_HEADING_LABEL, 0, 0, 4, 1);
        StandingPane.getStyleClass().add(CLASS_SMALL_BORDER);
        
        fantasyTeamTable = new TableView();
//        standingTeamList =FXCollections.observableArrayList();
//        standingTeamList = dataManager.getDraft().getFantasyTeamList();
        
        teamName = new TableColumn("Team Name");
        playerNeeded = new TableColumn("Player Needed");
        moneyLeft = new TableColumn("$ Left");
        moneyPerPerson = new TableColumn("$ PP");
        R = new TableColumn("R");
        HR = new TableColumn("HR");
        RBI = new TableColumn("RBI");
        SB = new TableColumn("SB");
        BA = new TableColumn("BA");
        W = new TableColumn("W");
        SV = new TableColumn("SV");
        K = new TableColumn("K");
        ERA = new TableColumn("ERA");
        WHIP = new TableColumn("WHIP");
        totalPoint = new TableColumn("Total Points");
        
        fantasyTeamTable.getColumns().add(teamName);
        fantasyTeamTable.getColumns().add(playerNeeded);
        fantasyTeamTable.getColumns().add(moneyLeft);
        fantasyTeamTable.getColumns().add(moneyPerPerson);
        fantasyTeamTable.getColumns().add(R);
        fantasyTeamTable.getColumns().add(HR);
        fantasyTeamTable.getColumns().add(RBI);
        fantasyTeamTable.getColumns().add(SB);
        fantasyTeamTable.getColumns().add(BA);
        fantasyTeamTable.getColumns().add(W);
        fantasyTeamTable.getColumns().add(SV);
        fantasyTeamTable.getColumns().add(K);
        fantasyTeamTable.getColumns().add(ERA);
        fantasyTeamTable.getColumns().add(WHIP);
        fantasyTeamTable.getColumns().add(totalPoint);
        
        teamName.setCellValueFactory(new PropertyValueFactory<FantasyTeam, String>("teamName"));
        playerNeeded.setCellValueFactory(new PropertyValueFactory<FantasyTeam, Integer>("playerNeed"));
        moneyLeft.setCellValueFactory(new PropertyValueFactory<FantasyTeam, Integer>("moneyLeft"));
        moneyPerPerson.setCellValueFactory(new PropertyValueFactory<FantasyTeam, Float>("moneyPerPerson"));
        R.setCellValueFactory(new PropertyValueFactory<FantasyTeam, Double>("r"));
        HR.setCellValueFactory(new PropertyValueFactory<FantasyTeam, Double>("hr"));
        RBI.setCellValueFactory(new PropertyValueFactory<FantasyTeam, Double>("rbi"));
        SB.setCellValueFactory(new PropertyValueFactory<FantasyTeam, Double>("sb"));
        BA.setCellValueFactory(new PropertyValueFactory<FantasyTeam, Double>("ba"));
        W.setCellValueFactory(new PropertyValueFactory<FantasyTeam, Double>("w"));
        SV.setCellValueFactory(new PropertyValueFactory<FantasyTeam, Double>("sv"));
        K.setCellValueFactory(new PropertyValueFactory<FantasyTeam, Double>("k"));
        ERA.setCellValueFactory(new PropertyValueFactory<FantasyTeam, Double>("era"));
        WHIP.setCellValueFactory(new PropertyValueFactory<FantasyTeam, Double>("whip"));
        totalPoint.setCellValueFactory(new PropertyValueFactory<FantasyTeam, Integer>("totalPoint"));
       
        
        fantasyTeamTable.setItems(dataManager.getDraft().getFantasyTeamList());
        fantasyStandingBox = new VBox();
        fantasyStandingBox.getChildren().add(StandingPane);
        fantasyStandingBox.getChildren().add(fantasyTeamTable);
        
    }
      public void updateToolbarControls(boolean saved) 
      {
        // THIS TOGGLES WITH WHETHER THE CURRENT COURSE
        // HAS BEEN SAVED OR NOT
        saveDraftButton.setDisable(false);

        // ALL THE OTHER BUTTONS ARE ALWAYS ENABLED
        // ONCE EDITING THAT FIRST COURSE BEGINS
        loadDraftButton.setDisable(false);
        exportDraftButton.setDisable(false);

        // NOTE THAT THE NEW, LOAD, AND EXIT BUTTONS
        // ARE NEVER DISABLED SO WE NEVER HAVE TO TOUCH THEM
    }
    
     private void initEventHandlers() throws IOException 
     {
         fileController = new FileController(messageDialog, yesNoCancelDialog, draftFileManager);
         playerController = new PlayerEditController(primaryStage, messageDialog, yesNoCancelDialog,this);
         teamController = new FantasyTeamController(primaryStage, messageDialog, yesNoCancelDialog);
          newDraftButton.setOnAction(e -> {
            fileController.handleNewDraftRequest(this);
        });
          
          exitButton.setOnAction(e-> {
              fileController.handleExitRequest(this);
          });
          
          playerButton.setOnAction(e->{
              workspacePane.setCenter(playerVBox);
          });
           homeButton.setOnAction(e->{
              workspacePane.setCenter(fantasyTeamBox);
          });
           
           draftButton.setOnAction(e->{
              workspacePane.setCenter(draftBox);
          });

           MLBTeamButton.setOnAction(e->{
              workspacePane.setCenter(MLBTeamBox);
          });
           
           StandingButton.setOnAction(e->{
              workspacePane.setCenter(fantasyStandingBox);
          });
           
           addPlayerButton.setOnAction(e->{
               playerController.handleAddPlayerRequest(this);
           });
           
           removePlayerButton.setOnAction(e->{
               playerController.handleRemovePlayerRequest(this, playerTable.getSelectionModel().getSelectedItem());
           });
           
           addFantasyTeamButton.setOnAction(e->{
               teamController.handleAddTeamRequest(this);
               
               if(dataManager.getDraft().getFantasyTeamList().size()>0)
               {
               selectTeamComboBox.getSelectionModel().selectFirst();
               selectTeamComboBox.setItems(dataManager.getDraft().getFantasyTeamList());
               }
//               teamNameList.clear();
//               for(int i = 0; i<dataManager.getDraft().getFantasyTeamList().size();i++)
//                {
//                    teamNameList.add(dataManager.getDraft().getFantasyTeamList().get(i).getTeamName());
//                }
           });
           removeFantasyTeamButton.setOnAction(e->{
               teamController.handleRemoveTeamRequest(this, (FantasyTeam)selectTeamComboBox.getSelectionModel().getSelectedItem());
               selectTeamComboBox.setItems(dataManager.getDraft().getFantasyTeamList());
               if(dataManager.getDraft().getFantasyTeamList().size() == 0)
               {
                   selectTeamComboBox.setItems(null);
               }
//               teamNameList.clear();
//               for(int i = 0; i<dataManager.getDraft().getFantasyTeamList().size();i++)
//                {
//                    teamNameList.add(dataManager.getDraft().getFantasyTeamList().get(i).getTeamName());
//                }
           });
           editFantasyTeamButton.setOnAction(e->{
               teamController.handleEditTeamRequest(this, (FantasyTeam)selectTeamComboBox.getSelectionModel().getSelectedItem());
           });
           
           movePlayerButton.setOnAction(e->{
               
               playerController.handleRemoveTeamMemberRequest(this, startLineTable.getSelectionModel().getSelectedItem());
           });
           
           draftPlayer.setOnAction(e->{
               playerController.handleDraftRandomPlayerRequest(this);
           });
           
           autoDraft.setOnAction(e->{
               playerController.handleAutoDraftRequest(this);
           });
           
           pause.setOnAction(e->{
               playerController.handlePauseRequest();
           });
                   
           All.setOnAction(e->{
               findPlayerbyPosition("All");
               checkregisterTextField(searchTextField);
           });
           
           C.setOnAction(e->{
               findPlayerbyPosition("C");
               checkregisterTextField(searchTextField);
           });
           B1.setOnAction(e->{
               findPlayerbyPosition("1B");
               checkregisterTextField(searchTextField);
           });
           CI.setOnAction(e->{
               findPlayerbyPosition("CI");
               checkregisterTextField(searchTextField);
           });
           B2.setOnAction(e->{
               findPlayerbyPosition("2B");
               checkregisterTextField(searchTextField);
           });
           B3.setOnAction(e->{
               findPlayerbyPosition("3B");
               checkregisterTextField(searchTextField);
           });
           MI.setOnAction(e->{
               findPlayerbyPosition("MI");
               checkregisterTextField(searchTextField);
           });
           SS.setOnAction(e->{
               findPlayerbyPosition("SS");
               checkregisterTextField(searchTextField);
           });
           OF.setOnAction(e->{
               findPlayerbyPosition("OF");
               checkregisterTextField(searchTextField);
           });
           U.setOnAction(e->{
               findPlayerbyPosition("U");
               checkregisterTextField(searchTextField);
           });
           P.setOnAction(e->{
               findPlayerbyPosition("P");
               checkregisterTextField(searchTextField);
           });
           playerTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                // OPEN UP THE SCHEDULE ITEM EDITOR
                Player editplayer = playerTable.getSelectionModel().getSelectedItem();
                playerController.handleEditPlayerRequest(this, editplayer);
                
            }
        });
           
           startLineTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                // OPEN UP THE SCHEDULE ITEM EDITOR
                Player editplayer = startLineTable.getSelectionModel().getSelectedItem();
                playerController.handleSwitchTeamRequest(this,editplayer);
                
            }
        });
           registerTextFieldController(searchTextField);
           
     }  
     
//      private void initTopWorkspace() {
//        // HERE'S THE SPLIT PANE, ADD THE TWO GROUPS OF CONTROLS
//        
//        // THE TOP WORKSPACE PANE WILL ONLY DIRECTLY HOLD 2 THINGS, A LABEL
//        // AND A SPLIT PANE, WHICH WILL HOLD 2 ADDITIONAL GROUPS OF CONTROLS
//        topWorkspacePane = new VBox();
//        topWorkspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
//
//        // HERE'S THE LABEL
//        courseHeadingLabel = initChildLabel(topWorkspacePane, WDK_PropertyType.COURSE_HEADING_LABEL, CLASS_HEADING_LABEL);
//
//        // AND NOW ADD THE SPLIT PANE
////        topWorkspacePane.getChildren().add(topWorkspaceSplitPane);
//    }
      
     public void findPlayerbyPosition(String pos)
     {
         playerTableList.clear();
         Draft temp = dataManager.getDraft();
         ArrayList<Player> resultList = temp.getPlayerList();
       
            if(pos.equalsIgnoreCase("All"))
             {
                 for(int i = 0; i< resultList.size();i++)
                 {
                     
                         playerTableList.add(resultList.get(i));
                     
                 } 
             }
            else if(pos == "U")
            {
                for(int i = 0; i< resultList.size();i++)
                 {
                     if(resultList.get(i).getPosition()!= "P")
                         playerTableList.add(resultList.get(i));
                     
                 }
            }
            else
            {   
                 for(int i = 0; i< resultList.size();i++)
                 {
                     if(resultList.get(i).getPosition().contains(pos)==true)
                     {
                         playerTableList.add(resultList.get(i));
                     }
                 }

            }
     }
    
    // INIT A BUTTON AND ADD IT TO A CONTAINER IN A TOOLBAR
    private Button initChildButton(Pane toolbar, WDK_PropertyType icon, WDK_PropertyType tooltip, boolean disabled) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imagePath = "file:" + PATH_IMAGES + props.getProperty(icon.toString());
        Image buttonImage = new Image(imagePath);
        Button button = new Button();
        button.setDisable(disabled);
        button.setGraphic(new ImageView(buttonImage));
        Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip.toString()));
        button.setTooltip(buttonTooltip);
        toolbar.getChildren().add(button);
        return button;
    }
    
    private Button initGridButton(GridPane container,WDK_PropertyType icon, WDK_PropertyType tooltip, boolean disabled, int col, int row, int colSpan, int rowSpan)
    {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imagePath = "file:" + PATH_IMAGES + props.getProperty(icon.toString());
        Image buttonImage = new Image(imagePath);
        Button button = new Button();
        button.setDisable(disabled);
        button.setGraphic(new ImageView(buttonImage));
        Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip.toString()));
        button.setTooltip(buttonTooltip);
        container.add(button, col, row, colSpan, rowSpan);
        return button;
    }
    
    private RadioButton initGridRadioButton(GridPane pane, String label, ToggleGroup togglegroup, int col, int row,int colSpan, int rowSpan)
    {
        
        RadioButton button = new RadioButton(label);
        button.setToggleGroup(togglegroup);
        
        pane.add(button, col, row, colSpan, rowSpan);
//        Label label = initLabel(Labelproperty,styleClass);
//        pane.add(label, col+1, row, colSpan, rowSpan);
        return button;
    }
    
    // INIT A LABEL AND SET IT'S STYLESHEET CLASS
    private Label initLabel(WDK_PropertyType labelProperty, String styleClass) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String labelText = props.getProperty(labelProperty);
        Label label = new Label(labelText);
        label.getStyleClass().add(styleClass);
        return label;
    }

    // INIT A LABEL AND PLACE IT IN A GridPane INIT ITS PROPER PLACE
    private Label initGridLabel(GridPane container, WDK_PropertyType labelProperty, String styleClass, int col, int row, int colSpan, int rowSpan) {
        Label label = initLabel(labelProperty, styleClass);
        container.add(label, col, row, colSpan, rowSpan);
        return label;
    }

    // INIT A LABEL AND PUT IT IN A TOOLBAR
    private Label initChildLabel(Pane container, WDK_PropertyType labelProperty, String styleClass) {
        Label label = initLabel(labelProperty, styleClass);
        container.getChildren().add(label);
        return label;
    }

    // INIT A COMBO BOX AND PUT IT IN A GridPane
    private ComboBox initGridComboBox(GridPane container, int col, int row, int colSpan, int rowSpan) throws IOException {
        ComboBox comboBox = new ComboBox();
        container.add(comboBox, col, row, colSpan, rowSpan);
        return comboBox;
    }
    
    

    // LOAD THE COMBO BOX TO HOLD Course SUBJECTS


    // INIT A TEXT FIELD AND PUT IT IN A GridPane
    private TextField initGridTextField(GridPane container, int size, String initText, boolean editable, int col, int row, int colSpan, int rowSpan) {
        TextField tf = new TextField();
        tf.setPrefColumnCount(size);
        tf.setText(initText);
        tf.setEditable(editable);
        container.add(tf, col, row, colSpan, rowSpan);
        return tf;
    }

    // INIT A DatePicker AND PUT IT IN A GridPane
    private DatePicker initGridDatePicker(GridPane container, int col, int row, int colSpan, int rowSpan) {
        DatePicker datePicker = new DatePicker();
        container.add(datePicker, col, row, colSpan, rowSpan);
        return datePicker;
    }

    // INIT A CheckBox AND PUT IT IN A TOOLBAR
    private CheckBox initChildCheckBox(Pane container, String text) {
        CheckBox cB = new CheckBox(text);
        container.getChildren().add(cB);
        return cB;
    }

    // INIT A DatePicker AND PUT IT IN A CONTAINER
    private DatePicker initChildDatePicker(Pane container) {
        DatePicker dp = new DatePicker();
        container.getChildren().add(dp);
        return dp;
    }
    
     private void initDialogs() {
        messageDialog = new MessageDialog(primaryStage, CLOSE_BUTTON_LABEL);
        yesNoCancelDialog = new YesNoCancelDialog(primaryStage);
    }
    // LOADS CHECKBOX DATA INTO A Course OBJECT REPRESENTING A CoursePage

    public void setDraftFileManager(DraftFileManager initdraftFileManager) {
        draftFileManager = initdraftFileManager;
    }
   
        public void setDataManager(DraftDataManager initDataManager) {
        dataManager = initDataManager;
    }
        
    private void registerTextFieldController(TextField textField) 
    {
        
       ArrayList <Player> allPlayer = dataManager.getDraft().getPlayerList();
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String s =newValue.toLowerCase();
             ObservableList <Player> temp =FXCollections.observableArrayList();
            for (int i=0; i<allPlayer.size();i++)
            {
                if(allPlayer.get(i).getFirstName().toLowerCase().startsWith(s)||allPlayer.get(i).getLastName().toLowerCase().startsWith(s))
                {
                    temp.add(allPlayer.get(i));
                }
            }
            playerTableList = temp;
            playerTable.setItems(playerTableList);
        });
    }
      private void checkregisterTextField(TextField textField)
      {
          if(textField.getText().toString() == null)
          {
              
          }
          else
          {
              String s =textField.getText().toString().toLowerCase();
                ObservableList <Player> temp =FXCollections.observableArrayList();
            for (int i=0; i<playerTableList.size();i++)
            {
                if(playerTableList.get(i).getFirstName().toLowerCase().startsWith(s)||playerTableList.get(i).getLastName().toLowerCase().startsWith(s))
                {
                    temp.add(playerTableList.get(i));
                }
            }
            playerTableList = temp;
            playerTable.setItems(playerTableList);
          }
      }
      
      public void updateStartLineTable(FantasyTeam team)
      {
          startLineTable.setItems(team.getStartLineList());
      }
      public void updateTaxiQuadTable(FantasyTeam team)
      {
          taxiQuadTable.setItems(team.getTaxiQuadList());
      }
      
      public void updateMLBTeamTable(String s)
      {
          MLBPlayer.clear();
          ArrayList<Player> temp = dataManager.getDraft().getOriginalList();
          for(int i =0; i<temp.size();i++)
          {
              if(temp.get(i).getTeam().equals(s))
              {
                  MLBPlayer.add(temp.get(i));
              }
          }
          Collections.sort(MLBPlayer);
      }
      
      
      
//      public void calculateTotalPoint()
//      {
//          FXCollections.sort(standingTeamList,new Comparator()
//          {
//
//              @Override
//              public int compare(Object o1, Object o2) 
//              {
//                  FantasyTeam obj1 = (FantasyTeam)o1;
//                  FantasyTeam obj2 = (FantasyTeam)o2;
//                  return Integer.compare(obj1.getR(), obj2.getR());
//                  
//              }    
//          } );
//          int counter = this.getDataManager().getDraft().getFantasyTeamList().size();
//          for (int i = 0; i< this.getDataManager().getDraft().getFantasyTeamList().size();i++)
//          {
//              this.getDataManager().getDraft().getFantasyTeamList().get(i).setTotalPoint(counter);
//              counter--;
//          }
//          
//          
//          counter = this.getDataManager().getDraft().getFantasyTeamList().size();
//          
//          Collections.sort(standingTeamList,new Comparator()
//          {
//
//              @Override
//              public int compare(Object o1, Object o2) 
//              {
//                  FantasyTeam obj1 = (FantasyTeam)o1;
//                  FantasyTeam obj2 = (FantasyTeam)o2;
//                  return Integer.compare(obj1.getHR(), obj2.getHR());
//                  
//              }    
//          } );
//          
//          counter = this.getDataManager().getDraft().getFantasyTeamList().size();
//          for (int i = 0; i< this.getDataManager().getDraft().getFantasyTeamList().size();i++)
//          {
//              this.getDataManager().getDraft().getFantasyTeamList().get(i).setTotalPoint(counter);
//              counter--;
//          }
//          
//          Collections.sort(standingTeamList,new Comparator()
//          {
//
//              @Override
//              public int compare(Object o1, Object o2) 
//              {
//                  FantasyTeam obj1 = (FantasyTeam)o1;
//                  FantasyTeam obj2 = (FantasyTeam)o2;
//                  return Integer.compare(obj1.getHR(), obj2.getHR());
//                  
//              }    
//          } );
//          
//          
//          
//          
//      }
              
}
