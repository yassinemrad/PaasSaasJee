package SugestionService;

import javax.ejb.Remote;

@Remote
public interface SugestrespRemote {
	 public String Add(String s );
	 public String getAll();
}
