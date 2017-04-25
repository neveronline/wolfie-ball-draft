/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package wdk.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import javafx.application.Platform;
import javafx.concurrent.Task;
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
import wdk.gui.AddPlayerDialog;

/**
 *
 * @author Leon
 */
public class PlayerEditController
{
    EditPlayerDialog sid;
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    AddPlayerDialog adp;
    boolean stop;
    
    
    public PlayerEditController(Stage initPrimaryStage, MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog,wolfie_GUI gui) throws IOException {
        sid = new EditPlayerDialog(initPrimaryStage, initMessageDialog,gui);
        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;
        adp = new AddPlayerDialog(initPrimaryStage, initMessageDialog);
        stop = true;
        
    }
    
    public void handleAddPlayerRequest(wolfie_GUI gui)
    {
        DraftDataManager ddm = gui.getDataManager();
        ArrayList<Player> playerList = ddm.getDraft().getPlayerList();
        adp.showAddPlayerDialog();
        
        // DID THE USER CONFIRM?
        if (adp.wasCompleteSelected())
        {
            // GET THE LECTURE
            Player si = adp.getPlayer();
            
            // AND ADD IT AS A ROW TO THE TABLE
            playerList.add(si);
            ddm.getDraft().getDisplayList().add(si);
            
            
            
        }
        else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
            // WE DO NOTHING
        }
    }
    public void handleSwitchTeamRequest(wolfie_GUI gui, Player itemToEdit)
    {
        
        sid.showEditPlayerDialog(gui,itemToEdit);
        if (sid.wasCompleteSelected())
        {
            Player si = sid.getPlayer();
            itemToEdit.setContract(si.getContract());
            itemToEdit.setSalary(si.getSalary());
            itemToEdit.setActualPosition(si.getActualPosition());
            
            for(int i = 0; i<gui.getDataManager().getDraft().getDraftList().size();i++)
            {
                if(itemToEdit.toString().equals(gui.getDataManager().getDraft().getDraftList().get(i).toString()))
                {
                    // in the draft list  but contract change to s1
                    if(itemToEdit.getContract().equals("S1"))
                    {
                        gui.getDataManager().getDraft().removeFromDraftList(itemToEdit);
                        break;
                    }
                }
            }
            if(itemToEdit.getContract().equals("S2") && !gui.getDataManager().getDraft().getDraftList().contains(itemToEdit))
            {
                gui.getDataManager().getDraft().addToDraftList(itemToEdit);
            }
            
            FantasyTeam team = sid.getTeam();
            team.addToStartLine(itemToEdit);
            FantasyTeam temp = new FantasyTeam();
            if(team.getStartLineList().contains(itemToEdit))
            {
                for(int i =0; i<gui.getDataManager().getDraft().getFantasyTeamList().size();i++)
                {
                    if( gui.getDataManager().getDraft().getFantasyTeamList().get(i).getTeamName().equalsIgnoreCase(itemToEdit.getAssignTeam()))
                    {
                        temp = gui.getDataManager().getDraft().getFantasyTeamList().get(i);
                    }
                }
                if(temp.getTeamName().equalsIgnoreCase(team.getTeamName()))
                {
                    if(sid.isPositionChanged())
                    {
                        temp.removeFromStartLine(itemToEdit);
                    }
                    else
                    {
                        //do not remove;
                    }
                }
                
                else
                {
                temp.removeFromStartLine(itemToEdit);
                }
            }
            
        }
    }
    
    
    
    public void handleEditPlayerRequest(wolfie_GUI gui, Player itemToEdit) {
        
        
        sid.showEditPlayerDialog(gui,itemToEdit);
        
        // DID THE USER CONFIRM?
        if (sid.wasCompleteSelected()) {
            // UPDATE THE LECTURE
            Player si = sid.getPlayer();
            itemToEdit.setContract(si.getContract());
            itemToEdit.setSalary(si.getSalary());
            itemToEdit.setActualPosition(si.getActualPosition());
            
            
            FantasyTeam team = sid.getTeam();
            itemToEdit.setAssignTeam(team.getTeamName());
            
            if(itemToEdit.getContract()=="S2")
            {
                gui.getDataManager().getDraft().getDraftList().add(itemToEdit);
                int i = gui.getDataManager().getDraft().getDraftList().size();
                
                itemToEdit.setPickNum(i);
            }
            
            
            if(itemToEdit.getContract()!="X")
            {
            
            team.addToStartLine(itemToEdit);
            
            if(team.getStartLineList().contains(itemToEdit))
            {
                gui.getDataManager().getDraft().removeFromDisplayList(itemToEdit);
                gui.getDataManager().getDraft().removeFromPlayerList(itemToEdit);
                gui.getPlayerTableList().remove(itemToEdit);
            }
            }
//            Lecture si = sid.getLectureItem();
//            itemToEdit.setTopic(si.getTopic());
//
//            itemToEdit.setSessions(si.getSessions());
        }
        if(sid.wasAddToTaxiSelected())
        {
            Player si = sid.getPlayer();
            itemToEdit.setContract(si.getContract());
            itemToEdit.setSalary(si.getSalary());
            FantasyTeam team = sid.getTeam();
            itemToEdit.setAssignTeam(team.getTeamName());
            
            if(itemToEdit.getContract()=="X" )
            {
                if(team.getPlayerNeed()==0 && team.getTaxiPlayerNeed()>0)
                {
                    gui.getDataManager().getDraft().getDraftList().add(itemToEdit);
                    int i = gui.getDataManager().getDraft().getDraftList().size();
                    itemToEdit.setPickNum(i);
                    
                    team.addToTaxiQuad(itemToEdit);
                    
                    if(team.getTaxiQuadList().contains(itemToEdit))
                    {
                        gui.getDataManager().getDraft().removeFromDisplayList(itemToEdit);
                        gui.getDataManager().getDraft().removeFromPlayerList(itemToEdit);
                        gui.getPlayerTableList().remove(itemToEdit);
                    }
                }
            }
        }
        else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
            // WE DO NOTHING
        }
        
    }
    
    public void handleRemovePlayerRequest(wolfie_GUI gui, Player itemToRemove) {
        // PROMPT THE USER TO SAVE UNSAVED WORK
        yesNoCancelDialog.show(PropertiesManager.getPropertiesManager().getProperty(REMOVE_ITEM_MESSAGE));
        
        // AND NOW GET THE USER'S SELECTION
        String selection = yesNoCancelDialog.getSelection();
        
        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (selection.equals(YesNoCancelDialog.YES)) {
            
            gui.getDataManager().getDraft().getDisplayList().remove(itemToRemove);
            gui.getDataManager().getDraft().getPlayerList().remove(itemToRemove);
            gui.getPlayerTableList().remove(itemToRemove);
            
            
        }
    }
    
    
    
    
    
    public void handleRemoveTeamMemberRequest(wolfie_GUI gui, Player itemToRemove) {
        // PROMPT THE USER TO SAVE UNSAVED WORK
        yesNoCancelDialog.show(PropertiesManager.getPropertiesManager().getProperty(REMOVE_ITEM_MESSAGE));
        
        // AND NOW GET THE USER'S SELECTION
        String selection = yesNoCancelDialog.getSelection();
        
        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (selection.equals(YesNoCancelDialog.YES)) {
            itemToRemove.setAssignTeam(null);
            gui.getDataManager().getDraft().addToDisplayList(itemToRemove);
            gui.getDataManager().getDraft().addToPlayerList(itemToRemove);
            gui.getPlayerTableList().add(itemToRemove);
            FantasyTeam team = (FantasyTeam)gui.getComboBoxTeam().getSelectionModel().getSelectedItem();
            team.removeFromStartLine(itemToRemove);
        }
    }
    
    
    
    
    public void handleDraftRandomPlayerRequest(wolfie_GUI gui)
    {
        if(gui.getDataManager().getDraft().getfull() == false)
        {
            if(gui.getDataManager().getDraft().getFantasyTeamList().size()!= 0)
            {
                FantasyTeam team = new FantasyTeam();
                int teamCounter = 0;
                team = gui.getDataManager().getDraft().getFantasyTeamList().get(teamCounter);
                if(team.getPlayerNeed() == 0)
                {
                    team.setFull(true);
                }
                while(team.getStartLineList().size()==23)
                {
                    if(teamCounter<gui.getDataManager().getDraft().getFantasyTeamList().size()-1)
                    {
                        teamCounter++;
                        team = gui.getDataManager().getDraft().getFantasyTeamList().get(teamCounter);
                    }
                    else
                    {
                        // this is the last team
                        break;
                    }
                }
                // all start line fill up
                
                if(team.getPlayerNeed() == 0 && team == gui.getDataManager().getDraft().getFantasyTeamList().get(gui.getDataManager().getDraft().getFantasyTeamList().size()-1))
                {
                    int counter =0;
                    team =  gui.getDataManager().getDraft().getFantasyTeamList().get(counter);
                    
                    while(team.getTaxiPlayerNeed()== 0)
                    {
                        if(counter<gui.getDataManager().getDraft().getFantasyTeamList().size()-1)
                        {
                            counter++;
                            team = gui.getDataManager().getDraft().getFantasyTeamList().get(counter);
                        }
                        else
                        {
                            // this is the last team
                            
                            break;
                        }
                        
                    }
                    
                    if(team.getTaxiPlayerNeed() == 0 && team == gui.getDataManager().getDraft().getFantasyTeamList().get(gui.getDataManager().getDraft().getFantasyTeamList().size()-1))
                    {
                        // all team are full!!
                        gui.getDataManager().getDraft().setfull(true);
                        
                        
                    }
                    
                    else
                    {
                        assignRandomPlayerToTeamTaxiQuad(gui,team);
                    }
                    
                    
                }
                
                // still need to fill start line
                else
                {
                    assignRandomPlayerToTeamStartLine(gui,team);
                    
                }
            }
            
            else
            {
                System.out.println("You need to create a team first!");
            }
        }
        
        else
        {
            System.out.println("this draft can't draw any player");
        }
    }
    
    public void assignRandomPlayerToTeamStartLine(wolfie_GUI gui,FantasyTeam fantasyTeam)
    {
        int size = gui.getDataManager().getDraft().getPlayerList().size();
        Random num = new Random();
        int index = num.nextInt(size);
        
        Player randomPlayer = gui.getDataManager().getDraft().getDisplayList().get(index);
        String playerPosition =randomPlayer.getPosition();
        if(playerPosition.contains("1B")||playerPosition.contains("3B"))
        {
            playerPosition = playerPosition + "_CI";
        }
        
        if(playerPosition.contains("2B")||playerPosition.contains("SS"))
        {
            playerPosition = playerPosition + "_MI";
        }
        
        if(!playerPosition.contains("P"))
        {
            playerPosition = playerPosition + "_U";
        }
        
        String []position = playerPosition.split("_");
        for(int i =0;i<position.length;i++)
        {
            if(fantasyTeam.getHashMap().get(position[i])>0)
            {
                randomPlayer.setActualPosition(position[i]);
                break;
            }
        }
        
        if(randomPlayer.getActualPosition()!=null)
        {
            randomPlayer.setAssignTeam(fantasyTeam.getTeamName());
            randomPlayer.setSalary(1);
            randomPlayer.setContract("S2");
            
            fantasyTeam.addToStartLine(randomPlayer);
            if(randomPlayer.getContract().equalsIgnoreCase("S2"))
            {
                gui.getDataManager().getDraft().addToDraftList(randomPlayer);
            }
            if(fantasyTeam.getStartLineList().contains(randomPlayer))
            {
                gui.getDataManager().getDraft().removeFromPlayerList(randomPlayer);
                gui.getDataManager().getDraft().removeFromDisplayList(randomPlayer);
            }
            
            
            
        }
        
        //no position avaliable in this team for this player
        else
        {
            assignRandomPlayerToTeamStartLine(gui,fantasyTeam);
            
        }
        
        
    }
    
    public void assignRandomPlayerToTeamTaxiQuad(wolfie_GUI gui,FantasyTeam fantasyTeam)
    {
        int size = gui.getDataManager().getDraft().getPlayerList().size();
        Random num = new Random();
        int index = num.nextInt(size);
        
        Player randomPlayer = gui.getDataManager().getDraft().getPlayerList().get(index);
        
        if(fantasyTeam.getTaxiPlayerNeed()>0)
        {
            randomPlayer.setAssignTeam(fantasyTeam.getTeamName());
            randomPlayer.setContract("X");
            randomPlayer.setSalary(1);
            fantasyTeam.addToTaxiQuad(randomPlayer);
            
            if(randomPlayer.getContract().equalsIgnoreCase("X"))
            {
                gui.getDataManager().getDraft().addToDraftList(randomPlayer);
            }
            if(fantasyTeam.getTaxiQuadList().contains(randomPlayer))
            {
                gui.getDataManager().getDraft().removeFromPlayerList(randomPlayer);
                gui.getDataManager().getDraft().removeFromDisplayList(randomPlayer);
            }
        }
        
        else
        {
            //team is full!!!
        }
    }
    
    
    
    public void handleAutoDraftRequest(wolfie_GUI gui)
    {
        stop = false;
        
        Task<Void> task = new Task<Void>() {
            Draft draft = gui.getDataManager().getDraft();
            
            @Override
            protected Void call() throws Exception {              
                while(!draft.getfull() && stop == false) {
                    
                    
                    
                    // THIS WILL BE DONE ASYNCHRONOUSLY VIA MULTITHREADING
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if(gui.getDataManager().getDraft().getFantasyTeamList().size()!=0)
                            handleDraftRandomPlayerRequest(gui);
                        }
                    });
                    
                    // SLEEP EACH FRAME
                    try 
                    {
                        Thread.sleep(400);
                    } catch (InterruptedException ie) 
                    {
                        ie.printStackTrace();
                    }

                }
                return null;
            }
        };
        
        Thread thread = new Thread(task);
        
        thread.start();
    }
    
    public void handlePauseRequest()
    {
        stop = true;
    }
    
    
}
