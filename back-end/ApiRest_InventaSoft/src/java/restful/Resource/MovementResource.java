
package restful.Resource;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import restful.Model.MovementModel;
import restful.Service.MovementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
/**
 *
 * @author JEAR
 */
@Path("movements")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovementResource {
    MovementService servicio = new MovementService();
    
    @GET
    public ArrayList<MovementModel> getMovement() {

        return servicio.getMovement();
    }
    
    @Path("/{MovementId}")
    @GET
    public MovementModel getMovement(@PathParam("MovementId") int id) {

        return servicio.getMovement(id);
    }
    @POST
    public MovementModel addProduct(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        MovementModel movement = gson.fromJson(JSON, MovementModel.class);
        return servicio.addMovement(movement);
    }

    @DELETE
    @Path("/{MovementId}")
    public String delMovement(@PathParam("MovementId") int id) {

        return servicio.delMovement(id);

    }

    @PUT
    public MovementModel updateProduct(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        MovementModel movement = gson.fromJson(JSON, MovementModel.class);
        return servicio.updateMovement(movement);
    }
}
