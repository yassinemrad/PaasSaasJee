package ReclamationService;



import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;




/**
 * Session Bean implementation class response
 */
@Stateless
@LocalBean
public class response implements responseRemote, responseLocal {

    /**
     * Default constructor. 
     */
    public response() {
        // TODO Auto-generated constructor stub
    }
    static javax.ws.rs.client.Client c= ClientBuilder.newClient();
    //set the appropriate URL
	static String getUrl = "http://localhost:13515/api/ApiReclamation/GetAll";
	
	
	public String getAll(){
		
		String lr = c.target(getUrl).request().get().readEntity(String.class);
		return lr;
	}
public String getTreat(){
		
		String lr = c.target("http://localhost:13515/api/ApiReclamation/Traiter").request().get().readEntity(String.class);
		return lr;
	}
public String getByUser(){
	
	String lr = c.target("http://localhost:13515/api/ApiReclamation/GetByU?id="+2).request().get().readEntity(String.class);
	return lr;
}
	@Override
	public String Add(String s )
	{
	
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:13515/api/ApiReclamation/Create");
		Invocation.Builder invocationBuilder = target.request();
		Response response = invocationBuilder.post(Entity.entity(s, MediaType.APPLICATION_JSON));
		return response.readEntity(String.class);
	}
}