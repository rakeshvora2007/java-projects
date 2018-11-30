
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;


 
public class employeeDetails implements Serializable {
    
    String firstName, lastName;
    int id;

    public employeeDetails(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public employeeDetails() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
       
    
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        employeeDetails mb = new employeeDetails("first value", "second value",1);
        employeeDetails eb = new employeeDetails("asdf", "sfddsf",2);

	// write object to file
        try {
            FileOutputStream fos = new FileOutputStream("D:\\mybean");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
	    oos.writeObject(mb);
	    oos.writeObject(eb);
            oos.close();
            
            
            // read object from a file
            FileInputStream fis = new FileInputStream("D:\\mybean");
            ObjectInputStream ois = new ObjectInputStream(fis);
            employeeDetails emp = (employeeDetails) ois.readObject();
            System.out.println(emp.getId());
            System.out.println(emp.getFirstName());
            System.out.println(emp.getLastName());
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(employeeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
			
    }
   
}
