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
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;


/**
 * Session Bean implementation class upload
 */
@Stateless
@LocalBean
public class upload implements uploadRemote, uploadLocal {


    static javax.ws.rs.client.Client c= ClientBuilder.newClient();
	AWSCredentials Creadendials =new BasicAWSCredentials("","");
	final AmazonS3 s3=AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(Creadendials)).withRegion(Regions.EU_WEST_1).build();
	
	public void uploadfile(String bac , File f , String nom)
	{
		
		s3.putObject(bac,nom, f);
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
	public String getByUser(int id){
		
		String lr = c.target("http://arcticjeeapi.eu-west-1.elasticbeanstalk.com/api/ApiBuckets/GetByU?id="+id).request().get().readEntity(String.class);
		return lr;
	}
	public List<String> listBackets()
	{
		List<String> lb=new ArrayList<String>();
		List<Bucket> buckets=s3.listBuckets();
		for(Bucket b:buckets)
		{
			lb.add(b.getName());
		//	System.out.println(b.getName());
		}
		return lb ;
	}
	public void createBucket(String name,String s)
	{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://arcticjeeapi.eu-west-1.elasticbeanstalk.com/api/ApiBuckets/Create");
		Invocation.Builder invocationBuilder = target.request();
		Response response = invocationBuilder.post(Entity.entity(s, MediaType.APPLICATION_JSON));
		s3.createBucket(name);

	}
	public void deleteFile(String bucket , String file)
	{
		s3.deleteObject(bucket,file);
	}
}
