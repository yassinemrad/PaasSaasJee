package suggestion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.json.JsonArray;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import StatService.StatRespRemote;
import SugestionService.Sugestresp;
import SugestionService.SugestrespRemote;
import user.login;

@ManagedBean
@RequestScoped
public class suggestion {
	//public enum color{ red,green,yellow}
	private String titre ;
	private String description;
	private String auteur ;
	private int priorite;
	private int id;
	private int idproject = 5;
	private int i;
	public int getIdproject() {
		return idproject;
	}

	public void setIdproject(int idproject) {
		this.idproject = idproject;
	}

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
	public String add()
	{
		if((titre == null)||(description ==null)||(auteur== null))
		{
			return "Suggestionform?faces-redirect=true";
			}else{
			String jsonString = new JSONObject()

					.put("titre", titre)
					.put("description", description)
					.put("auteur", auteur)
					.put("priorite", priorite)
					.toString();
			sug.Add(jsonString);
			System.out.println(jsonString);
			return "Panelsugst?faces-redirect=true";
		}	 //rec.getByUser();




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

	@PUT
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public String etat(int id)
	{
	//	System.out.println("Vote try");

		String jsonString = new JSONObject()
				.put("Idsug", id)
				.toString();
		sug.etat(jsonString);
		return "listadmin?faces-redirect=true";
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
	public ArrayList<Object> TaskesById() throws ParseException{

		String lr= stat.getAlls(idproject);		 
		JSONArray array = new JSONArray(lr);
		ArrayList<Object> listdata = new ArrayList<Object>();     

		if (array != null) { 
			for (int i=0;i<array.length();i++){ 
				JSONObject jsob = new JSONObject(array.get(i).toString());
				System.out.println("this is JSOB : "+jsob);

				String dt1 =jsob.get("startDate").toString();
				dt1 = dt1.substring(0, 10);
				SimpleDateFormat dtf1 = new SimpleDateFormat("yyyyy-mm-dd"); 
				Date date = dtf1.parse(dt1);
				SimpleDateFormat dtf2 = new SimpleDateFormat("dd-mm-yyyy");

				jsob.put("startDate", dtf2.format(date));
				listdata.add(array.get(i));

			} 
		}
		System.out.println("this  is list of task: "+listdata);

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

	@GET

	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public JSONObject projById() throws ParseException{
		String lr= stat.getprojbyid(idproject);    
		//JSONArray  array = new JSONArray(lr);
		//ArrayList<Object> al=new ArrayList<Object>();
		JSONObject js=new JSONObject(lr);
		String dt1 =js.get("startDate").toString();
		dt1 = dt1.substring(0, 10);
		SimpleDateFormat dtf1 = new SimpleDateFormat("yyyyy-mm-dd"); 
		Date date = dtf1.parse(dt1);
		SimpleDateFormat dtf2 = new SimpleDateFormat("dd-mm-yyyy");

		System.out.println(dt1);
		js.put("startDate", dtf2.format(date));
		System.out.println(js);

		return js;
	}

	@DELETE
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public String delete(int id)
	{
		sug.Deletee(id);
		return "Panelsugst?faces-redirect=true";

	}
	// 	System.out.println(array);
	//System.out.println(obj.getInt("id"));

//	public String redirectList()
//	{
//		String navigateTo="";
//		if(login.role.equals("Admin"))
//		{
//		 navigateTo = "../suggestion/listadmin?faces-redirect=true";
//		}else{
//			 navigateTo = "../suggestion/Panelsugst?faces-redirect=true";
//
//		}
//		return navigateTo;
//	}



	public StatRespRemote getStat() {
		return stat;
	}

	public void setStat(StatRespRemote stat) {
		this.stat = stat;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

}
