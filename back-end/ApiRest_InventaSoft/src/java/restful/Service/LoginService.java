
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
import restful.Model.LoginModel;
import restful.Model.Conexion;
/**
 *
 * @author JEAR
 */
public class LoginService {
     public LoginModel getUser(String user) {
        LoginModel usuario = new LoginModel();
        Conexion conex = new Conexion();
        String Sql = "SELECT * FROM users WHERE user = ?";

        try {

            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setString(1, user);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                usuario.setId(rs.getInt("id"));
                usuario.setFull_name(rs.getString("full_name"));
                usuario.setEmail(rs.getString("email"));
                usuario.setUser(rs.getString("user"));
                usuario.setPassword(rs.getString("password"));
                usuario.setProfile_id(rs.getInt("profile_id"));

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return usuario;
    }
}
