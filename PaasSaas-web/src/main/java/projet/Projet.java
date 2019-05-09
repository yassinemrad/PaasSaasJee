package projet;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;
import org.xhtmlrenderer.pdf.ITextRenderer;

import ProjetService.ProjetRemote;

@ManagedBean
@RequestScoped

public class Projet {
	private String name;
	private String startDate ;
	private String endDate ;
	private String description;
	private int budget;
	static int x;
	static int pp;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss");

	//Date d = sdf.parse(startDate);
    //Date d2=dateFormat.parse(endDate);

	@EJB
	private ProjetRemote rec ;

	
	Projet j;
	
	
	
	@GET
	
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Object> getNT(){
		
		String lr= rec.getAll();		       
		JSONArray  array = new JSONArray(lr);
		ArrayList<Object> al=new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            al.add(array.get(i));
             x =al.size();

        	//JSONObject obj =array.getJSONObject(i);
        //	System.out.println(obj.getString("description"));
       // 	System.out.println(obj.getInt("id"));
        }

		return al;

	}
@GET
	
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Object> getP(){
		
		String lrr= rec.getProjectDelivred();		       
		JSONArray  array = new JSONArray(lrr);
		ArrayList<Object> al=new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            al.add(array.get(i));
             pp =al.size();

        	//JSONObject obj =array.getJSONObject(i);
        //	System.out.println(obj.getString("description"));
       // 	System.out.println(obj.getInt("id"));
        }

		return al;

	}
	
	@POST
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public void add() throws ParseException
	{ int test =0;
		 String jsonString = new JSONObject()
                 .put("name", name)
                 .put("description", description)
                 .put("startDate", startDate)
                 .put("endDate", endDate)
                 .put("budget", budget)

                 .toString();
		 JSONObject jsonObject = new JSONObject(jsonString);
		 String endDate = jsonObject.getString("endDate");
		 System.out.println(endDate);
		 test = calcule_date(endDate);
		 System.out.println(test);
		 FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage("thi project will last "+test+" days."));
		rec.Add(jsonString);
		 //rec.getByUser();
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public int getPp() {
		return pp;
	}
	public void setPp(int pp) {
		this.pp = pp;
	}
	public String redirectList()
	{	String 	 navigateTo = "displayProjet?faces-redirect=true";

		return navigateTo;
	}
	
	public void createPDF(){
		   //report.setEventId(eventId);
		   
			FacesContext facesContext = FacesContext.getCurrentInstance();
		    ExternalContext externalContext = facesContext.getExternalContext();
		    HttpSession session = (HttpSession) externalContext.getSession(true);
		    String url = "http://localhost:18080/PaasSaas-web/Projet/reports.xhtml;JSESSIONID="+session.getId()+"pdf=true";
		    try {
		    ITextRenderer renderer = new ITextRenderer();
		    renderer.setDocument(url);
		    renderer.layout();
		    HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		    response.reset();
		    response.setContentType("application/pdf");
		    response.setHeader("Content-Disposition","C://user//first.pdf");
		    OutputStream browserStream = response.getOutputStream();
		    renderer.createPDF(browserStream);
		    browserStream.close();
		    session.invalidate();
		    } catch (Exception ex) {
		       ex.printStackTrace();
		    }
		    facesContext.responseComplete();
		    
	}

	public  int calcule_date(String endDate) throws ParseException  {
	    
	    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    //String startDate ;
	    Date today = new Date();
		System.out.println(dateFormat.format(today));
	 
	    long nbDaysFirstDate=0;
	    long nbDaysSecondDate=0;
	 
	 
	    Date d1= today;
	    Date d2=dateFormat.parse(endDate);
	    long DayInMillisecond=24*60*60*1000;
	    nbDaysFirstDate=d1.getTime()/DayInMillisecond;
	    nbDaysSecondDate=d2.getTime()/DayInMillisecond;
	 
	   String NBjour=Long.toString(nbDaysSecondDate-nbDaysFirstDate);
	   return Integer.parseInt(NBjour);     
	}
}
