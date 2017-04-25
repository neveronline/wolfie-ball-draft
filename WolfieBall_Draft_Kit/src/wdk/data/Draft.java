/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

/**
 *
 * @author Leon
 */
import java.util.ArrayList;
import java.util.Collections;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;





/**
 *
 * @author Leon
 */
public class Draft
{
    String draftName;
    ArrayList <Player>playerList;
    ObservableList <Player> displayList;
    ObservableList<FantasyTeam> teamList;
    ObservableList<Player> draftList;
    ArrayList<Player> originalPlayerList;
    int picknum;
    boolean fillUpAllTeam;
    
    
    public Draft()
    {
        playerList = new ArrayList();
        displayList = FXCollections.observableArrayList();
        teamList = FXCollections.observableArrayList();
        draftName = "";
        draftList = FXCollections.observableArrayList();
        originalPlayerList =  new ArrayList();
        picknum = draftList.size();
        fillUpAllTeam = false;
    }
    
    public boolean getfull()
    {
        return fillUpAllTeam;
    }
    
    public void setfull(boolean b)
    {
        fillUpAllTeam = b;
    }
    public void addToPlayerList(Player p)
    {
        playerList.add(p);
    }
    
    public void removeFromPlayerList(Player p)
    {
        playerList.remove(p);
    }
    
    public ArrayList <Player> getPlayerList()
    {
        return playerList;
    }
    
            
    public ArrayList <Player> getOriginalList()
    {
        return originalPlayerList;
    }
    public void addToDisplayList(Player p)
    {
        displayList.add(p);
    }
    
    public void removeFromDisplayList(Player p)
    {
        displayList.remove(p);
    }
    
    public void addToDraftList(Player p)
    {
        updatePickNum();
        p.setPickNum(picknum);
        draftList.add(p);
        picknum++;
    }
    
    public void removeFromDraftList(Player p)
    {
        
        draftList.remove(p);
        ObservableList<Player> temp = FXCollections.observableArrayList();
        temp = draftList;
        draftList = FXCollections.observableArrayList(); 
        for(int i =0; i<temp.size();i++)
        {
            addToDraftList(temp.get(i));
        }
        
        
        
    }
    
    public ObservableList <Player> getDraftList()
    {
        return draftList;
    }
    
    public ObservableList <Player> getDisplayList()
    {
        return displayList;
    }
    
    public void clearPlayerList()
    {
        playerList.clear();
    }
    
    public void clearDisplayList()
    {
        displayList.clear();
    }
    public void addTeam(FantasyTeam t)
    {
        if(t.getTeamName()!=null && t.getOwner()!=null)
            teamList.add(t);
    }
    
    public void removeTeam(FantasyTeam t)
    {
        teamList.remove(t);
    }
    
    public ObservableList <FantasyTeam> getFantasyTeamList()
    {
        return teamList;
    }
    
    
    public void setDraftName(String s)
    {
        draftName = s;
    }
    
    public String getDraftName()
    {
        return draftName;
    }
    
    public void updatePickNum()
    {
        picknum = draftList.size()+1;
    }
    
    public void updateTotalPoints()
    {
        ObservableList<FantasyTeam> temp = getFantasyTeamList();
        
        
    }
}

//class compareByRBI implements java.util.Comparator
//{
//
//    @Override
//    public int compare(Object o1, Object o2) 
//    {
//        return Integer.compare(((FantasyTeam)o1).getRBI(), ((FantasyTeam)o2).getRBI());
//    }
//    
//}
//Collections.sort(listname, new comparebyr())
