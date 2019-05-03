package RatingService;

import javax.ejb.Remote;

@Remote
public interface RatingrespRemote {
	 public String Add(String s );
	 public int getAll();
}
