
package restful.Resource;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import restful.Model.CategoryModel;
import restful.Service.CategoryService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;

/**
 *
 * @author SENA
 */
@Path("categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

/**
 *
 * @author JEAR
 */


public class CategoryResource {
    CategoryService servicio = new CategoryService();
    
    @GET
    public ArrayList<CategoryModel> getCategory() {

        return servicio.getCategory();
    }
    
    @Path("/{CategoryId}")
    @GET
    public CategoryModel getCategory(@PathParam("CategoryId") int id) {

        return servicio.getCategory(id);
    }
    @POST
    public CategoryModel addCategory(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        CategoryModel category = gson.fromJson(JSON, CategoryModel.class);
        return servicio.addCategory(category);
    }

    @DELETE
    @Path("/{CategoryId}")
    public String delCategory(@PathParam("CategoryId") int id) {

        return servicio.delCategory(id);

    }

    @PUT
    public CategoryModel updateCategory(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        CategoryModel category = gson.fromJson(JSON, CategoryModel.class);
        return servicio.updateCategory(category);
    }
}
