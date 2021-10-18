
package restful;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author JEAR
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(restful.GenericResource.class);
        resources.add(restful.Resource.CategoryResource.class);
        resources.add(restful.Resource.LoginResource.class);
        resources.add(restful.Resource.MovementResource.class);
        resources.add(restful.Resource.ProductResource.class);
        resources.add(restful.Resource.ProfileResource.class);
        resources.add(restful.Resource.TypeMovementResource.class);
        resources.add(restful.Resource.UserResources.class);
        resources.add(restful.Service.MovementService.class);
        resources.add(restful.Service.ProductService.class);
        resources.add(restful.Service.ProfileService.class);
        resources.add(restful.Service.TypeMovementService.class);
    }
    
}
