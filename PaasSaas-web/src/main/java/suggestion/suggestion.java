package suggestion;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import SugestionService.Sugestresp;
import SugestionService.SugestrespRemote;

@ManagedBean
@RequestScoped
public class suggestion {
	private String titre ;
	private String description;
	private String auteur ;
	public SugestrespRemote getSug() {
		return sug;
	}

	public void setSug(SugestrespRemote sug) {
		this.sug = sug;
	}

	@EJB
	private SugestrespRemote sug ;
	

	
	Sugestresp j;
	
	@POST
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public void add()
	{
		 String jsonString = new JSONObject()
                 .put("titre", titre)
                 .put("description", description)
                 .put("auteur", auteur)
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
            System.out.println(array.get(i));
           } 
        }
      
       // 	System.out.println(array);
        	//System.out.println(obj.getInt("id"));
        return listdata;
		
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



}
