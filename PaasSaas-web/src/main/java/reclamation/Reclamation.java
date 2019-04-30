package reclamation;



import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;


import ReclamationService.responseRemote;

@ManagedBean
@RequestScoped

public class Reclamation {
	private String object ;
	private String description ;
  
	
	@EJB
	private responseRemote rec ;

	
	Response j;
	

	@GET
	
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Object> getAll(){
		
		String lr= rec.getAll();		       
		JSONArray  array = new JSONArray(lr);
		ArrayList<Object> al=new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            al.add(array.get(i));
        	//JSONObject obj =array.getJSONObject(i);
        //	System.out.println(obj.getString("description"));
       // 	System.out.println(obj.getInt("id"));
        }
		return al;
	}
	@GET
	
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public void getByUser(){
		
		String lr= rec.getByUser();		       
        JSONArray array = new JSONArray(lr);
        for (int i = 0; i < array.length(); i++) {
            
        	JSONObject obj =array.getJSONObject(i);
        	System.out.println(obj.getString("description"));
        	System.out.println(obj.getInt("id"));
        }
		
	}
	@POST
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public void add()
	{
		 String jsonString = new JSONObject()
                 .put("objet", object)
                 .put("description", description)
                 .put("user", 6).toString();
		rec.Add(jsonString);
		 //rec.getByUser();
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



}
