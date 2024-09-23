package fr.jypast.parisjanitorapi.domain.functionnal.service.files;

import com.azure.identity.ClientSecretCredentialBuilder;
 import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Component
public class FilesManagementService implements FilesManagement{
	private static final Logger LOGGER = LoggerFactory.getLogger(FilesManagementService.class);
	
	BlobServiceClient blobServiceClient;
	
	@PostConstruct
	public void init() {
		blobServiceClient = new BlobServiceClientBuilder()
									.endpoint("https://esgipa.blob.core.windows.net/")
									.credential(new ClientSecretCredentialBuilder()
														.tenantId("851bef4a-6c51-445b-812f-9dd619bedeb7")
														.clientId("0ed872a4-42b7-4294-b972-b22b0cae0dcb")
														.clientSecret("Kk58Q~HuqIcMV6lf9h16MBaLPHGdw2p2AgJhkais").build())
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
		Arrays.asList(files).stream().forEach(file -> {
			String fileName = file.getName();
			try {
				BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
				var client = containerClient.getBlobClient(fileName);
				client.upload(file.getInputStream());
			} catch(Exception e){
				LOGGER.error(e.getMessage());
			}
		});
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
