package fr.jypast.parisjanitorapi.client.controller;

import com.azure.core.credential.AzureSasCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/files")
public class FilesController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FilesController.class);
	
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
	@GetMapping(("/create-directory/{name}"))
	@ResponseStatus(CREATED)
	public void createContainer(@PathVariable String name){
		try {
			blobServiceClient.createBlobContainer(name);
			LOGGER.error(String.format("Directory %s created", name ));
		} catch(Exception e){
			LOGGER.error(e.getMessage());
		}
	}
	
	@GetMapping(("/get-files-url/{container_name}"))
	@ResponseStatus(CREATED)
	public void addFileToContainer(MultipartFile file, @PathVariable("container_name") String containerName){
		String fileName = file.getName();
		try {
			BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
			var client = containerClient.getBlobClient(fileName);
			client.upload(file.getInputStream());
		} catch(Exception e){
			LOGGER.error(e.getMessage());
		}
	}
}
