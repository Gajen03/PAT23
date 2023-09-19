/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import DBBackend.DB;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class PlayerManagerBE {
    
    public static void addPlayer(String name,String surname,int DOB,int kitnumber, String position) throws SQLException, ClassNotFoundException{
        DB database = new DB();
        database.update("INSERT INTO Players (Players.Name,Surname,Age,Position,KitNumber,Gender) VALUES ('"+name+"', '"+surname+"', '"+DOB+"','"+kitnumber+"','"+position+"');");
    }
    
    public static void removePlayer(int playerID) throws ClassNotFoundException, SQLException{
        DB database = new DB();
        database.update("DELETE FROM Players WHERE Players.idPlayer = "+playerID);
        database.update("DELETE FROM Stats WHERE Stats.PlayerID = "+playerID);
        database.update("DELETE FROM TeamPlayer WHERE TeamPlayer.idPlayer = "+playerID);
        
    }
    
    
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
}
