
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Search
            {
                
                String url = "jdbc:mysql://localhost:3306/sakila";
                String USERNAME = "root";
                String PASSWORD = "password";
                
                Connection con = null;
                PreparedStatement ps1 = null;
                ResultSet rs =  null;
                
                
                public Search()
                {
                    try
                        {
                            Class.forName("com.mysql.jdbc.Driver");
                            this.con = (Connection) DriverManager.getConnection(url, USERNAME, PASSWORD);
                            this.ps1 = (PreparedStatement) con.prepareStatement( "SELECT a.first_name, a.last_name, c.title FROM actor a, film_actor b, film c WHERE a.first_name = ? AND a.last_name = ? AND a.actor_id = b.actor_id AND b.film_id = c.film_id");
                            //this.ps1 = (PreparedStatement) con.prepareStatement( "select * from sakila.actor");
                        }
                    catch(SQLException e)
                    {
                        e.printStackTrace();
                    }catch(ClassNotFoundException f){
                        f.printStackTrace();
                    }
                }
                
                public ResultSet getSearchResult(String firstName, String lastName)
                {
                    try
                    {   this.ps1.setString(1, firstName);
                        this.ps1.setString(2, lastName);
                        this.rs = ps1.executeQuery();                                        
                    }

                    catch(SQLException e)
                    {
                        e.printStackTrace();
                    }
                    return rs;
                }
            }
            