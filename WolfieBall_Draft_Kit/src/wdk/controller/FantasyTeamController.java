/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.controller;
import java.io.IOException;
import java.util.ArrayList;
import static wdk.WDK_PropertyType.REMOVE_ITEM_MESSAGE;
import wdk.data.Draft;
import wdk.data.DraftDataManager;
import wdk.data.Player;
import wdk.gui.wolfie_GUI;
import wdk.gui.MessageDialog;
import wdk.gui.EditPlayerDialog;
import wdk.gui.YesNoCancelDialog;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import wdk.data.FantasyTeam;
import wdk.gui.FantasyTeamDialog;

/**
 *
 * @author Leon
 */
public class FantasyTeamController 
{
    FantasyTeamDialog teamDialog;
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    
     public FantasyTeamController(Stage initPrimaryStage, MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog) throws IOException 
     {
        teamDialog =  new FantasyTeamDialog(initPrimaryStage, initMessageDialog);
        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;
     }
     
      public void handleAddTeamRequest(wolfie_GUI gui) 
    {
        DraftDataManager ddm = gui.getDataManager();
        teamDialog.showAddFantasyTeamDialog();
        
        if (teamDialog.wasCompleteSelected()) 
        {
            // GET THE LECTURE
            FantasyTeam team = teamDialog.getFantasyTeam();
            
            // AND ADD IT AS A ROW TO THE TABLE
            
            ddm.getDraft().addTeam(team);
            
            
            
            
        }
    }
      
      public void handleEditTeamRequest(wolfie_GUI gui, FantasyTeam itemToEdit) 
      {
          teamDialog.showEditFantasyTeamDialog(itemToEdit);
          
          if (teamDialog.wasCompleteSelected()) {
            // UPDATE THE LECTURE
            FantasyTeam temp = teamDialog.getFantasyTeam();
            itemToEdit.setTeamName(temp.getTeamName());
            itemToEdit.setOwner(temp.getOwner());
            
//            Lecture si = sid.getLectureItem();
//            itemToEdit.setTopic(si.getTopic());
//            
//            itemToEdit.setSessions(si.getSessions());
        }
        else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
            // WE DO NOTHING
        }        
          
      }
      
       public void handleRemoveTeamRequest(wolfie_GUI gui, FantasyTeam itemToRemove) 
       {
           // PROMPT THE USER TO SAVE UNSAVED WORK
        yesNoCancelDialog.show(PropertiesManager.getPropertiesManager().getProperty(REMOVE_ITEM_MESSAGE));
        
        // AND NOW GET THE USER'S SELECTION
        String selection = yesNoCancelDialog.getSelection();
         if (selection.equals(YesNoCancelDialog.YES)) { 
             if(itemToRemove.getStartLineList().size()!=0)
             {
                 for(int i=0; i<itemToRemove.getStartLineList().size(); i++)
                 {
                    gui.getDataManager().getDraft().addToDisplayList(itemToRemove.getStartLineList().get(i)); 
                    gui.getDataManager().getDraft().addToPlayerList(itemToRemove.getStartLineList().get(i)); 
                 }
             }
             
             if(itemToRemove.getTaxiQuadList().size()!=0)
             {
                 for(int i=0; i<itemToRemove.getTaxiQuadList().size(); i++)
                 {
                    gui.getDataManager().getDraft().addToDisplayList(itemToRemove.getTaxiQuadList().get(i)); 
                    gui.getDataManager().getDraft().addToPlayerList(itemToRemove.getTaxiQuadList().get(i)); 
                 }
             }
            gui.getDataManager().getDraft().getFantasyTeamList().remove(itemToRemove);
            
        }
        
       }
}
