package reclamation;



import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;


import ReclamationService.responseRemote;
import user.login;

@ManagedBean
@RequestScoped

public class Reclamation {
	private String object ;
	private String description ;
	public int id ;
  
	
	@EJB
	private responseRemote rec ;

	
	Response j;
	

	@GET
	
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Object> getNT(){
		
		String lr= rec.getNonTreat();		       
		JSONArray  array = new JSONArray(lr);
		ArrayList<Object> al=new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            al.add(array.get(i));
      
        }
		return al;
	}
	@GET
	
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Object> getT(){
		
		String lr= rec.getTreat();		       
		JSONArray  array = new JSONArray(lr);
		ArrayList<Object> al=new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            al.add(array.get(i));
        
        }
		return al;
	}
	@GET
	
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Object> getByUser(){
		
		String lr= rec.getByUser(login.idU);	
		//System.out.println("aaaaaaaaaaa"+lr);
        JSONArray array = new JSONArray(lr);
    	ArrayList<Object> al=new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
        	  al.add(array.get(i));
        }
        return al;
	}
	@POST
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public String add()
	{
		System.out.println("reclamation="+login.idU);
		 String jsonString = new JSONObject()
                 .put("objet", object)
                 .put("description", description)
                 .put("user",login.idU).toString();
		rec.Add(jsonString);
		 //rec.getByUser();
		String navigateTo = "myReclamations?faces-redirect=true";
		return navigateTo;
	}


	@DELETE
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public void delete(int id)
	{
		rec.Delete(id);
	}
	
	@PUT
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public void treat(int id)
	{
		 String jsonString = new JSONObject()
                 .put("id", id)
                .toString();
		rec.Treat(jsonString);
		 //rec.getByUser();
	}
	
	
	@GET
	
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public Object getUSerById(int id){
		
		String lr= rec.getUserById(id)		 ;      
		//JSONArray  array = new JSONArray(lr);
		//ArrayList<Object> al=new ArrayList<Object>();
		JSONObject js=new JSONObject(lr);
      
		return js;
	}
	public String redirectOld()
	{
		String navigateTo = "oldReclamation?faces-redirect=true";
		return navigateTo;
	}
	public String redirectNew()
	{
		String navigateTo = "display?faces-redirect=true";
		return navigateTo;
	}
	public String redirectCreate()
	{
		System.out.println("hany nod5ol");
		String navigateTo = "add?faces-redirect=true";
		return navigateTo;
	}
	public String redirectMyRec()
	{
		String navigateTo = "myReclamations?faces-redirect=true";
		return navigateTo;
	}
	public String redirectList()
	{
		String navigateTo="";
		if(login.role.equals("Admin"))
		{
		 navigateTo = "../Reclmation/display.jsf?faces-redirect=true";
		}else{
			 navigateTo = "../Reclmation/myReclamations.jsf?faces-redirect=true";

		}
		return navigateTo;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}



}
