package tasks;



import java.security.Security;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Properties;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import com.amazonaws.services.ecs.model.Task;
import com.sun.mail.smtp.SMTPTransport;

import ReclamationService.responseRemote;
import TaskService.TaskresponseRemote;

@ManagedBean
@ApplicationScoped

public class Tasks {
	
	public int id ;
	private String name ;
	private String description ;
	private String date;
	@EJB
	private TaskresponseRemote tas ;
	private Object taskbyid;
	private String state ; 
	private String search  ;

	
	Response j;
	


@GET
	
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Object> getTasks(){
		
		String lr= tas.getAll();		       
		JSONArray  array = new JSONArray(lr);
		ArrayList<Object> al=new ArrayList<Object>();
		if(search==null || search.isEmpty()){
        for (int i = 0; i < array.length(); i++) {
            al.add(array.get(i));
      
        }}
		else{
			 for (int i = 0; i < array.length(); i++) {
				 JSONObject a =new JSONObject(array.get(i).toString());
				 if(a.get("name").toString().contains(search)){
					 al.add(array.get(i));
				 }
			 }
			
		}
		return al;
	}
	@GET
	
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Object> getModules(){
		
		String lr= tas.getAllM();		       
		JSONArray  array = new JSONArray(lr);
		ArrayList<Object> al=new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            al.add(array.get(i));
      
        }
		return al;
	}
@GET
	
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public String getTaskById(int id){
		
     	String lr= tas.getTaskById(id);	     
		JSONObject js=new JSONObject(lr);
		taskbyid=(Object)js; 
		state=js.get("etat").toString();
		
		this.getTaskbyid();
		return "getTaskById?faces-redirect=true";
	}
	
	@PUT
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public void changeStatus(int id)
	{

		 JSONObject jsonString = new JSONObject();
             jsonString.put("id",id);
             jsonString.put("etat",this.state);
             System.out.println(this.state);
                System.out.println(jsonString.toString());
		tas.Update(jsonString.toString());
	
	}
	public String updateTasks(int i,String o , String d,String b)
	{
		this.setId(i);
		this.setname(o);
		this.setDescription(d);
		this.setdate(b);
		return "update?faces-redirect=true";
	}
	
	@DELETE
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public void delete(int id)
	{
		tas.Delete(id);
	}
	public String redirectMyTask()
	{
		String navigateTo = "myTasks?faces-redirect=true";
		return navigateTo;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public String getdate() {
		return date;
	}
	public void setdate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	
	
	public void  sendMail() throws AddressException, MessagingException {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        /*
        If set to false, the QUIT command is sent and the connection is immediately closed. If set 
        to true (the default), causes the transport to wait for the response to the QUIT command.
        ref :   http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp/package-summary.html
                http://forum.java.sun.com/thread.jspa?threadID=5205249
                smtpsend.java - demo program from javamail
        */
		Session session = Session.getInstance(prop, new Authenticator() {
			// Set the account information session，transport will send mail
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("pi.dev1920@gmail.com", "98945544a");
			}
		});

		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("khaoula.nefziguessmi@esprit.tn"));
		message.setRecipients(
		  Message.RecipientType.TO, InternetAddress.parse("khaoula.nefziguessmi@esprit.tn"));
		message.setSubject("Remind");
		 
		String msg = "vous devez faire tache, vous étes en retard !Attention Accélérer !!!!";
		 
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(msg, "text/html");
		 
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);
		 
		message.setContent(multipart);
		 
		Transport.send(message);
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
	public Object getTaskbyid(){
		System.out.println(taskbyid);
		return this.taskbyid;
	}
	public void setTaskbyid(Object js){
		this.taskbyid=js;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
	



}
