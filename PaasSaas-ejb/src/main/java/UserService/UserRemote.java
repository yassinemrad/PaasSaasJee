package UserService;

import javax.ejb.Remote;

@Remote
public interface UserRemote {
	 public String login(String l , String p);
}
