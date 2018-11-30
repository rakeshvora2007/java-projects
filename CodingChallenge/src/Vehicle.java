
import java.util.List;


public class Vehicle implements VehicleService{
    int Id, Year;
    String Make, Model, Class1;

    public Vehicle() {
    }
    
    public Vehicle(int Id, int Year, String Make, String Model, String Class1) {
        this.Id = Id;
        this.Year = Year;
        this.Make = Make;
        this.Model = Model;
        this.Class1 = Class1;
    }

    
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int Year) {
        this.Year = Year;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String Make) {
        this.Make = Make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public String getClass1() {
        return Class1;
    }

    public void setClass1(String Class1) {
        this.Class1 = Class1;
    }

    @Override
    public List<Vehicle> Get() {
        throw new UnsupportedOperationException("Not supported yet.");
        
    }

    @Override
    public Vehicle Get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Void Create(Vehicle vehicle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Void Update(Vehicle vehicle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Void Delete(Vehicle vehicle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
