package reclamation;



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

public class test {
	@EJB
	private responseRemote rec ;

	
	Response j;
	

	@GET
	
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public void getAll(){
		
		String lr= rec.getAll();		       
        JSONArray array = new JSONArray(lr);
        for (int i = 0; i < array.length(); i++) {
            
        	JSONObject obj =array.getJSONObject(i);
        	System.out.println(obj.getString("description"));
        	System.out.println(obj.getInt("id"));
        }
		
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
                 .put("objet", "Hello World!")
                 .put("description", "Hello my World!")
                 .put("user", 6).toString();
		rec.Add(jsonString);
		 //rec.getByUser();
	}


}
