package RatingService;

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
 * Session Bean implementation class Ratingresp
 */
@Stateless
@LocalBean
public class Ratingresp implements RatingrespRemote, RatingrespLocal {

    /**
     * Default constructor. 
     */
    public Ratingresp() {
        // TODO Auto-generated constructor stub
    }
    public String Add(String s )
   	{
   	
   		
   		Client client = ClientBuilder.newClient();
   		WebTarget target = client.target("http://localhost:13515/api/SugestApi/rate");
   		Invocation.Builder invocationBuilder = target.request();
   		Response response = invocationBuilder.post(Entity.entity(s, MediaType.APPLICATION_JSON));
   		return response.readEntity(String.class);
   	}
       static javax.ws.rs.client.Client c= ClientBuilder.newClient();
       //set the appropriate URL
   	static String getUrl = "http://localhost:13515/api/SugestApi/GetAllrat";
   	
   	
   	public int getAll(){
   		
   		String lr = c.target(getUrl).request().get().readEntity(String.class);
  int count=Integer.parseInt(lr);
   		return count;
   	}

}
