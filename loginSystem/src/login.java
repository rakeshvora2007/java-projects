/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rakesh J
 */
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class login {

    Connection con;
    Statement st;
    ResultSet rs;
    
    JFrame jf = new JFrame("User Login");
    JLabel l = new JLabel("Username:");
    JLabel l1 = new JLabel("Password:");
    JTextField t = new JTextField(10);
    JTextField t1 = new JTextField(10);
    JButton b = new JButton("Login");

    public login() {
        connect();
        frame();

    }

    public void connect() {

        try {
            

            String db = "jdbc:ucanaccess://D:/Database1.accdb";
            con = DriverManager.getConnection(db);

            st = con.createStatement();
            
            
        } catch (Exception e) {
        }

    }

    public void frame() {
        
        jf.setSize(600,400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        
        JPanel jp = new JPanel();
        jp.add(l);
        jp.add(t);  
        jp.add(l1);
        jp.add(t1);
        jp.add(b);
        
        jf.add(jp);
        
        b.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
             try {
                String user1 = t.getText().trim();
                String pass = t1.getText().trim();
                
                String sql = "select 'user', 'pass' from Table1 where username = '"+user1+"'and password = '"+pass+"'";
                rs = st.executeQuery(sql);
                
                int count = 0;
                
                while(rs.next()){
                    count = count++;
                }
                
                if(count == 1){
                    JOptionPane.showMessageDialog(null, "User Found Access Granted!");
                }
                else if(count > 1){
                    JOptionPane.showMessageDialog(null, "Duplicate User, Access Denied");
                }
                
                else{
                    JOptionPane.showMessageDialog(null, "User Doesn't Exist");
                }
                                      
                
                } catch (SQLException ex) {
                    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public static void main(String[] args) {
        
        new login();

    }
}
