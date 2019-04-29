package ReclamationService;

import javax.ejb.Remote;





@Remote
public interface responseRemote {
	public String getAll();
	public String Add(String s );
	public String getByUser();}
