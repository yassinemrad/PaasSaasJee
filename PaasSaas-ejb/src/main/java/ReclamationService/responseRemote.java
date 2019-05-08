package ReclamationService;

import javax.ejb.Remote;





@Remote
public interface responseRemote {
	public String getAll();
	public String Add(String s );
	public String getByUser(int id);
	public String Delete (int id  );
	public String Update(String s );
	public String getTreat();
	public String getNonTreat();
	public String Treat (String s   );
	public String getUserById(int id);
	}
