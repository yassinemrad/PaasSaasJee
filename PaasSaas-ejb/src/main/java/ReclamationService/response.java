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
	static String getUrl = "arcticjeeapi.eu-west-1.elasticbeanstalk.com/api/ApiReclamation/GetAll";
	
	
	public String getAll(){
		
		String lr = c.target(getUrl).request().get().readEntity(String.class);
		return lr;
	}
public String getTreat(){
		
		String lr = c.target("arcticjeeapi.eu-west-1.elasticbeanstalk.com/api/ApiReclamation/Traiter").request().get().readEntity(String.class);
		return lr;
	}
public String getUserById(int id){
	
	String lr = c.target("https://arcticjeeapi.eu-west-1.elasticbeanstalk.com/api/ApiUser/GetByid?id="+id).request().get().readEntity(String.class);
	return lr;
}
public String getNonTreat(){
	
	String lr = c.target("arcticjeeapi.eu-west-1.elasticbeanstalk.com/api/ApiReclamation/NonTraiter").request().get().readEntity(String.class);
	return lr;
}
public String getByUser(){
	
	String lr = c.target("arcticjeeapi.eu-west-1.elasticbeanstalk.com/api/ApiReclamation/GetByU?id="+6).request().get().readEntity(String.class);
	return lr;
}
	@Override
	public String Add(String s )
	{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("arcticjeeapi.eu-west-1.elasticbeanstalk.com/api/ApiReclamation/Create");
		Invocation.Builder invocationBuilder = target.request();
		Response response = invocationBuilder.post(Entity.entity(s, MediaType.APPLICATION_JSON));
		return response.readEntity(String.class);
	}
	@Override
	public String Update(String s )
	{
	
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("arcticjeeapi.eu-west-1.elasticbeanstalk.com/api/ApiReclamation/Update");
		Invocation.Builder invocationBuilder = target.request();
		Response response = invocationBuilder.put(Entity.entity(s, MediaType.APPLICATION_JSON));
		return response.readEntity(String.class);
	}
	@Override
	public String Delete (int id  )
	{
	
		String lr = c.target("arcticjeeapi.eu-west-1.elasticbeanstalk.com/api/ApiReclamation/Delete?id="+id).request().delete().readEntity(String.class);
		return lr;
	
	}
	@Override
	public String Treat (String s )
	{
	

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("arcticjeeapi.eu-west-1.elasticbeanstalk.com/api/ApiReclamation/Traitement");
		Invocation.Builder invocationBuilder = target.request();
		Response response = invocationBuilder.put(Entity.entity(s, MediaType.APPLICATION_JSON));
		return response.readEntity(String.class);
	}
}
