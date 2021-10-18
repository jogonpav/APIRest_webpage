
package restful.Resource;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import restful.Model.ProfileModel;
import restful.Service.ProfileService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
/**
 *
 * @author JEAR
 */

@Path("profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {
    ProfileService servicio = new ProfileService();
    
    @GET
    public ArrayList<ProfileModel> getProfile() {

        return servicio.getProfile();
    }
    
    @Path("/{ProfileId}")
    @GET
    public ProfileModel getProfile(@PathParam("ProfileId") int id) {

        return servicio.getProfile(id);
    }
    @POST
    public ProfileModel addProfile(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        ProfileModel profile = gson.fromJson(JSON, ProfileModel.class);
        return servicio.addProfile(profile);
    }

    @DELETE
    @Path("/{ProfileId}")
    public String delProfile(@PathParam("ProfileId") int id) {

        return servicio.delProfile(id);

    }

    @PUT
    public ProfileModel updateProfile(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        ProfileModel profile = gson.fromJson(JSON, ProfileModel.class);
        return servicio.updateProfile(profile);
    }
}
