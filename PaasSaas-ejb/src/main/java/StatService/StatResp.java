package StatService;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.client.ClientBuilder;

/**
 * Session Bean implementation class StatResp
 */
@Stateless
@LocalBean
public class StatResp implements StatRespRemote, StatRespLocal {

    /**
     * Default constructor. 
     */
	
    public StatResp() {
        // TODO Auto-generated constructor stub
    }
    static javax.ws.rs.client.Client c= ClientBuilder.newClient();
    //set the appropriate URL
	static String getUrl = "http://localhost:13515/api/Statapi/dateuser";
	
	
	public String getAllStat(){
		
		String lr = c.target(getUrl).request().get().readEntity(String.class);
		return lr;
	}
	public String getBytask(int id){
		
		String lr = c.target("http://localhost:13515/api/StatApi?id="+id).request().get().readEntity(String.class);
		return lr;
	}
	
public String getprogtodo(int id){
		
		String lr = c.target("http://localhost:13515/api/StatApi/etatprogtodo?id="+id).request().get().readEntity(String.class);
		return lr;
	}
public String getprogdone(int id){
	
	String lr = c.target("http://localhost:13515/api/StatApi/etatprogdone?id="+id).request().get().readEntity(String.class);
	return lr;
}
public String getprogdoing(int id){
	
	String lr = c.target("http://localhost:13515/api/StatApi/etatprogdoing?id="+id).request().get().readEntity(String.class);
	return lr;
}




public String getAlls(int id){
	String lr = c.target("http://localhost:13515/api/Statapi/listt?id="+id).request().get().readEntity(String.class);
	return lr;
}
public String getAllp(){
	String lr = c.target("http://localhost:13515/api/Statapi/listp").request().get().readEntity(String.class);
	return lr;
}
}
