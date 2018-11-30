
import java.util.List;


public interface VehicleService {
    
    List<Vehicle> Get();
    Vehicle Get(int id);
    Void Create (Vehicle vehicle);
    Void Update (Vehicle vehicle);
    Void Delete (Vehicle vehicle);
    
}
