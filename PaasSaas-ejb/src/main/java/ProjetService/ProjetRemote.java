package ProjetService;

import javax.ejb.Remote;

@Remote
public interface ProjetRemote {
	public String getAll();
	public String Add(String s );
	public String getProjectDelivred();

}
