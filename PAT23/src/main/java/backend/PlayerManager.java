/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import DBBackend.DB;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class PlayerManager {
    
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
}
