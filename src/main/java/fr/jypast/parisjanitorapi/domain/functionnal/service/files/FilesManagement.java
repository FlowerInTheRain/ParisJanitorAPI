package fr.jypast.parisjanitorapi.domain.functionnal.service.files;

import com.azure.storage.blob.BlobContainerClient;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FilesManagement {
	void createContainer(String containerName);
	
	void addFilesToContainer(MultipartFile[] file, String containerName);
	
	public void deleteFileFromContainer(String containerName, String fileName);
	
	List<String> getUrlsFromContainer(String containerName);
}
