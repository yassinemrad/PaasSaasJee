package SugestionService;

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
 * Session Bean implementation class Sugestresp
 */
@Stateless
@LocalBean
public class Sugestresp implements SugestrespRemote, SugestrespLocal {

    /**
     * Default constructor. 
     */
    public Sugestresp() {
        // TODO Auto-generated constructor stub
    }
    public String Add(String s )
	{
	
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:13515/api/SugestApi/Create");
		Invocation.Builder invocationBuilder = target.request();
		Response response = invocationBuilder.post(Entity.entity(s, MediaType.APPLICATION_JSON));
		return response.readEntity(String.class);
	}
    static javax.ws.rs.client.Client c= ClientBuilder.newClient();
    //set the appropriate URL
	static String getUrl = "http://localhost:13515/api/SugestApi/GetAll";
	
	
	public String getAll(){
		
		String lr = c.target(getUrl).request().get().readEntity(String.class);
		return lr;
	}
	
	public String Update(String s )
	{
	
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:13515/api/SugestApi/Update");
		Invocation.Builder invocationBuilder = target.request();
		Response response = invocationBuilder.put(Entity.entity(s, MediaType.APPLICATION_JSON));
		return response.readEntity(String.class);
	}
	public String Votes(String s )
	{
	
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:13515/api/SugestApi/Vote");
		Invocation.Builder invocationBuilder = target.request();
		Response response = invocationBuilder.put(Entity.entity(s, MediaType.APPLICATION_JSON));
		return response.readEntity(String.class);
	}
	
	public String Deletee (int id)
	{
	
		String lr = c.target("http://localhost:13515/api/SugestApi/Delete?id="+id).request().delete().readEntity(String.class);
		return lr;
	
	}
	
	
}
