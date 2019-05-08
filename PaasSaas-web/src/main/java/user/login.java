package user;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;


import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import UserService.UserRemote;

@ManagedBean
@RequestScoped
public class login {
	@EJB
	private UserRemote ur;
private String login ;
private String mdp ;
public static int idU ;
	@GET
	
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public String loginn()
	{
		String navigateTo="";
		String lr=ur.login(login,mdp);
		JSONObject js=new JSONObject(lr);
		String s=js.get("Id").toString();
		idU=Integer.parseInt(s);
		System.out.println(s);

		if(lr.equals(null))
		{
			
			 navigateTo = "/home/login?faces-redirect=true";
			
		}
		else{
			 navigateTo = "/home/log?faces-redirect=true";
		}
	
	    return navigateTo;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public static int getIdU() {
		return idU;
	}

	
}
