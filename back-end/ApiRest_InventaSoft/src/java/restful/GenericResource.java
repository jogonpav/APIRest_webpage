
package restful;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author JEAR
 */
@Path("inventarios")
public class GenericResource {

    @Context
    private UriInfo context;

    
    public GenericResource() {
    }

    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getXml() {
       return "Hola estás conectado al recurso inventarios desde mi API";
    }

    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
