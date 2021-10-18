
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
import restful.Model.TypeMovementModel;
import restful.Model.Conexion;
/**
 *
 * @author JEAR
 */
@Path("typeMovements")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TypeMovementService {
    public ArrayList<TypeMovementModel> getTypeMovement() {
        ArrayList<TypeMovementModel> lista = new ArrayList<>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM type_movements";

        try {
            Statement stm = conn.getCon().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                TypeMovementModel profile = new TypeMovementModel();
                profile.setId(rs.getInt("id"));
                profile.setType_movement(rs.getString("type_movement"));
               
                lista.add(profile);
            }
        } catch (SQLException e) {
        }

        return lista;
    }

    public TypeMovementModel getTypeMovement(int id) {
        TypeMovementModel profile = new TypeMovementModel();
        Conexion conex = new Conexion();
        String Sql = "SELECT * FROM type_movements WHERE id = ?";

        try {

            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                profile.setId(rs.getInt("id"));
                profile.setType_movement(rs.getString("type_movement"));
                
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return profile;
    }

    public TypeMovementModel addTypeMovement(TypeMovementModel profile) {
        Conexion conex = new Conexion();
        String Sql = "INSERT INTO type_movements(id,type_movement)";
        Sql = Sql + "values (?,?)";

        try {
            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, profile.getId());
            pstm.setString(2, profile.getType_movement());
            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return profile;
    }

    public TypeMovementModel updateTypeMovement(TypeMovementModel profile) {
        Conexion conn = new Conexion();
        String sql = "UPDATE type_movements SET type_movement=? WHERE id= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            pstm.setString(1, profile.getType_movement());
            pstm.setInt(2, profile.getId());
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al actualizar  " + excepcion.getMessage());
            return null;
        }
        return profile;
    }

    public String delTypeMovement(int id) {
        Conexion conn = new Conexion();

        String sql = "DELETE FROM type_movements WHERE id= ?";
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
