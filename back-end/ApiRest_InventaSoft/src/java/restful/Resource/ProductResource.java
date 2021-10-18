
package restful.Resource;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import restful.Model.ProductModel;
import restful.Service.ProductService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
/**
 *
 * @author JEAR
 */
@Path("products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {
    ProductService servicio = new ProductService();
    
    @GET
    public ArrayList<ProductModel> getProduct() {

        return servicio.getProduct();
    }
    
    @Path("/{ProductId}")
    @GET
    public ProductModel getProduct(@PathParam("ProductId") int id) {

        return servicio.getProduct(id);
    }
    @POST
    public ProductModel addProduct(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        ProductModel product = gson.fromJson(JSON, ProductModel.class);
        return servicio.addProduct(product);
    }

    @DELETE
    @Path("/{ProductId}")
    public String delProduct(@PathParam("ProductId") int id) {

        return servicio.delProduct(id);

    }

    @PUT
    public ProductModel updateProduct(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        ProductModel product = gson.fromJson(JSON, ProductModel.class);
        return servicio.updateProduct(product);
    }
}
