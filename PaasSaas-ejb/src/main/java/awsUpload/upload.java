package awsUpload;




import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;


/**
 * Session Bean implementation class upload
 */
@Stateless
@LocalBean
public class upload implements uploadRemote, uploadLocal {


    static javax.ws.rs.client.Client c= ClientBuilder.newClient();
	AWSCredentials Creadendials =new BasicAWSCredentials("AKIAIXT2WGXMUQFQ5V2Q","VWGkEbZlZbmKG1sctYQT9bgqD1t5bL+OXYAQ8L/N	");
	final AmazonS3 s3=AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(Creadendials)).withRegion(Regions.EU_WEST_1).build();
	
	public void uploadfile(String bac , File f)
	{
		
		s3.putObject(bac,"a.txt", f);
	//	AmazonS3 s3client=new AmazonS3Client(credentials);
		//try{
			//File file=new File(uploadfilename);
			//s3client.putObject(new PutObjectRequest(backetname,keyname,file));
	//	}catch(AmazonServiceException ase)
		//{
			//System.out.println(ase.getMessage());
	//	}
		
	}
	public List<String> ListFile(String name)
	{
		List<String > l=new ArrayList<String>();
		ObjectListing ol=s3.listObjects(name);
		List<S3ObjectSummary> object=ol.getObjectSummaries();
		for(S3ObjectSummary os:object)
		{
			l.add(os.getKey());
		//	System.out.p
		}
		return l;
	}
	public String getByUser(){
		
		String lr = c.target("http://localhost:13515/api/ApiBuckets/GetByU?id="+6).request().get().readEntity(String.class);
		return lr;
	}
	public void listBackets()
	{
		
		//List<Bucket> buckets=s3.listBuckets();
		//for(Bucket b:buckets)
		//{
		//	System.out.println(b.getName());
	//	}
		
	}
	public void createBucket(String name,String s)
	{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:13515/api/ApiBuckets/Create");
		Invocation.Builder invocationBuilder = target.request();
		Response response = invocationBuilder.post(Entity.entity(s, MediaType.APPLICATION_JSON));
		s3.createBucket(name);

	}
}
