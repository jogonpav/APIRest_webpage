
package restful.Resource;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import restful.Model.TypeMovementModel;
import restful.Service.TypeMovementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
/**
 *
 * @author JEAR
 */
@Path("typeMovements")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TypeMovementResource {
    TypeMovementService servicio = new TypeMovementService();
    
    @GET
    public ArrayList<TypeMovementModel> getTypeMovement() {

        return servicio.getTypeMovement();
    }
    
    @Path("/{TypeMovementId}")
    @GET
    public TypeMovementModel getTypeMovement(@PathParam("TypeMovementId") int id) {

        return servicio.getTypeMovement(id);
    }
    @POST
    public TypeMovementModel addTypeMovement(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        TypeMovementModel typeMovement = gson.fromJson(JSON, TypeMovementModel.class);
        return servicio.addTypeMovement(typeMovement);
    }

    @DELETE
    @Path("/{TypeMovementId}")
    public String delTypeMovement(@PathParam("TypeMovementId") int id) {

        return servicio.delTypeMovement(id);

    }

    @PUT
    public TypeMovementModel updateProfile(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        TypeMovementModel typeMovement = gson.fromJson(JSON, TypeMovementModel.class);
        return servicio.updateTypeMovement(typeMovement);
    }
}
