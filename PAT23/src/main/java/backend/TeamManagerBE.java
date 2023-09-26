/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import DBBackend.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class TeamManagerBE {
    
    public static void addTeam(String teamName,String schoolName,String coachName) throws ClassNotFoundException, SQLException{
        DB database = new DB();
        database.update("INSERT INTO Teams(Name,School,Coach) VALUES ('"+teamName+"', '"+schoolName+"', '"+coachName+"');");
    }
    
    public static void addPlayerToTeam(String PlayerID,String TeamID) throws ClassNotFoundException, SQLException{
        DB database = new DB();
        database.update("INSERT INTO TeamPlayer(TeamID,PlayerID) VALUES ('"+TeamID+"','"+PlayerID+"');");
       
        
    }
    public static void deletePlayerFromTeam(String PlayerID,String TeamID) throws ClassNotFoundException, SQLException{
       DB database = new DB();
       
       database.update("DELETE FROM TeamPlayer WHERE TeamPlayer.PlayerID = '"+PlayerID+"' AND TeamPlayer.TeamID = '"+TeamID+"'; ");
       
    }

    public static ArrayList<String> getRHBTeamNames() throws SQLException, ClassNotFoundException{
         DB database = new DB();
    
        ResultSet getTeamNames = database.query("SELECT Teams.Name  FROM Teams WHERE Teams.School = 'Reddam House Ballito';");
        ArrayList<String> output = new ArrayList<>();

        while (getTeamNames.next()) {
            output.add(getTeamNames.getString("Name"));
        }
      
        
        return output;
    }
    
    
    public static String getTeamID(String teamName) throws ClassNotFoundException, SQLException{
        DB database = new DB();
        ResultSet getTeamId = database.query("SELECT TeamID FROM Teams WHERE Teams.Name = '"+teamName+"' ;");
        return DB.toString(getTeamId);
    }
    public static String getTeamName(char teamID) throws ClassNotFoundException, SQLException{
        DB database = new DB();
        ResultSet getTeamId = database.query("SELECT Teams.Name FROM Teams WHERE Teams.TeamID = '"+teamID+"' ;");
        return DB.toString(getTeamId);
    }
    public static String[] getTeamPlayerName(char teamID) throws ClassNotFoundException, SQLException{
        DB database = new DB();
        ResultSet getCount = database.query("SELECT COUNT(*) FROM TeamPlayer,Players WHERE Players.PlayerID = TeamPlayer.PlayerID AND TeamPlayer.TeamID = '"+teamID+"' ;");
        getCount.next();
        int numRows = getCount.getInt(1);
        
        ResultSet dbData = database.query("SELECT Players.Name , Players.Surname FROM TeamPlayer,Players WHERE Players.PlayerID = TeamPlayer.PlayerID AND TeamPlayer.TeamID = '"+teamID+"';");
        String[] players = new String[numRows];
        int count = 0;
        
        while (dbData.next()) {
            players[count] = dbData.getString("Name") + " " + dbData.getString("Surname");
          

            count++;
        }
        
        return players;
    }
    
    
    public static String[][] getPlayersInfoForTeam(String teamName) throws SQLException, ClassNotFoundException {
        DB database = new DB();
        

        ResultSet countQueryTeamPlayer = database.query("SELECT COUNT(*) FROM Players, TeamPlayer, Teams WHERE (Teams.TeamID=TeamPlayer.TeamID AND TeamPlayer.PlayerID = Players.PlayerID) AND (Teams.Name = \'"+teamName+"\');");
//        countQueryTeamPlayer.next();
//        int numRows = countQueryTeamPlayer.getInt(1);


        ResultSet dbData = database.query("SELECT Players.Name , Players.Surname ,Position,KitNumber FROM Players, TeamPlayer, Teams WHERE (Teams.TeamID=TeamPlayer.TeamID AND TeamPlayer.PlayerID = Players.PlayerID) AND (Teams.Name = \'"+teamName+"\');");
        String[][] outputTable = new String[10000][5];
        int count = 0;
        while (dbData.next()) {
            outputTable[count][0] = dbData.getString("Name");
            outputTable[count][1] = dbData.getString("Surname");
            outputTable[count][2] = dbData.getString("Position");
            outputTable[count][3] = dbData.getString("KitNumber");

            count++;
        }
        return outputTable;
    }
    
    public static String [] getPlayerInfoHeaders(){
        String [] headers = {"NAMES","SURNAMES","POSITION","KIT NUMBER"};
        return headers;
    }





}
