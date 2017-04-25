/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import static wdk.WDK_StartupConstants.JSON_FILE_PATH_HITTER;
import static wdk.WDK_StartupConstants.JSON_FILE_PATH_PITCHER;
import static wdk.WDK_StartupConstants.PATH_DRAFT;
import wdk.data.Draft;
import wdk.data.FantasyTeam;
import wdk.data.Player;

/**
 *
 * @author Leon
 */
public class JsonDraftFileManager implements DraftFileManager 
{
     // JSON FILE READING AND WRITING CONSTANTS
    String JSON_TEAM = "TEAM";
    String JSON_LASTNAME = "LAST_NAME";
    String JSON_FIRSTNAME = "FIRST_NAME";
    String JSON_NOTES = "NOTES";
    String JSON_YEAR_OF_BIRTH = "YEAR_OF_BIRTH";
    String JSON_NATION ="NATION_OF_BIRTH";
    
    //HITTER STAT
    String JSON_QP = "QP";
    String JSON_AB = "AB";
    String JSON_R = "R";
    String JSON_H = "H";
    String JSON_HR = "HR";
    String JSON_RBI = "RBI";
    String JSON_SB = "SB";
   
    
    //PITCHER STAT
    String JSON_IP = "IP";
    String JSON_ER = "ER";
    String JSON_W = "W";
    String JSON_SV = "SV";
    
    String JSON_BB = "BB";
    String JSON_K = "K";
    String JSON_HITTER = "Hitters";
    String JSON_PITCHER ="Pitchers";
    
    String JSON_EXT = ".json";
    String SLASH = "/";
    /**
     * Loads the courseToLoad argument using the data found in the json file.
     * 
     * @param courseToLoad Course to load.
     * @param jsonFilePath File containing the data to load.
     * 
     * @throws IOException Thrown when IO fails.
     */
    
   
    
   
     private JsonObject loadJSONFile(String jsonFilePath) throws IOException 
     {
        InputStream is = new FileInputStream(jsonFilePath);
        JsonReader jsonReader = Json.createReader(is);
        JsonObject json = jsonReader.readObject();
        jsonReader.close();
        is.close();
        return json;
    }
     


    

    @Override
    public void loadDraft(Draft draftToLoad) throws IOException {
        JsonObject json = loadJSONFile(JSON_FILE_PATH_HITTER);
        draftToLoad.getPlayerList().clear();
        draftToLoad.getDisplayList().clear();
        JsonArray jsonHitterArray = json.getJsonArray(JSON_HITTER);
        
        for (int i = 0; i < jsonHitterArray.size(); i++) {
            JsonObject jso = jsonHitterArray.getJsonObject(i);
            
            Player p = new Player();
            p.sethitter(true);
        p.setLastName(jso.getString(JSON_LASTNAME));
        p.setFirstName(jso.getString(JSON_FIRSTNAME));
        p.setTeam(jso.getString(JSON_TEAM));
        p.setPosition(jso.getString(JSON_QP));
        p.setBirth(jso.getString(JSON_YEAR_OF_BIRTH));
        p.setRW(Integer.parseInt(jso.getString(JSON_R)));
        p.setHRSV(Integer.parseInt(jso.getString(JSON_HR)));
        p.setRBIK(Integer.parseInt(jso.getString(JSON_RBI)));
        p.setSBERA(Integer.parseInt(jso.getString(JSON_SB)));
        
        p.setBAWHIP(p.calculateBA(Integer.parseInt(jso.getString(JSON_H)), Integer.parseInt(jso.getString(JSON_AB))));
        p.setNotes(jso.getString(JSON_NOTES));    
        p.setNation(jso.getString(JSON_NATION));
        p.setAB(Integer.parseInt(jso.getString(JSON_AB)));
        p.setH(Integer.parseInt(jso.getString(JSON_H)));
            // ADD IT TO THE COURSE
            draftToLoad.addToPlayerList(p);
            
            draftToLoad.addToDisplayList(p);
            draftToLoad.getOriginalList().add(p);
            
            
        }
        
        
        JsonObject json1 = loadJSONFile(JSON_FILE_PATH_PITCHER);
         JsonArray jsonPitcherArray = json1.getJsonArray(JSON_PITCHER);
         for (int i = 0; i < jsonPitcherArray.size(); i++) 
         {
            JsonObject jso = jsonPitcherArray.getJsonObject(i);
            Player p = new Player();
            p.sethitter(false);
            p.setLastName(jso.getString(JSON_LASTNAME));
            p.setFirstName(jso.getString(JSON_FIRSTNAME));
            p.setTeam(jso.getString(JSON_TEAM));
            p.setPosition("P");
            p.setBirth(jso.getString(JSON_YEAR_OF_BIRTH));
            p.setRW(Integer.parseInt(jso.getString(JSON_W)));
            p.setRBIK(Integer.parseInt(jso.getString(JSON_K)));
            p.setHRSV(Integer.parseInt(jso.getString(JSON_SV)));
            p.setSBERA(p.calculateERA(Integer.parseInt(jso.getString(JSON_ER)), Float.parseFloat(jso.getString(JSON_IP))));
            p.setBAWHIP(p.calculateWHIP(Integer.parseInt(jso.getString(JSON_H)),Integer.parseInt(jso.getString(JSON_W)), Float.parseFloat(jso.getString(JSON_IP))));
            p.setNotes(jso.getString(JSON_NOTES));
            p.setNation(jso.getString(JSON_NATION));
            
            p.setER(Integer.parseInt(jso.getString(JSON_ER)));
            p.setIP(Float.parseFloat(jso.getString(JSON_IP)));
            p.setPitcherH(Integer.parseInt(jso.getString(JSON_H)));
            p.setW(Integer.parseInt(jso.getString(JSON_W)));
            
            draftToLoad.addToPlayerList(p);
            
            draftToLoad.addToDisplayList(p);
            draftToLoad.getOriginalList().add(p);
         }
         
        
    }
    
     public void saveDraft(Draft draftToSave) throws IOException 
     {
        // BUILD THE FILE PATH
        
        String jsonFilePath = PATH_DRAFT + draftToSave.getDraftName() +JSON_EXT;
        OutputStream os = new FileOutputStream(jsonFilePath);
        JsonWriter jsonWriter = Json.createWriter(os);  
     }
     
     
}
