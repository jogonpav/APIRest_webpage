package restful.Resource;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import restful.Model.UserModel;
import restful.Service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;

/**
 *
 * @author JEAR
 */
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResources {

    UserService servicio = new UserService();

    @GET
    public ArrayList<UserModel> getUser() {

        return servicio.getUser();
    }

    @Path("/{UserId}")
    @GET
    public UserModel getUser(@PathParam("UserId") int id) {

        return servicio.getUser(id);
    }
    

    @POST
    public UserModel addUser(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        UserModel user = gson.fromJson(JSON, UserModel.class);
        return servicio.addUser(user);
    }

    @DELETE
    @Path("/{UserId}")
    public String delUser(@PathParam("UserId") int id) {

        return servicio.delUser(id);

    }

    @PUT
    public UserModel updateUser(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        UserModel user = gson.fromJson(JSON, UserModel.class);
        return servicio.updateUser(user);
    }
/*
    @Path("/{EmailId}")
    @GET
    public UserModel getUsuarioemail(@PathParam("EmailId") String ema) {

        return servicio.getUsuariopwd(ema);
    }*/
}
