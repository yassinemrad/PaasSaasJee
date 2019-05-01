package aws;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;
import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;
import org.primefaces.model.UploadedFile;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import awsUpload.uploadRemote;
@ManagedBean
@RequestScoped

public class Awsbucket {
	
private Part   uf ;	
private String Backet ;
private List<String> ListeFile ;
	@EJB
	private uploadRemote u ;

public void list()
{
	u.listBackets();
}
public void addBacket()
{
	String jsonString = new JSONObject()
            .put("name", Backet)
            
            .put("user", 6).toString();
	u.createBucket(Backet, jsonString);
}

@GET

@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
public ArrayList<Object> getliste(){
	
	String lr= u.getByUser();	       
	JSONArray  array = new JSONArray(lr);
	ArrayList<Object> al=new ArrayList<Object>();
    for (int i = 0; i < array.length(); i++) {
        al.add(array.get(i));
    
    }
	return al;
}

public  List<String> getFile()
{
	System.out.println(Backet);
	return u.ListFile("javaeearctictest");
}
public String redirectlistFile(String name)
{

		Backet =name;

	
	return "AddFile?faces-redirect=true";
		
}
public void addFile() throws IOException
{
	  
	File f=new File(uf.getSubmittedFileName());
	u.uploadfile("javaeearctictest", f);
}
public String redirectAddBacket()
{
	return "AddBacket?faces-redirect=true";
}

public Part  getUf() {
	return uf;
}
public void setUf(Part  uf) {
	this.uf = uf;
}
public String getBacket() {
	return Backet;
}
public void setBacket(String backet) {
	Backet = backet;
}
public List<String> getListeFile() {
	return ListeFile;
}
public void setListeFile(List<String> listeFile) {
	ListeFile = listeFile;
}


}
