package TaskService;

import javax.ejb.Remote;

@Remote
public interface TaskresponseRemote {
	public String getAll();
	public String getAllM();
	public String Add(String s );
	public String getTaskById(int id);
	public String Delete (int id  );
	public String Update(String s );	
	public String getUserById(int id);
	}

