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

    public static void addGame(String location, String RHbTeam, String OpponentTeam, int RhbTeamScore, int OpponentScore, String Date) throws ClassNotFoundException, SQLException {

        DB database = new DB();
        database.update("INSERT INTO Results(TeamA,TeamB,Location,TeamAGoals,TeamBGoals,MatchDate) VALUES('" + RHbTeam + "','" + OpponentTeam + "','" + location + "','" + RhbTeamScore + "','" + OpponentScore + "','" + Date + "');");

    }

    public static void addGameGoals(String gameID, String playerFullName, int goals) throws ClassNotFoundException, SQLException {

        if (!playerFullName.equalsIgnoreCase("")) {
            Scanner sc = new Scanner(playerFullName).useDelimiter(" ");
            String playerID = "";
            while (sc.hasNext()) {
                String name = sc.next();
                String surname = sc.next();
                playerID += PlayerManagerBE.getPlayerID(name, surname);
            }
            DB database = new DB();
            database.update("INSERT INTO Stats(Stats.GameID,Stats.PlayerID,Goals) VALUES('" + gameID + "','" + playerID + "','" + goals + "');");

        } else {
            System.out.println("blank");
        }

    }

    public static void addGameAssists(String gameID, String playerFullName, int assists) throws ClassNotFoundException, SQLException {

        if (!playerFullName.equalsIgnoreCase("")) {
            Scanner sc = new Scanner(playerFullName).useDelimiter(" ");
            String playerID = "";
            while (sc.hasNext()) {
                String name = sc.next();
                String surname = sc.next();
                playerID += PlayerManagerBE.getPlayerID(name, surname);
            }
            DB database = new DB();
            database.update("INSERT INTO Stats(Stats.GameID,Stats.PlayerID,Assists) VALUES('" + gameID + "','" + playerID + "','" + assists + "') ;");
        } else {
            System.out.println("blank");
        }
    }

    public static String getGameID(String date) throws ClassNotFoundException, SQLException {
        DB database = new DB();
        ResultSet getGameID = database.query("SELECT Results.GameID FROM Results WHERE MatchDate = '" + date + "' LIMIT 1;");
        return DB.toString(getGameID).replace("#", "");
    }

    public static String getTeamAGoals(String gameID) throws ClassNotFoundException, SQLException {
        DB database = new DB();
        ResultSet getLocation = database.query("SELECT TeamAGoals FROM Results WHERE Results.GameID = '" + gameID + "' ;");
        return DB.toString(getLocation).replace("#", "");
    }
    public static String getTeamBGoals(String gameID) throws ClassNotFoundException, SQLException {
        DB database = new DB();
        ResultSet getLocation = database.query("SELECT TeamBGoals FROM Results WHERE Results.GameID = '" + gameID + "' ;");
        return DB.toString(getLocation).replace("#", "");
    }
    public static String getLocation(String gameID) throws ClassNotFoundException, SQLException {
        DB database = new DB();
        ResultSet getLocation = database.query("SELECT Location FROM Results WHERE Results.GameID = '" + gameID + "' ;");
        return DB.toString(getLocation).replace("#", "");
    }
    public static String getTeamAName(String gameID) throws ClassNotFoundException, SQLException {
        DB database = new DB();
        ResultSet getLocation = database.query("SELECT TeamA FROM Results WHERE Results.GameID = '" + gameID + "' ;");
        return DB.toString(getLocation).replace("#", "");
    }
    public static String getTeamBName(String gameID) throws ClassNotFoundException, SQLException {
        DB database = new DB();
        ResultSet getLocation = database.query("SELECT TeamB FROM Results WHERE Results.GameID = '" + gameID + "' ;");
        return DB.toString(getLocation).replace("#", "");
    }
    
    public static String[] getPlayerContribs(String gameID) throws SQLException, ClassNotFoundException {
        DB database = new DB();
        
        ResultSet countQueryTeamPlayer = database.query("SELECT COUNT(*) FROM Stats WHERE Stats.GameID = '"+gameID+"';");
        countQueryTeamPlayer.next();
        int numRows = countQueryTeamPlayer.getInt(1);


        ResultSet dbData = database.query("SELECT name,surname,Goals,Assists FROM gajendranDB.Stats,Players  WHERE GameID = '"+gameID+"' AND Stats.PlayerID = Players.PlayerID ;");
        String[] players = new String[numRows];
        int count = 0;
        
        while (dbData.next()) {
            players[count] = dbData.getString("name") + " " + dbData.getString("surname")+ "    Goals:" + dbData.getString("Goals") + "     Assists:" + dbData.getString("Assists")+"\n";
            
            count++;
        }
        return players;
    }
    public static String[] getAllResults(String teamName) throws SQLException, ClassNotFoundException {
        DB database = new DB();
        
        ResultSet countQueryTeamPlayer = database.query("SELECT COUNT(*) FROM Results WHERE TeamA = '"+teamName+"';");
        countQueryTeamPlayer.next();
        int numRows = countQueryTeamPlayer.getInt(1);


        ResultSet dbData = database.query("SELECT TeamA , TeamB , MatchDate FROM Results WHERE TeamA = '"+teamName+"';");
        String[] players = new String[numRows];
        int count = 0;
        
        while (dbData.next()) {
            players[count] = dbData.getString("TeamA") + "vs" + dbData.getString("TeamB")+ " -" + dbData.getString("MatchDate");
            
            count++;
        }
        return players;
    }
    
   
}
