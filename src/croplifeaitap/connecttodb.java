/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package croplifeaitap;

import com.mysql.cj.api.mysqla.result.Resultset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author albert
 */
public class connecttodb {

    Connection con = null;
    PreparedStatement pst = null;
    Resultset rs = null;
public static Connection Connectdb(){
  try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/croplifeaitap","root", "");
            //JOptionPane.showMessageDialog(null, "connected");
            return con;
          
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
}  
}
