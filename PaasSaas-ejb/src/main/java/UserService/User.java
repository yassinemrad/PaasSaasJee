package UserService;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.client.ClientBuilder;

/**
 * Session Bean implementation class User
 */
@Stateless
@LocalBean
public class User implements UserRemote, UserLocal {
	  static javax.ws.rs.client.Client c= ClientBuilder.newClient();
    /**
     * Default constructor. 
     */
    public User() {
        // TODO Auto-generated constructor stub
    }
    
    public String login(String l , String p){
    	
    	String lr = c.target("http://dotnetapilogin.eu-west-1.elasticbeanstalk.com/api/ApiUser/login?l="+l+"&p="+p).request().get().readEntity(String.class);
    	return lr;
    }
}
