
package restful.Model;
import java.sql.Date;
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
public class ProductModel {
    private int id;
    private String code;
    private String product_name;
    private String reference;
    private int quantity;
    private int category_id;
    private String category;
    private String expiration;
    private String location;

    public ProductModel() {
    }

    public ProductModel(int id, String code, String product_name, String reference, int quantity, int category_id, String expiration, String location) {
        this.id = id;
        this.code = code;
        this.product_name = product_name;
        this.reference = reference;
        this.quantity = quantity;
        this.category_id = category_id;
        this.expiration = expiration;
        this.location = location;
    }

    public ProductModel(int id, String code, String product_name, String reference, int quantity, int category_id, String category, String expiration, String location) {
        this.id = id;
        this.code = code;
        this.product_name = product_name;
        this.reference = reference;
        this.quantity = quantity;
        this.category_id = category_id;
        this.category = category;
        this.expiration = expiration;
        this.location = location;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    
    
}
