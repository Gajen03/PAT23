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

    public static void addGame(String location, String RHbTeam, String OpponentTeam,int RhbTeamScore, int OpponentScore) throws ClassNotFoundException, SQLException {

        DB database = new DB();
        database.update("INSERT INTO Results(Location,TeamA,TeamB,TeamAGoals,TeamBGoals) VALUES('" + location + "','" + RHbTeam + "','" + OpponentTeam + "','"+ RhbTeamScore + "','"  + OpponentScore + "');");

    }

    public static void addGameGoals(String gameID, String playerFullName, int goals) throws ClassNotFoundException, SQLException {

        Scanner sc = new Scanner(playerFullName).useDelimiter(" ");
        String playerID = "";
        while (sc.hasNext()) {
            String name = sc.next();
            String surname = sc.next();
            playerID += PlayerManagerBE.getPlayerID(name, surname);
        }
        DB database = new DB();
        database.update("INSERT INTO Stats(Stats.GameID,Stats.PlayerID,Goals) VALUES('" + gameID + "','" + playerID + "','" + goals + "');");

    }

    public static void addGameAssists(String gameID, String playerFullName, int assists) throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(playerFullName).useDelimiter(" ");
        String playerID = "";
        while (sc.hasNext()) {
            String name = sc.next();
            String surname = sc.next();
            playerID += PlayerManagerBE.getPlayerID(name, surname);
        }
        DB database = new DB();
        database.update("INSERT INTO Stats(Stats.GameID,Stats.PlayerID,Assists) VALUES('" + gameID + "','" + playerID + "','" + assists + "') ;");

    }

    public static String getGameID(String location, String RHbTeam, String OpponentTeam, int OpponentScore) throws ClassNotFoundException, SQLException {
        DB database = new DB();
        ResultSet getGameID = database.query("SELECT GameID FROM Game WHERE Location = " + location + "TeamA = " + RHbTeam + "TeamB = " + OpponentTeam + "TeamBGoals = " + OpponentScore + ";");
        return DB.toString(getGameID);
    }
}
