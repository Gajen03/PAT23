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
        DB database = new DB();
        ResultSet getGoals = database.query("SELECT COUNT(*) FROM Results WHERE TeamAGoals > TeamBGoals;");
        return DB.toString(getGoals).replace("#", "");        
    }
     
    public static String getLosses(String teamID) throws ClassNotFoundException, SQLException{
        DB database = new DB();
        ResultSet getGoals = database.query("SELECT COUNT(*) FROM Results WHERE TeamAGoals < TeamBGoals;");
        return DB.toString(getGoals).replace("#", "");        
    }
    
    public static String getDraws(String teamID) throws ClassNotFoundException, SQLException{
        DB database = new DB();
        ResultSet getGoals = database.query("SELECT COUNT(*) FROM Results WHERE TeamAGoals = TeamBGoals;");
        return DB.toString(getGoals).replace("#", "");        
    }
    
    
    
    
    
    ///////// PLAYER STAT CALCULATIONS///////////////////////////
    
}
