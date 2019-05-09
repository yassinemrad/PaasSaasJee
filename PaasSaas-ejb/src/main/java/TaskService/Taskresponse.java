package TaskService;

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
public class Taskresponse implements TaskresponseRemote, TaskresponseLocal {

    /**
     * Default constructor. 
     */
    public Taskresponse() {
        // TODO Auto-generated constructor stub
    }
    static javax.ws.rs.client.Client c= ClientBuilder.newClient();
    //set the appropriate URL
	static String getUrl = "http://localhost:21514/api/TasksApi/GetAll";
	
	
	public String getAll(){
		
		String lr = c.target(getUrl).request().get().readEntity(String.class);
		return lr;
	}
	static String getUrl1 = "http://localhost:21514/api/ModuleApi/GetAllM";
	
	
	public String getAllM(){
		
		String lr = c.target(getUrl1).request().get().readEntity(String.class);
		return lr;
	}

public String getTaskById(int id){
	
	String lr = c.target("http://localhost:21514/api/TasksApi/byid?id="+id).request().get().readEntity(String.class);
	return lr;
}
public String getUserById(int id){
	
	String lr = c.target("http://localhost:21514/api/TasksApi/Getbyid?id="+id).request().get().readEntity(String.class);
	return lr;
}

public String getByUser(){
	
	String lr = c.target("http://localhost:13515/api/ApiReclamation/GetByU?id="+6).request().get().readEntity(String.class);
	return lr;
}
	@Override
	public String Add(String s )
	{
	
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:21514/api/TasksApi/Ajout");
		Invocation.Builder invocationBuilder = target.request();
		Response response = invocationBuilder.post(Entity.entity(s, MediaType.APPLICATION_JSON));
		return response.readEntity(String.class);
	}
	@Override
	public String Update(String s )
	{
	
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:21514/api/TasksApi/change");
		Invocation.Builder invocationBuilder = target.request();
		Response response = invocationBuilder.put(Entity.entity(s, MediaType.APPLICATION_JSON));
		return response.readEntity(String.class);
	}
	@Override
	public String Delete (int id  )
	{
	
		String lr = c.target("http://localhost:21514/api/TasksApi/Delete?id="+id).request().delete().readEntity(String.class);
		return lr;
	
	}

}
