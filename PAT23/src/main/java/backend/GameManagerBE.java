/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import DBBackend.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class GameManagerBE {

    public static void addGame(String location, String RHbTeam, String OpponentTeam,int RhbTeamScore, int OpponentScore, String Date) throws ClassNotFoundException, SQLException {

        DB database = new DB();
        database.update("INSERT INTO Results(TeamA,TeamB,Location,TeamAGoals,TeamBGoals,MatchDate) VALUES('" + RHbTeam + "','" + OpponentTeam + "','"+ location + "','" + RhbTeamScore + "','"  + OpponentScore + "','" + Date +"');");

    }

    public static void addGameGoals(String gameID, String playerFullName, int goals) throws ClassNotFoundException, SQLException {

       if(!playerFullName.equalsIgnoreCase("")){
           Scanner sc = new Scanner(playerFullName).useDelimiter(" ");
        String playerID = "";
        while (sc.hasNext()) {
            String name = sc.next();
            String surname = sc.next();
            playerID += PlayerManagerBE.getPlayerID(name, surname);
        }
        DB database = new DB();
        database.update("INSERT INTO Stats(Stats.GameID,Stats.PlayerID,Goals) VALUES('" + gameID + "','" + playerID + "','" + goals + "');");

       } else{
           System.out.println("blank");
       }
        
    }

    public static void addGameAssists(String gameID, String playerFullName, int assists) throws ClassNotFoundException, SQLException {
        
        if(!playerFullName.equalsIgnoreCase("")){
        Scanner sc = new Scanner(playerFullName).useDelimiter(" ");
        String playerID = "";
        while (sc.hasNext()) {
            String name = sc.next();
            String surname = sc.next();
            playerID += PlayerManagerBE.getPlayerID(name, surname);
        }
        DB database = new DB();
        database.update("INSERT INTO Stats(Stats.GameID,Stats.PlayerID,Assists) VALUES('" + gameID + "','" + playerID + "','" + assists + "') ;");
        }else{
            System.out.println("blank");
        }
    }

    public static String getGameID(String date) throws ClassNotFoundException, SQLException {
        DB database = new DB();
        ResultSet getGameID = database.query("SELECT Results.GameID FROM Results WHERE MatchDate = '"+date+"' LIMIT 1;");
        return DB.toString(getGameID).replace("#", "");
    }
    
    public static String getLocation(String gameID) throws ClassNotFoundException, SQLException{
        DB database = new DB();
        ResultSet getLocation = database.query("SELECT Location FROM Results WHERE Results.GameID = '"+gameID+"' ;");
        return DB.toString(getLocation).replace("#", "");
    }

}
