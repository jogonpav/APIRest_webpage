
package restful.Resource;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import restful.Model.LoginModel;
import restful.Service.LoginService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;

/**
 *
 * @author JEAR
 */
@Path("login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {
    LoginService servicio = new LoginService();
    
     @Path("/{User}")
    @GET
    public LoginModel getUser(@PathParam("User") String user) {

        return servicio.getUser(user);
    }
    
}
