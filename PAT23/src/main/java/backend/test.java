/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        //PlayerManager.addPlayer("Gajen", "Naidoo",'2006-01-03', 6, "ST");
//        String[] names = TeamManagerBE.getTeamPlayerName("1");
//        for (int i = 0; i < names.length; i++) {
//            System.out.println(names[i]);
//            
//        }

          String teamName = TeamManagerBE.getTeamID("RHB Boys 1st");
          System.out.println(teamName);
          
          String playerID = PlayerManagerBE.getPlayerID("Gajendra", "Naidoo");
          System.out.println(playerID);
    }
}
