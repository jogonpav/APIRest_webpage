
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
import restful.Model.ProfileModel;
import restful.Model.Conexion;
/**
 *
 * @author JEAR
 */
@Path("profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileService {
    public ArrayList<ProfileModel> getProfile() {
        ArrayList<ProfileModel> lista = new ArrayList<>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM profile";

        try {
            Statement stm = conn.getCon().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ProfileModel profile = new ProfileModel();
                profile.setId(rs.getInt("id"));
                profile.setType(rs.getString("type"));
               
                lista.add(profile);
            }
        } catch (SQLException e) {
        }

        return lista;
    }

    public ProfileModel getProfile(int id) {
        ProfileModel profile = new ProfileModel();
        Conexion conex = new Conexion();
        String Sql = "SELECT * FROM profile WHERE id = ?";

        try {

            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                profile.setId(rs.getInt("id"));
                profile.setType(rs.getString("type"));
                
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return profile;
    }

    public ProfileModel addProfile(ProfileModel profile) {
        Conexion conex = new Conexion();
        String Sql = "INSERT INTO profile(id,type)";
        Sql = Sql + "values (?,?)";

        try {
            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, profile.getId());
            pstm.setString(2, profile.getType());
            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return profile;
    }

    public ProfileModel updateProfile(ProfileModel profile) {
        Conexion conn = new Conexion();
        String sql = "UPDATE profile SET type=? WHERE id= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            pstm.setString(1, profile.getType());
            pstm.setInt(2, profile.getId());
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al actualizar  " + excepcion.getMessage());
            return null;
        }
        return profile;
    }

    public String delProfile(int id) {
        Conexion conn = new Conexion();

        String sql = "DELETE FROM profile WHERE id= ?";
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
