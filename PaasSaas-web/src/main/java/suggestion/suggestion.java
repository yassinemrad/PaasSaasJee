package suggestion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import StatService.StatRespRemote;
import SugestionService.Sugestresp;
import SugestionService.SugestrespRemote;

@ManagedBean
@RequestScoped
public class suggestion {
	//public enum color{ red,green,yellow}
	private String titre ;
	private String description;
	private String auteur ;
	private int priorite;
	private int id;
	public SugestrespRemote getSug() {
		return sug;
	}

	public int getPriorite() {
		return priorite;
	}

	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}

	public void setSug(SugestrespRemote sug) {
		this.sug = sug;
	}

	@EJB
	private SugestrespRemote sug ;
	@EJB
	private StatRespRemote stat;

	
	Sugestresp j;
	
	@POST
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public void add()
	{
		 String jsonString = new JSONObject()
                 .put("titre", titre)
                 .put("description", description)
                 .put("auteur", auteur)
                 .put("priorite", priorite)
                 .toString();
		sug.Add(jsonString);
		System.out.println(jsonString);
		 //rec.getByUser();
	}

@GET
	
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Object> getAll(){
		
		String lr= sug.getAll();		       
        JSONArray array = new JSONArray(lr);
        ArrayList<Object> listdata = new ArrayList<Object>();     
        
        if (array != null) { 
           for (int i=0;i<array.length();i++){ 
            listdata.add(array.get(i));
        //    System.out.println(array.get(i));
           } 
        }
      
       // 	System.out.println(array);
        	//System.out.println(obj.getInt("id"));
        return listdata;
		
	}
@PUT
@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
public void update()
{

	 String jsonString = new JSONObject()
            .put("titre", titre)
            .put("description", description).put("auteur", auteur)
            .put("priorite", priorite).put("Idsug",id)
            .toString();
	sug.Update(jsonString);
}

@PUT
@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
public String Vote(int idSug)
{
	 System.out.println("Vote try");

	 String jsonString = new JSONObject()
            .put("Idsug",(int) idSug)
            .toString();
	sug.Votes(jsonString);
	return "Panelsugst?faces-redirect=true";
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getTitre() {
	return titre;
}

public void setTitre(String titre) {
	this.titre = titre;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getAuteur() {
	return auteur;
}

public void setAuteur(String auteur) {
	this.auteur = auteur;
}

@GET
@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
public ArrayList<Object> TaskesById(){
	
	String lr= stat.getAlls(5);		       
    JSONArray array = new JSONArray(lr);
    ArrayList<Object> listdata = new ArrayList<Object>();     
    
    if (array != null) { 
       for (int i=0;i<array.length();i++){ 
        listdata.add(array.get(i));
        System.out.println(array.get(i));
       } 
    }
  
   // 	System.out.println(array);
    	//System.out.println(obj.getInt("id"));
    return listdata;
	
}
@GET
@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
public ArrayList<Object> proj(){
	
	String lr= stat.getAllp();		       
    JSONArray array = new JSONArray(lr);
    ArrayList<Object> listdata = new ArrayList<Object>();     
    
    if (array != null) { 
       for (int i=0;i<array.length();i++){ 
        listdata.add(array.get(i));
        System.out.println(array.get(i));
       } 
    }
  
   // 	System.out.println(array);
    	//System.out.println(obj.getInt("id"));
    return listdata;
	
}

public StatRespRemote getStat() {
	return stat;
}

public void setStat(StatRespRemote stat) {
	this.stat = stat;
}

}
