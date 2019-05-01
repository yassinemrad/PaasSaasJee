package awsUpload;

import java.io.File;
import java.util.List;

import javax.ejb.Remote;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@Remote
public interface uploadRemote {
	public void uploadfile(String bac , File f);
	 public void listBackets();
	 public void createBucket(String name,String s);
	 public String getByUser();
	 public List<String> ListFile(String name);
}
