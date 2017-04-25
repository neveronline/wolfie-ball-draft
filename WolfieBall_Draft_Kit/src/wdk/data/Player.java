/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package wdk.data;

import java.text.DecimalFormat;
import java.util.Collections;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Leon
 */
public class Player implements Comparable
{
    
    boolean isHitter;
    final StringProperty lastName;
    final StringProperty firstName;
    final StringProperty team;
    final StringProperty contract;
    final IntegerProperty salary;
    final StringProperty actualPosition;
    
    final StringProperty position;
    final StringProperty birth;
    final IntegerProperty rw;
    final IntegerProperty hrsv;
    final IntegerProperty rbik;
    final FloatProperty sbera;
    final FloatProperty bawhip;
    final StringProperty notes;
    final StringProperty nation;
    final StringProperty assignTeam;
    final IntegerProperty pickNum;
    
    // int to calculate BA
    int h;
    int ab;
    
    
    // stat to calculate ERA
    int er;
    double ip;
    
    
    // stat to calculate WHIP
    int w;
    int pitcherh;
    
    
    
    
    public Player()
    {
        lastName = new SimpleStringProperty("");
        firstName = new SimpleStringProperty("");
        team = new SimpleStringProperty("");
        contract = new SimpleStringProperty("");
        salary = new SimpleIntegerProperty(1);
        position = new SimpleStringProperty();
        actualPosition = new SimpleStringProperty();
        birth = new SimpleStringProperty();
        rw = new SimpleIntegerProperty();
        hrsv = new SimpleIntegerProperty();
        rbik = new SimpleIntegerProperty();
        sbera = new SimpleFloatProperty();
        bawhip = new SimpleFloatProperty();
        notes = new SimpleStringProperty();
        nation = new SimpleStringProperty();
        assignTeam = new SimpleStringProperty();
        pickNum = new SimpleIntegerProperty();
    }
    public String getAssignTeam()
    {
        return assignTeam.get();
    }
    
    public Integer getPickNum()
    {
        return pickNum.get();
    }
    
    public String getActualPosition()
    {
        return actualPosition.get();
    }
    public String getNation()
    {
        return nation.get();
    }
    public void sethitter(boolean b)
    {
        isHitter = b;
    }
    
    public boolean getbool()
    {
        return isHitter;
    }
    
    public String getNotes()
    {
        return notes.get();
    }
    
    public String getLastName()
    {
        return lastName.get();
    }
    
    public String getFirstName()
    {
        return firstName.get();
    }
    
    public String getTeam()
    {
        return team.get();
    }
    
    public String getContract()
    {
        return contract.get();
    }
    
    public int getSalary()
    {
        return salary.get();
    }
    
    public String getPosition()
    {
        return position.get();
    }
    
    public String getBirth()
    {
        return birth.get();
    }
    
    public int getRW()
    {
        return rw.get();
    }
    
    public int getHRSV()
    {
        return hrsv.get();
    }
    
    public int getRBIK()
    {
        return rbik.get();
    }
    
    public float getSBERA()
    {
        return sbera.get();
    }
    
    public float getBAWHIP()
    {
        return bawhip.get();
    }
    
    public void setPickNum(int i)
    {
        pickNum.set(i);
    }
    
    public void setAssignTeam(String s)
    {
        assignTeam.set(s);
    }
    
    public void setActualPosition(String s)
    {
        actualPosition.set(s);
    }
    public void setNotes(String s)
    {
        notes.set(s);
    }
    public void setNation(String s)
    {
        nation.set(s);
    }
    
    public void setLastName(String s)
    {
        lastName.set(s);
    }
    
    public void setFirstName(String s)
    {
        firstName.set(s);
    }
    
    public void setTeam(String s)
    {
        team.set(s);
    }
    
    public void setContract(String s)
    {
        contract.set(s);
    }
    
    public void setSalary(int i)
    {
        salary.set(i);
    }
    
    public void setPosition(String s)
    {
        position.set(s);
    }
    
    public void setBirth(String s)
    {
        birth.set(s);
    }
    
    public void setRW(int i)
    {
        rw.set(i);
    }
    
    public void setRBIK(int i)
    {
        rbik.set(i);
    }
    
    public void setHRSV(int i)
    {
        hrsv.set(i);
    }
    
    public void setSBERA(float i)
    {
        sbera.set(i);
    }
    
    public void setBAWHIP(float f)
    {
        bawhip.set(f);
    }
    
    public IntegerProperty pickNumProperty()
    {
        return pickNum;
    }
    
    public StringProperty assignTeamProperty()
    {
        return assignTeam;
    }
    
    public StringProperty actualPositionProperty()
    {
        return actualPosition;
    }
    
    public StringProperty nationProperty()
    {
        return nation;
    }
    
    public StringProperty notesProperty()
    {
        return notes;
    }
    
    public StringProperty lastNameProperty()
    {
        return lastName;
    }
    
    public StringProperty firstNameProperty()
    {
        return firstName;
    }
    
    public StringProperty teamProperty()
    {
        return team;
    }
    
    public StringProperty contractProperty()
    {
        return contract;
    }
    
    public IntegerProperty salaryProperty()
    {
        return salary;
    }
    
    public StringProperty positionProperty()
    {
        return position;
    }
    
    public StringProperty birthProperty()
    {
        return birth;
    }
    
    public IntegerProperty rwProperty()
    {
        return rw;
    }
    
    public IntegerProperty rbikProperty()
    {
        return rbik;
    }
    
    public IntegerProperty hrsvProperty()
    {
        return hrsv;
    }
    
    public FloatProperty sberaProperty()
    {
        return sbera;
    }
    
    public FloatProperty bawhipProperty()
    {
        return bawhip;
    }
    
    public int getH()
    {
        return h;
    }
    public void setH(int i)
    {
        h=i;
    }
    public int getAB()
    {
        return ab;
    }
    public void setAB(int i)
    {
        ab=i;
    }
    
    public int getER()
    {
        return er;
    }
    public void setER(int i)
    {
        er=i;
    }
    public double getIP()
    {
        return ip;
    }
    public void setIP(double i)
    {
        ip=i;
    }
    
    public int getW()
    {
        return w;
    }
    public void setW(int i)
    {
        w=i;
    }
    public int getPitcherH()
    {
        return pitcherh;
    }
    public void setPitcherH(int i)
    {
        pitcherh=i;
    }
    
    public float calculateERA(int i, float f)
    {
       if(f!=0)
        {
            DecimalFormat myFormatter = new DecimalFormat("0.00");
            float temp =(float)(i*9)/f;
            float output = Float.parseFloat(myFormatter.format(temp));
            return output;
        }
        else
            return 0;
    }
    
    // cant find walk stat, if find need add to the method
    public float calculateWHIP(int w,int h, float ip)
    {
        if(ip!=0)
        {
            DecimalFormat myFormatter = new DecimalFormat("0.00");
            float temp = (float)(w+h)/ip ;
            float output = Float.parseFloat(myFormatter.format(temp));
            return output;
        }
        else
            return 0;
    }
    
    public float calculateBA(int i, int k)
    {
        if(k!=0)
        {
            DecimalFormat myFormatter = new DecimalFormat("0.000");
            float temp = (float)i/k ;
            float output = Float.parseFloat(myFormatter.format(temp));
            return output;
        }
        else
            return 0;
    }
    
    public String toString()
    {
        return lastName.get()+" "+firstName.get();
    }
    
    @Override
    public int compareTo(Object o)
    {
        Player otherPlayer = (Player)o;
        if(this.getLastName().equalsIgnoreCase(otherPlayer.getLastName()))
        {
            return this.getFirstName().compareTo(otherPlayer.getFirstName());
        }
        else
        {
            return this.getLastName().compareTo(otherPlayer.getLastName());
        }
    }
    
}
