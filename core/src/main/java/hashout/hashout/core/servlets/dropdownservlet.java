package hashout.hashout.core.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.osgi.framework.Constants;
import org.osgi. service.component.annotations.Component;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.EmptyDataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;



@Component(
        service = Servlet.class,
        property = {
                Constants.SERVICE_DESCRIPTION + "= Dynamic Drop Down",
                "sling.servlet.paths=" + "/bin/availableThemes"
        })
public class dropdownservlet extends SlingSafeMethodsServlet {
 private static final long serialVersionUID = 1668099305241096740L;
 @Override
 protected void doGet(SlingHttpServletRequest request,
   SlingHttpServletResponse response) throws ServletException,
   IOException {
  List<Resource> themes = new ArrayList<Resource>();
  // set fallback
  ResourceResolver resolver = request.getResourceResolver();
  request.setAttribute(DataSource.class.getName(),
    EmptyDataSource.instance());
  ValueMap vm = null;
  for (int i = 0; i < 5; i++) {
   // allocate memory to the Map instance
   vm = new ValueMapDecorator(new HashMap<String, Object>());
  // Specify the value and text values
   String Value = "value" + i;
   String Text = "text" + i;
   // populate the map
   vm.put("value", Value);
   vm.put("text", Text);
   themes.add(new ValueMapResource(resolver, new ResourceMetadata(),
     "nt:unstructured", vm));
  }
  DataSource dataSource = new SimpleDataSource(themes.iterator());
  request.setAttribute(DataSource.class.getName(), dataSource);
 }
}
