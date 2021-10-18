
package restful.Service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import restful.Model.MovementModel;
import restful.Model.Conexion;
/**
 *
 * @author JEAR
 */
@Path("movements")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovementService {
    public ArrayList<MovementModel> getMovement() {
        ArrayList<MovementModel> movement = new ArrayList<>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM movements";

        try {
            Statement stm = conn.getCon().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                MovementModel movimiento = new MovementModel();
                movimiento.setId(rs.getInt("id"));
                movimiento.setQuantity(rs.getInt("quantity"));
                movimiento.setDate(rs.getString("date"));
                movimiento.setProduct_id(rs.getInt("product_id"));
                movimiento.setUser_id(rs.getInt("users_id"));
                movimiento.setType_movement_id(rs.getInt("type_movement_id"));
                movement.add(movimiento);
            }
        } catch (SQLException e) {
        }

        return movement;
    }

    public MovementModel getMovement(int id) {
        MovementModel movement = new MovementModel();
        Conexion conex = new Conexion();
        String Sql = "SELECT * FROM movements WHERE id = ?";

        try {

            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

               movement.setId(rs.getInt("id"));
                movement.setQuantity(rs.getInt("quantity"));
                movement.setDate(rs.getString("date"));
                movement.setProduct_id(rs.getInt("product_id"));
                movement.setUser_id(rs.getInt("user_id"));
                movement.setType_movement_id(rs.getInt("type_movement_id"));
                
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return movement;
    }

    public MovementModel addMovement(MovementModel movement) {
        Conexion conex = new Conexion();
        String Sql = "INSERT INTO movements(id,quantity,date,product_id,users_id,type_movement_id)";
        Sql = Sql + "values (?,?,?,?,?,?)";

        try {
            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, movement.getId());
            pstm.setInt(2, movement.getQuantity());
            pstm.setString(3, movement.getDate());
            pstm.setInt(4, movement.getProduct_id());
            pstm.setInt(5, movement.getUser_id());
            pstm.setInt(6, movement.getType_movement_id());
            
            
            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return movement;
    }

    public MovementModel updateMovement(MovementModel movement) {
        Conexion conn = new Conexion();
        String sql = "UPDATE movements SET quantity=?,date=?,product_id=?,users_id=?,type_movement_id=? WHERE id= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
             pstm.setInt(1, movement.getQuantity());
            pstm.setString(2, movement.getDate());
            pstm.setInt(3, movement.getProduct_id());
            pstm.setInt(4, movement.getUser_id());
            pstm.setInt(5, movement.getType_movement_id());
            pstm.setInt(6, movement.getId());
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al actualizar  " + excepcion.getMessage());
            return null;
        }
        return movement;
    }

    public String delMovement(int id) {
        Conexion conn = new Conexion();

        String sql = "DELETE FROM movements WHERE id= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al eliminar  " + excepcion.getMessage());
            return "{\"Accion\":\"Error\"}";
        }
        return "{\"Accion\":\"Registro Borrado\"}";
    }
}
