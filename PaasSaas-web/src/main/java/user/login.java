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
public static String role ;
	@GET
	
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public String loginn()
	{
		String navigateTo="";
		String lr=ur.login(login,mdp);
		//System.out.println("user"+lr);
		if(lr.equals("null"))
		{
			 navigateTo = "log?faces-redirect=true";
			

		}else{
		JSONObject js=new JSONObject(lr);
	//	System.out.println("user"+lr);

		String s=js.get("Id").toString();
		idU=Integer.parseInt(s);
		role=js.get("Role").toString();
		System.out.println("role="+role);
		//System.out.println("id"+s);
		
		
			 navigateTo = "home?faces-redirect=true";
			
		
			
		}
	
	    return navigateTo;
	}
	public String redirectLogin()
	{
		String navigateTo = "log?faces-redirect=true";
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
