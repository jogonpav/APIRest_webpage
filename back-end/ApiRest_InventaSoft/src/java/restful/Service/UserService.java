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
import restful.Model.UserModel;
import restful.Model.ProfileModel;
import restful.Model.Conexion;

/**
 *
 * @author JEAR
 */
public class UserService {

    public ArrayList<UserModel> getUser() {
        ArrayList<UserModel> lista = new ArrayList<>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM users";
        //  String sql2="SELECT type FROM profile as p, users as u where p.id=u.profile_id AND u.profile_id=?";

        try {
            Statement stm = conn.getCon().createStatement();
            Statement stm2 = conn.getCon().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                UserModel user = new UserModel();
                ProfileModel profile = new ProfileModel();
                user.setId(rs.getInt("id"));
                user.setFull_name(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setUser(rs.getString("user"));
                user.setPassword(rs.getString("password"));
                user.setProfile_id(rs.getInt("profile_id"));
                String sql2 = "SELECT type FROM profile as p, users as u where p.id=u.profile_id AND u.profile_id=" + rs.getInt("profile_id");
                ResultSet rs2 = stm2.executeQuery(sql2);
                if (rs2.next()) {
                    profile.setType(rs2.getString("type"));
                    user.setProfile(profile.getType());
                }

                lista.add(user);
            }
        } catch (SQLException e) {
        }

        return lista;
    }

    public UserModel getUser(int id) {
        UserModel user = new UserModel();
        Conexion conex = new Conexion();
        String Sql = "SELECT * FROM users WHERE id = ?";

        try {

            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                user.setId(rs.getInt("id"));
                user.setFull_name(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setUser(rs.getString("user"));
                user.setPassword(rs.getString("password"));
                user.setProfile_id(rs.getInt("profile_id"));

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return user;
    }

    public UserModel addUser(UserModel user) {
        Conexion conex = new Conexion();
        String Sql = "INSERT INTO users(id,full_name,email,user,password,profile_id)";
        Sql = Sql + "values (?,?,?,?,?,?)";

        try {
            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, user.getId());
            pstm.setString(2, user.getFull_name());
            pstm.setString(3, user.getEmail());
            pstm.setString(4, user.getUser());
            pstm.setString(5, user.getPassword());
            pstm.setInt(6, user.getProfile_id());
            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return user;
    }

    public UserModel updateUser(UserModel user) {
        Conexion conn = new Conexion();
        String sql = "UPDATE users SET full_name=?, email=?, user=?, password=?, profile_id=? WHERE id= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            pstm.setString(1, user.getFull_name());
            pstm.setString(2, user.getEmail());
            pstm.setString(3, user.getUser());
            pstm.setString(4, user.getPassword());
            pstm.setInt(5, user.getProfile_id());
            pstm.setInt(6, user.getId());
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al actualizar  " + excepcion.getMessage());
            return null;
        }
        return user;
    }

    public String delUser(int id) {
        Conexion conn = new Conexion();

        String sql = "DELETE FROM users WHERE id= ?";
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
