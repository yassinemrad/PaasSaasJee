package suggestion;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

import org.primefaces.event.RateEvent;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import RatingService.RatingrespRemote;

@ManagedBean
public class RatingView {
     
      
    private Integer rating3;   
    private Integer rating4 = 3;
     
    public void onrate(RateEvent rateEvent) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Application", "You rated:" + ((Integer) rateEvent.getRating()).intValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
    public void oncancel() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancel Event", "Rate Reset");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
 
  
 
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

 
    public Integer getRating3() {
        return rating3;
    }
 
    public void setRating3(Integer rating3) {
        this.rating3 = rating3;
    }
 
 
    @EJB
	private RatingrespRemote rating ;
	private int nbrating ;
	private String username;
	
	@POST
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public String add()
	{
		 String jsonString = new JSONObject()
                 .put("nbrating", this.rating3)
                 .put("UserName", username)
                
                 .toString();
		rating.Add(jsonString);
		System.out.println(jsonString);
		return  "home?faces-redirect=true";
		 //rec.getByUser();
	}

@GET
	
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public int getAll(){
		
		int lr= rating.getAll();		       
               return lr;
		
	}
}