package StatService;

import javax.ejb.Remote;

@Remote
public interface StatRespRemote {
	public String getAllStat();
	public String getBytask(int id);
	public String getprogtodo(int id);
	public String getprogdone(int id);
	public String getprogdoing(int id);
	public String getAlls(int id);
	public String getAllp();
	public String getprojbyid(int id);
}
