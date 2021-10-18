
package restful.Model;
import java.sql.Date;
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
public class MovementModel {
   private int id;
   private int quantity;
   private String date;
   private int product_id;
   private int user_id;
   private int type_movement_id;

    public MovementModel() {
    }

    public MovementModel(int id, int quantity, String date, int product_id, int users_id, int type_movement_id) {
        this.id = id;
        this.quantity = quantity;
        this.date = date;
        this.product_id = product_id;
        this.user_id = users_id;
        this.type_movement_id = type_movement_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

 
    public int getType_movement_id() {
        return type_movement_id;
    }

    public void setType_movement_id(int type_movement_id) {
        this.type_movement_id = type_movement_id;
    }
   
   
}
