/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package wdk.data;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wdk.gui.wolfie_GUI;

/**
 *
 * @author Leon
 */
public class FantasyTeam
{
    String teamName;
    HashMap < String, Integer> startLine;
    ObservableList <Player> startLineList;
    ObservableList <Player> taxiQuadList;
    String owner;
    final IntegerProperty  moneyLeft;
    final IntegerProperty  playerNeed;
    final FloatProperty moneyPerPerson;
    final IntegerProperty r;
    final IntegerProperty hr;
    final IntegerProperty rbi;
    final IntegerProperty sb;
    final FloatProperty ba;
    final IntegerProperty w;
    final IntegerProperty sv;
    final IntegerProperty k;
    final FloatProperty era;
    final FloatProperty whip;
    final IntegerProperty totalPoint;
    int taxiPlayerNeed;
    boolean startLineIsFull;
    
    // int to calculate BA
    int h;
    int ab;
    
    
    // stat to calculate ERA
    int er;
    double ip;
    
    
    // stat to calculate WHIP
    int pitcherw;
    int pitcherh;
    
    
    
    public FantasyTeam()
    {
        teamName = null;
        owner = null;
        startLine = new HashMap();
        startLineList = FXCollections.observableArrayList();
        taxiQuadList = FXCollections.observableArrayList();
        startLine.put("C", 2);
        startLine.put("1B", 1);
        startLine.put("3B", 1);
        startLine.put("CI", 1);
        startLine.put("2B", 1);
        startLine.put("SS", 1);
        startLine.put("MI", 1);
        startLine.put("OF", 5);
        startLine.put("U", 1);
        startLine.put("P", 9);
        moneyLeft = new SimpleIntegerProperty(260);
        playerNeed  = new SimpleIntegerProperty(23);
        moneyPerPerson = new SimpleFloatProperty();
        r  = new SimpleIntegerProperty();
        hr = new SimpleIntegerProperty();
        rbi = new SimpleIntegerProperty();
        sb = new SimpleIntegerProperty();
        ba = new SimpleFloatProperty();
        w = new SimpleIntegerProperty();
        sv = new SimpleIntegerProperty();
        k = new SimpleIntegerProperty();
        era = new SimpleFloatProperty();
        whip = new SimpleFloatProperty();
        totalPoint= new SimpleIntegerProperty();
        taxiPlayerNeed = 8;
        startLineIsFull =false;
        
    }
    
    public boolean isFull()
    {
        return startLineIsFull;
    }
    
    public void setFull(boolean b)
    {
        startLineIsFull = b;
    }
    public void setTaxiPlayerNeed(int i)
    {
        taxiPlayerNeed =i;
    }
    public void setMoneyLeft(int i)
    {
        moneyLeft.set(i);
    }
    public void setPlayerNeed(int i)
    {
        playerNeed.set(i);
    }
    
    public void setMoneyPerPerson(float f)
    {
        moneyPerPerson.set(f);
    }
    public void setTeamName(String s )
    {
        teamName =s;
    }
    
    public String toString()
    {
        return teamName;
        
    }
    
    public int getTaxiPlayerNeed()
    {
        return taxiPlayerNeed;
    }
    
    public int getMoneyLeft()
    {
        return moneyLeft.get();
    }
    
    public int getPlayerNeed()
    {
        return playerNeed.get();
    }
    public float getMoneyPerPerson()
    {
        return moneyPerPerson.get();
    }
    public String getTeamName()
    {
        return teamName;
    }
    
    public void setOwner(String s)
    {
        owner =s;
    }
    
    public String getOwner()
    {
        return owner;
    }
    
    public HashMap < String, Integer> getHashMap()
    {
        return startLine;
    }
    
    public ObservableList <Player> getStartLineList()
    {
        return startLineList;
    }
    
    public ObservableList <Player> getTaxiQuadList()
    {
        return taxiQuadList;
    }
    public void addToTaxiQuad(Player p)
    {
        taxiQuadList.add(p);
        taxiPlayerNeed--;
    }
    public void removeFromTaxiQuad(Player p)
    {
        taxiQuadList.remove(p);
        taxiPlayerNeed++;
    }
    
    public void removeFromStartLine(Player p)
    {
        String position = p.getActualPosition();
        startLine.put(position, startLine.get(position)+1);
        
        setMoneyLeft(getMoneyLeft()+p.getSalary());
        setPlayerNeed(getPlayerNeed()+1);
        setMoneyPerPerson(calculateMoneyPerPerson());
        
        startLineList.remove(p);
        
        
    }
    public void addToStartLine(Player p)
    {
        if(p.getActualPosition()!=null && p.getContract()!= null)
        {
            String position = p.getActualPosition();
            if(startLine.get(position)>0)
            {
                startLine.put(position, startLine.get(position)-1);
                
//       startLineList.add(p);
                if(startLineList.size()>0)
                {
                    String firstPlayerPos = startLineList.get(0).getActualPosition();
                    int index = 0;
                    while(index<startLineList.size())
                    {
                        firstPlayerPos = startLineList.get(index).getActualPosition();
                        if(comparePos(position)<comparePos(firstPlayerPos))
                        {
                            break;
                        }
                        index++;
                    }
                    startLineList.add(index,p);
                }
                else
                {
                    startLineList.add(p);
                    
                }
            }
            setMoneyLeft(getMoneyLeft()-p.getSalary());
            setPlayerNeed(getPlayerNeed()-1);
            setMoneyPerPerson(calculateMoneyPerPerson());
            updateTeamStat(p);
            
        }
        else
        {
            //do nothing
        }
        
        
    }
    
    public int comparePos(String pos)
    {
        String [] temp = {"C","1B","CI","3B","2B","MI","SS","OF","U","P"};
        for(int i= 0; i<temp.length;i++)
        {
            if(pos.equals(temp[i]))
            {
                return i;
            }
        }
        return -1;
    }
    
    public float calculateMoneyPerPerson()
    {
        float person = getPlayerNeed();
        float moneySpend = getMoneyLeft();
        
        return moneySpend/person;
        
    }
    public void setR(int f)
    {
        r.set(f);
    }
    
    public void setHR(int f)
    {
        hr.set(f);
    }
    
    public void setRBI(int f)
    {
        rbi.set(f);
    }
    
    public void setSB(int f)
    {
        sb.set(f);
    }
    
    public void setBA(float f)
    {
        ba.set(f);
    }
    
    public void setW(int f)
    {
        w.set(f);
    }
    
    public void setSV(int f)
    {
        sv.set(f);
    }
    
    public void setK(int f)
    {
        k.set(f);
    }
    
    public void setERA(float f)
    {
        era.set(f);
    }
    
    public void setWHIP(float f)
    {
        whip.set(f);
    }
    
    public void setTotalPoint(int i)
    {
        totalPoint.set(i);
    }
    
    public int getR()
    {
        return r.get();
    }
    
    public int getHR()
    {
        return hr.get();
    }
    
    public int getRBI()
    {
        return rbi.get();
    }
    
    public int getSB()
    {
        return sb.get();
    }
    
    public float getBA()
    {
        return ba.get();
    }
    
    public int getW()
    {
        return w.get();
    }
    
    public int getSV()
    {
        return sv.get();
    }
    
    public int getK()
    {
        return k.get();
    }
    
    public float getERA()
    {
        return era.get();
    }
    public float getWHIP()
    {
        return whip.get();
    }
    
    public int getTotalPoint()
    {
        return totalPoint.get();
    }
    
    public IntegerProperty moneyLeftProperty()
    {
        return moneyLeft;
    }
    
    public IntegerProperty playerNeedProperty()
    {
        return playerNeed;
    }
    public FloatProperty moneyPerPersonProperty()
    {
        return moneyPerPerson;
    }
    
    public IntegerProperty rProperty()
    {
        return r;
    }
    public IntegerProperty hrProperty()
    {
        return hr;
    }
    public IntegerProperty rbiProperty()
    {
        return rbi;
    }
    public IntegerProperty sbProperty()
    {
        return sb;
    }
    public FloatProperty baProperty()
    {
        return ba;
    }
    public IntegerProperty wProperty()
    {
        return w;
    }
    public IntegerProperty svProperty()
    {
        return sv;
    }
    
    public IntegerProperty kProperty()
    {
        return k;
    }
    
    public FloatProperty eraProperty()
    {
        return era;
    }
    
    public FloatProperty whipProperty()
    {
        return whip;
    }
    
    public IntegerProperty totalPointProperty()
    {
        return totalPoint;
    }
    
    public void updateTeamStat(Player p )
    {
        int pitcherCounter = 0;
        int hitterCounter = 0;
        w.set(0);
        sv.set(0);
        k.set(0);
        era.set(0);
        whip.set(0);
        
        r.set(0);
        hr.set(0);
        rbi.set(0);
        sb.set(0);
        ba.set(0);
        
        
        h =0;
        ab =0;
        er =0;
        ip =0;
        pitcherw =0;
        pitcherh =0;
        for (int i =0; i< startLineList.size();i++)
        {
            // if it is a pitcher, add stat
            if(startLineList.get(i).getPosition().equalsIgnoreCase("P"))
            {
                w.set(w.get()+startLineList.get(i).getRW());
                sv.set(sv.get()+startLineList.get(i).getHRSV());
                k.set(k.get()+startLineList.get(i).getRBIK());
                er += startLineList.get(i).getER();
                ip += startLineList.get(i).getIP();
                pitcherw += startLineList.get(i).getW();
                pitcherh += startLineList.get(i).getPitcherH();
                pitcherCounter++;
            }
            if(!startLineList.get(i).getPosition().equalsIgnoreCase("P"))
            {
                r.set(r.get()+startLineList.get(i).getRW());
                hr.set(hr.get()+startLineList.get(i).getHRSV());
                rbi.set(rbi.get()+startLineList.get(i).getRBIK());
                sb.set((int) (sb.get()+startLineList.get(i).getSBERA()));
                h += startLineList.get(i).getH();
                ab +=startLineList.get(i).getAB();
                hitterCounter++;
            }
        }
//        if(p.getPosition().equalsIgnoreCase("P"))
//        {
//            W.set(W.get()+p.getRW());
//            SV.set(SV.get()+p.getHRSV());
//            K.set(K.get()+p.getRBIK());
//            ERA.set(ERA.get()+p.getSBERA());
//            WHIP.set(WHIP.get()+p.getBAWHIP());
//            pitcherCounter++;
//        }
//
//        if(!p.getPosition().equalsIgnoreCase("P"))
//        {
//            R.set(R.get()+p.getRW());
//            HR.set(HR.get()+p.getHRSV());
//            RBI.set(RBI.get()+p.getRBIK());
//            SB.set(SB.get()+p.getSBERA());
//            BA.set(BA.get()+p.getBAWHIP());
//            hitterCounter++;
//        }
        // pitcher stat
        
        
        
        era.set(calculateERA(er, (float) ip));
        whip.set(calculateWHIP(pitcherw,pitcherh, (float) ip));
        
        //hitter stat
        
        
        ba.set(calculateBA(h, ab));
        
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
    
    
    
}



