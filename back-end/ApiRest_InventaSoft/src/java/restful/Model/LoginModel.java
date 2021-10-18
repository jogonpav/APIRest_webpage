
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
public class LoginModel {
    private int id;
    private String full_name;
    private String email;
    private String user;
    private String password;
    private int profile_id;
    private String profile;

    public LoginModel() {
    }

    public LoginModel(int id, String full_name, String email, String user, String password, int profile_id) {
        this.id = id;
        this.full_name = full_name;
        this.email = email;
        this.user = user;
        this.password = password;
        this.profile_id = profile_id;
    }

    public LoginModel(int id, String full_name, String email, String user, String password, int profile_id, String profile) {
        this.id = id;
        this.full_name = full_name;
        this.email = email;
        this.user = user;
        this.password = password;
        this.profile_id = profile_id;
        this.profile = profile;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(int profile_id) {
        this.profile_id = profile_id;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
    
    
}
