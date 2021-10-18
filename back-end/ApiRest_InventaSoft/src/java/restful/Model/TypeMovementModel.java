
package restful.Model;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author JEAR
 */
@XmlRootElement
public class TypeMovementModel {
    private int id;
    private String type_movement;

    public TypeMovementModel() {
    }

    public TypeMovementModel(int id, String type_movement) {
        this.id = id;
        this.type_movement = type_movement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType_movement() {
        return type_movement;
    }

    public void setType_movement(String type_movement) {
        this.type_movement = type_movement;
    }
    
    
}
