
package restful.Service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import restful.Model.ProductModel;
import restful.Model.CategoryModel;
import restful.Model.Conexion;
/**
 *
 * @author JEAR
 */
@Path("products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductService {
     public ArrayList<ProductModel> getProduct() {
        ArrayList<ProductModel> product = new ArrayList<>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM products";

        try {
            Statement stm = conn.getCon().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            Statement stm2 = conn.getCon().createStatement();
            while (rs.next()) {
                ProductModel producto = new ProductModel();
                CategoryModel category = new CategoryModel();
                producto.setId(rs.getInt("id"));
                producto.setCode(rs.getString("code"));
                producto.setProduct_name(rs.getString("product_name"));
                producto.setReference(rs.getString("reference"));
                producto.setQuantity(rs.getInt("quantity"));
                producto.setCategory_id(rs.getInt("category_id"));
                String sql2 = "SELECT type FROM  products AS p,category as c where p.category_id=c.id AND p.category_id="+ rs.getInt("category_id");
                
                ResultSet rs2 = stm2.executeQuery(sql2);
                
                if (rs2.next()) {
                    producto.setCategory(rs2.getString("type"));
                }
                producto.setExpiration(rs.getString("expiration"));
                producto.setLocation(rs.getString("location"));
                product.add(producto);
            }
        } catch (SQLException e) {
        }

        return product;
    }

    public ProductModel getProduct(int id) {
        ProductModel product = new ProductModel();
        Conexion conex = new Conexion();
        String Sql = "SELECT * FROM products WHERE id = ?";

        try {

            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            

            while (rs.next()) {

               product.setId(rs.getInt("id"));
                product.setCode(rs.getString("code"));
                product.setProduct_name(rs.getString("product_name"));
                product.setReference(rs.getString("reference"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCategory_id(rs.getInt("category_id"));
                product.setExpiration(rs.getString("expiration"));
                product.setLocation(rs.getString("location"));
                
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return product;
    }

    public ProductModel addProduct(ProductModel product) {
        Conexion conex = new Conexion();
        String Sql = "INSERT INTO products(id,code,product_name,reference,quantity,category_id,expiration,location)";
        Sql = Sql + "values (?,?,?,?,?,?,?,?)";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-mm-dd");
       // String fecha= formato.format(product.getExpiration());
        
        try {
            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            
            pstm.setInt(1, product.getId());
            pstm.setString(2, product.getCode());
            pstm.setString(3, product.getProduct_name());
            pstm.setString(4, product.getReference());
            pstm.setInt(5, product.getQuantity());
            pstm.setInt(6, product.getCategory_id());
            //pstm.setString(7, fecha.format(product.getExpiration()));
            pstm.setString(7, product.getExpiration());
            pstm.setString(8, product.getLocation());
            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return product;
    }

    public ProductModel updateProduct(ProductModel product) {
        Conexion conn = new Conexion();
        String sql = "UPDATE products SET code=?, product_name=?, reference=?, quantity=?, category_id=?, expiration=?, location=? WHERE id= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            pstm.setString(1, product.getCode());
            pstm.setString(2, product.getProduct_name());
            pstm.setString(3, product.getReference());
            pstm.setInt(4, product.getQuantity());
            pstm.setInt(5, product.getCategory_id());
            pstm.setString(6, product.getExpiration());
            pstm.setString(7, product.getLocation());
            pstm.setInt(8, product.getId());
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al actualizar  " + excepcion.getMessage());
            return null;
        }
        return product;
    }

    public String delProduct(int id) {
        Conexion conn = new Conexion();

        String sql = "DELETE FROM products WHERE id= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al eliminar  " + excepcion.getMessage());
            return "{\"Accion\":\"Error\"}";
        }
        return "{\"Accion\":\"Registro Borrado\"}";
    }
}
