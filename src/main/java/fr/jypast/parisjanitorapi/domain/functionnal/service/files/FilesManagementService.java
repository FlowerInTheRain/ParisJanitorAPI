package fr.jypast.parisjanitorapi.domain.functionnal.service.files;

import com.azure.identity.ClientSecretCredentialBuilder;
 import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Component
public class FilesManagementService implements FilesManagement{
	private static final Logger LOGGER = LoggerFactory.getLogger(FilesManagementService.class);
	BlobServiceClient blobServiceClient;
	@Value("azure.client-id")
	private String clientId;
	@Value("azure.tenant-id")
	private String tenantId;
	@Value("azure.client-value")
	private String clientSecret;
	

	@PostConstruct
	public void init() {
		blobServiceClient = new BlobServiceClientBuilder()
									.endpoint("https://esgipa.blob.core.windows.net/")
									.credential(new ClientSecretCredentialBuilder()
														.tenantId(tenantId)
														.clientId(clientId)
														.clientSecret(clientSecret).build())
									.buildClient();
	}
	
	@Override
	public void createContainer(String containerName){
		try {
			blobServiceClient.createBlobContainer(containerName);
			LOGGER.error(String.format("Directory %s created", containerName ));
		} catch(Exception e){
			LOGGER.error(e.getMessage());
		}
	}
	
	@Override
	public void addFilesToContainer(MultipartFile[] files, String containerName) {
		Arrays.stream(files).forEach(file -> {
			String fileName = file.getName();
			if(file.getContentType() != null && (file.getContentType().equals(MediaType.IMAGE_JPEG_VALUE) || file.getContentType().equals(MediaType.IMAGE_PNG_VALUE))){
				try {
					BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
					var client = containerClient.getBlobClient(fileName);
					client.upload(file.getInputStream());
				} catch(Exception e){
					LOGGER.error(e.getMessage());
				}
			}
		});
	}
	
	@Override
	public void updateProfilePicture(MultipartFile[] files, String containerName) {
		BlobContainerClient containerClient = blobServiceClient
													  .getBlobContainerClient(containerName);
		containerClient.listBlobs().stream().forEach(blob -> {
			containerClient.getBlobClient(blob.getName()).delete();
		});
		addFilesToContainer(files,containerName);
	}
	
	@Override
	public void deleteFileFromContainer(String containerName, String fileName) {
		BlobContainerClient containerClient = blobServiceClient
													  .getBlobContainerClient(containerName);
		try {
			containerClient.getBlobClient(fileName).delete();
		} catch(Exception e){
			LOGGER.error("Blob does not exist");
		}
	}
	
	@Override
	public void deleteContainer(String containerName) {
		try {
			blobServiceClient.getBlobContainerClient(containerName).delete();
		} catch(Exception e){
			LOGGER.error(e.getMessage());
		}
	}
	
	@Override
	public List<String> getUrlsFromContainer(String containerName) {
		BlobContainerClient containerClient = blobServiceClient
													  .getBlobContainerClient(containerName);
		try {
			return containerClient.listBlobs().stream().map(item -> String.format("https://esgipa.blob.core.windows" +
																						  ".net/%s/%s",
																				  containerName,
																				  item.getName())).toList();
		} catch(Exception e){
			LOGGER.error("Blob does not exist");
			throw new RuntimeException();
		}
	}
}
