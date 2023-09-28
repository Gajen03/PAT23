/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import DBBackend.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class PlayerManagerBE {
    
    // adds player to a player list with inputs give
    public static void addPlayer(String name,String surname,String DOB,String kitnumber, String position) throws SQLException, ClassNotFoundException{
        DB database = new DB();
        database.update("INSERT INTO Players (Players.name,surname,age,kitNumber, position) VALUES ('"+name+"', '"+surname+"', '"+DOB+"','"+kitnumber+"','"+position+"');");
    }
    
    // deletes player selected in the j list
    public static void removePlayer(String playerID) throws ClassNotFoundException, SQLException{
        DB database = new DB();
        database.update("DELETE FROM Players WHERE Players.PlayerID = "+playerID);
//        database.update("DELETE FROM Stats WHERE Stats.PlayerID = "+playerID);
//        database.update("DELETE FROM TeamPlayer WHERE TeamPlayer.idPlayer = "+playerID);
        
    }
    
    // gets all players from all teams
    public static String[] getAllPlayers() throws SQLException, ClassNotFoundException {
        DB database = new DB();
        
        ResultSet countQueryTeamPlayer = database.query("SELECT COUNT(*) FROM Players;");
        countQueryTeamPlayer.next();
        int numRows = countQueryTeamPlayer.getInt(1);


        ResultSet dbData = database.query("SELECT Players.Name , Players.Surname  FROM Players;");
        String[] players = new String[numRows];
        int count = 0;
        
        while (dbData.next()) {
            players[count] = dbData.getString("Name") + " " + dbData.getString("Surname");
            
            count++;
        }
        return players;
    }
    
    // gets ID of player selected(used in many other function )
    public static String getPlayerID(String name,String surname) throws ClassNotFoundException, SQLException{
       DB database = new DB();
       
       ResultSet getPlayerID = database.query("SELECT Players.PlayerID FROM Players WHERE Players.Name = '"+name+"' AND Players.Surname = '"+surname+"' ;");
       String playerID = DB.toString(getPlayerID).replace("#","");
       if(playerID.equals("")){
           return "0";
       }else{
       return playerID;
           
       }
       
       
       
    }
    
    // gets players stats that are needed
    public static String getPlayerGoals(String playerID) throws ClassNotFoundException, SQLException{
        DB databse = new DB();
        ResultSet getGoals = databse.query("SELECT ROUND(SUM(Goals)) FROM Stats WHERE Stats.PlayerID = '"+playerID+"';");
      
        if(getGoals.equals("")){
            return "0";
        }else{
            return DB.toString(getGoals).replace("#", "");
        }
     
        
    }
    
    public static String getPlayerAssists(String playerID) throws ClassNotFoundException, SQLException{
        DB databse = new DB();
        ResultSet getAssists = databse.query("SELECT ROUND(SUM(Assists)) FROM Stats WHERE Stats.PlayerID = '"+playerID+"';");
      
        if(getAssists.equals("")){
            return "0";
        }else{
        return DB.toString(getAssists).replace("#", "");
        }
    }
    
    public static String getPosition(String playerID) throws ClassNotFoundException, SQLException{
        DB databse = new DB();
        ResultSet getPos = databse.query("SELECT Position FROM Players WHERE Players.PlayerID = '"+playerID+"'");
        
            
        return DB.toString(getPos);
        
        
    }
    public static String getKitNum(String playerID) throws ClassNotFoundException, SQLException{
        DB databse = new DB();
        ResultSet getPos = databse.query("SELECT kitnumber FROM Players WHERE Players.PlayerID = '"+playerID+"'");
        
            
        return DB.toString(getPos);
        
        
    }
    public static String getAge(String playerID) throws ClassNotFoundException, SQLException{
        DB databse = new DB();
        ResultSet getPos = databse.query("SELECT age FROM Players WHERE Players.PlayerID = '"+playerID+"'");
        
            
        return DB.toString(getPos);
        
        
    }
    
    // calc players ocr based of goals and asssistes,(maybe what team they in)
 public static String calcOVR(String playerID) throws ClassNotFoundException, SQLException{
        
       DB databse = new DB();
      
        ResultSet getOvr = databse.query("SELECT (SUM(Goals)+SUM(Assists)+70) FROM Stats WHERE Stats.PlayerID = '" + playerID + "';");

        String ovr = DB.toString(getOvr).replace("\\n", "").replace("#", "");
        if (ovr.contains("null")) {

            return "70";
        }else{
            return ovr;
        }


        
    }

// gets all players that are assocciated with a certain team selected
    public static String[] getTeamPlayers(String teamID) throws ClassNotFoundException, SQLException{
        DB database = new DB();
        ResultSet rs = database.query("SELECT COUNT(*) FROM TeamPlayer,Players WHERE TeamPlayer.TeamID = '"+teamID+"' AND TeamPlayer.PlayerID = Players.PlayerID ;");
        rs.next();
        int numRowsRS = rs.getInt(1);
        
        ResultSet dbData = database.query("SELECT Players.Name , Players.Surname  FROM TeamPlayer,Players WHERE TeamPlayer.TeamID = '"+teamID+"' AND TeamPlayer.PlayerID = Players.PlayerID;");
        String[] teamPlayers = new String[numRowsRS];
        int count = 0;

        
        while (dbData.next()) {
            teamPlayers[count] = dbData.getString("Name") + " " + dbData.getString("Surname");
            

            count++;
        }
        return teamPlayers;
    }
    public static String[] getAvaliblePlayers(char teamID) throws ClassNotFoundException, SQLException{
        DB database = new DB();
        
        Scanner sc = new Scanner(Arrays.toString(TeamManagerBE.getTeamPlayerName(""+teamID))).useDelimiter(", ");
        String allFullnameStr = "";
        while(sc.hasNext()){
            allFullnameStr += sc.next().replace("[", "").replace("]", "")+"#";
        }

        Scanner fullNameSc = new Scanner(allFullnameStr.substring(0, allFullnameStr.length()-1)).useDelimiter("#");
        
        String query = "WHERE ";
        while(fullNameSc.hasNext()){
            String fullname = fullNameSc.next();
            Scanner fullNameScanner = new Scanner(fullname);
            String name = fullNameScanner.next();
            String surname = fullNameScanner.next();
            query += "Players.Name != '"+name+"' AND Players.Surname != '"+surname+"' AND ";
        }
        
        ResultSet avaliblePlayersRS = database.query("SELECT COUNT(*) FROM Players "+query+" Players.Name = Players.Name;");
        avaliblePlayersRS.next();
        int numRows = avaliblePlayersRS.getInt(1);
        
        ResultSet dbData = database.query("SELECT Players.Name,Players.Surname FROM Players "+query+" Players.Name = Players.Name;");
        String[] avaliblePlayers = new String[numRows];
        int count =0;
        
        while(dbData.next()){           
            avaliblePlayers[count] = dbData.getString("Name") + " " + dbData.getString("Surname");

            count++;
        }
        
        return avaliblePlayers;
    }

}
