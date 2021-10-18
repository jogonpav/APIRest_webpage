package restful.Service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import restful.Model.CategoryModel;
import restful.Model.Conexion;
/**
 *
 * @author JEAR
 */
public class CategoryService {
     public ArrayList<CategoryModel> getCategory() {
        ArrayList<CategoryModel> lista = new ArrayList<>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM category";

        try {
            Statement stm = conn.getCon().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                CategoryModel category = new CategoryModel();
                category.setId(rs.getInt("id"));
                category.setType(rs.getString("type"));
               
                lista.add(category);
            }
        } catch (SQLException e) {
        }

        return lista;
    }

    public CategoryModel getCategory(int id) {
        CategoryModel category = new CategoryModel();
        Conexion conex = new Conexion();
        String Sql = "SELECT * FROM category WHERE id = ?";

        try {

            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                category.setId(rs.getInt("id"));
                category.setType(rs.getString("type"));
                
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return category;
    }

    public CategoryModel addCategory(CategoryModel category) {
        Conexion conex = new Conexion();
        String Sql = "INSERT INTO category(id,type)";
        Sql = Sql + "values (?,?)";

        try {
            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, category.getId());
            pstm.setString(2, category.getType());
            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return category;
    }

    public CategoryModel updateCategory(CategoryModel category) {
        Conexion conn = new Conexion();
        String sql = "UPDATE category SET type=? WHERE id= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            pstm.setString(1, category.getType());
            pstm.setInt(2, category.getId());
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al actualizar  " + excepcion.getMessage());
            return null;
        }
        return category;
    }

    public String delCategory(int id) {
        Conexion conn = new Conexion();

        String sql = "DELETE FROM category WHERE id= ?";
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
