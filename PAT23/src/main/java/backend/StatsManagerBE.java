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
public class StatsManagerBE {
    
    public static String getCoach(String teamName) throws SQLException, ClassNotFoundException{
        DB database = new DB();
        ResultSet getCoachNameQuery = database.query("SELECT Coach FROM Teams WHERE Teams.Name = '"+teamName+"';");

        return DB.toString(getCoachNameQuery).replace("#", " ");
        
    }
    
     public static String getRHBGoals(String teamID) throws ClassNotFoundException, SQLException{
        DB database = new DB();
        ResultSet getGoals = database.query("SELECT SUM(TeamAGoals) FROM Results;");
        return DB.toString(getGoals).replace("#", "");        
    }
     
     public static String getWins(String teamID) throws ClassNotFoundException, SQLException{
        String teamName = TeamManagerBE.getTeamName(teamID);
        DB database = new DB();
        ResultSet getGoals = database.query("SELECT DISTINCT COUNT(*)FROM gajendranDB.Stats,Results WHERE Results.TeamA = '"+teamName+"' AND Stats.GameID = Results.GameID AND TeamAGoals > TeamBGoals GROUP BY Results.GameID ;");
        return DB.toString(getGoals).replace("#", "");        
    }
     
    public static String getLosses(String teamID) throws ClassNotFoundException, SQLException{
        String teamName = TeamManagerBE.getTeamName(teamID);
        DB database = new DB();
        ResultSet getGoals = database.query("SELECT DISTINCT COUNT(*)FROM gajendranDB.Stats,Results WHERE Results.TeamA = '"+teamName+"' AND Stats.GameID = Results.GameID AND TeamAGoals < TeamBGoals GROUP BY Results.GameID ;");
        return DB.toString(getGoals).replace("#", "");        
    }
    
    public static String getDraws(String teamID) throws ClassNotFoundException, SQLException{
        String teamName = TeamManagerBE.getTeamName(teamID);
        DB database = new DB();
        ResultSet getGoals = database.query("SELECT DISTINCT COUNT(*)FROM gajendranDB.Stats,Results WHERE Results.TeamA = '"+teamName+"' AND Stats.GameID = Results.GameID AND TeamAGoals = TeamBGoals GROUP BY Results.GameID ;");
        return DB.toString(getGoals).replace("#", "");        
    }
    
    
    
    
    
    ///////// PLAYER STAT CALCULATIONS///////////////////////////
    public static String getTopG(String teamID) throws ClassNotFoundException, SQLException {
        DB database = new DB();
        ResultSet getTopGName = database.query("SELECT Players.name\n"
                + "FROM gajendranDB.Stats,Players,TeamPlayer\n"
                + "WHERE TeamPlayer.TeamID = (\n"
                + "	SELECT DISTINCT TeamPlayer.TeamID\n"
                + "	FROM gajendranDB.TeamPlayer,Teams\n"
                + "	WHERE Teams.name = \"RHB Boys 1st\"\n"
                + "	AND TeamPlayer.TeamID =Teams.TeamID\n"
                + ")\n"
                + "AND Players.PlayerID = Stats.PlayerID\n"
                + "AND Stats.GameID IN (\n"
                + "	SELECT GameID\n"
                + "	FROM gajendranDB.Results,Teams\n"
                + "	WHERE TeamA = \"RHB Boys 1st\"\n"
                + "	AND TeamA = Teams.name\n"
                + ")\n"
                + "GROUP BY Players.name\n"
                + "ORDER BY SUM(Goals) DESC\n"
                + "LIMIT 1\n"
                + ";");
        ResultSet getTopGSurname = database.query("SELECT Players.surname\n"
                + "FROM gajendranDB.Stats,Players,TeamPlayer\n"
                + "WHERE TeamPlayer.TeamID = (\n"
                + "	SELECT DISTINCT TeamPlayer.TeamID\n"
                + "	FROM gajendranDB.TeamPlayer,Teams\n"
                + "	WHERE Teams.name = \"RHB Boys 1st\"\n"
                + "	AND TeamPlayer.TeamID =Teams.TeamID\n"
                + ")\n"
                + "AND Players.PlayerID = Stats.PlayerID\n"
                + "AND Stats.GameID IN (\n"
                + "	SELECT GameID\n"
                + "	FROM gajendranDB.Results,Teams\n"
                + "	WHERE TeamA = \"RHB Boys 1st\"\n"
                + "	AND TeamA = Teams.name\n"
                + ")\n"
                + "GROUP BY Players.surname\n"
                + "ORDER BY SUM(Goals) DESC\n"
                + "LIMIT 1\n"
                + ";");
        String name = DB.toString(getTopGName).replace("#", "");
        String surname = DB.toString(getTopGSurname).replace("#", "");

        return name + " "+surname;
    }
    
    public static String getTopA(String teamID) throws ClassNotFoundException, SQLException {
        DB database = new DB();
        ResultSet getTopGName = database.query("SELECT Players.name\n"
                + "FROM gajendranDB.Stats,Players,TeamPlayer\n"
                + "WHERE TeamPlayer.TeamID = (\n"
                + "	SELECT DISTINCT TeamPlayer.TeamID\n"
                + "	FROM gajendranDB.TeamPlayer,Teams\n"
                + "	WHERE Teams.name = \"RHB Boys 1st\"\n"
                + "	AND TeamPlayer.TeamID =Teams.TeamID\n"
                + ")\n"
                + "AND Players.PlayerID = Stats.PlayerID\n"
                + "AND Stats.GameID IN (\n"
                + "	SELECT GameID\n"
                + "	FROM gajendranDB.Results,Teams\n"
                + "	WHERE TeamA = \"RHB Boys 1st\"\n"
                + "	AND TeamA = Teams.name\n"
                + ")\n"
                + "GROUP BY Players.name\n"
                + "ORDER BY SUM(Assists) DESC\n"
                + "LIMIT 1\n"
                + ";");
        ResultSet getTopGSurname = database.query("SELECT Players.surname\n"
                + "FROM gajendranDB.Stats,Players,TeamPlayer\n"
                + "WHERE TeamPlayer.TeamID = (\n"
                + "	SELECT DISTINCT TeamPlayer.TeamID\n"
                + "	FROM gajendranDB.TeamPlayer,Teams\n"
                + "	WHERE Teams.name = \"RHB Boys 1st\"\n"
                + "	AND TeamPlayer.TeamID =Teams.TeamID\n"
                + ")\n"
                + "AND Players.PlayerID = Stats.PlayerID\n"
                + "AND Stats.GameID IN (\n"
                + "	SELECT GameID\n"
                + "	FROM gajendranDB.Results,Teams\n"
                + "	WHERE TeamA = \"RHB Boys 1st\"\n"
                + "	AND TeamA = Teams.name\n"
                + ")\n"
                + "GROUP BY Players.surname\n"
                + "ORDER BY SUM(Goals) DESC\n"
                + "LIMIT 1\n"
                + ";");
        String name = DB.toString(getTopGName).replace("#", "");        
        String surname = DB.toString(getTopGSurname).replace("#", "");
        
        return name+" "+surname;
    }
}
