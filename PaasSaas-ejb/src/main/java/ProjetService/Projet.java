package ProjetService;

import java.io.OutputStream;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Session Bean implementation class Project
 */
@Stateless
@LocalBean
public class Projet implements ProjetRemote, ProjetLocal {
	@PersistenceContext(unitName = "PaasSaas-ejb")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public Projet() {
        // TODO Auto-generated constructor stub
    }
    static javax.ws.rs.client.Client c= ClientBuilder.newClient();
    //set the appropriate URL
	static String getUrl = "http://localhost:21514/api/ProjetApiController/GetAll";
	
	static String getUrll = "http://localhost:21514/api/ProjetApiController/GetDelivredProjects";

	public String getAll(){
		
		String lr = c.target(getUrl).request().get().readEntity(String.class);
		return lr;
	}
public String getProjectDelivred(){
		
		String lr = c.target(getUrll).request().get().readEntity(String.class);
		return lr;
	}
	@Override
	public String Add(String s )
	{
	
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:21514/api/ProjetApiController/Create");
		Invocation.Builder invocationBuilder = target.request();
		Response Projet = invocationBuilder.post(Entity.entity(s, MediaType.APPLICATION_JSON));
		return Projet.readEntity(String.class);
	}
	
	
	
	
	
}
