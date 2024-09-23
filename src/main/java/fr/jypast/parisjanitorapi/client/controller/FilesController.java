package fr.jypast.parisjanitorapi.client.controller;

import com.azure.storage.blob.BlobContainerClient;
import fr.jypast.parisjanitorapi.domain.functionnal.service.files.FilesManagement;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/files")
public class FilesController {
	private final FilesManagement filesManagement;
	private static final Logger LOGGER = LoggerFactory.getLogger(FilesController.class);
	
	@GetMapping("/pictures/property/list/{propertyReference}")
	@ResponseStatus(OK)
	public List<String> createContainer(@PathVariable String propertyReference){
		return filesManagement.getUrlsFromContainer(propertyReference);
	}
	
	@DeleteMapping("/pictures/property/delete/{propertyReference}/{fileName}")
	@ResponseStatus(OK)
	public void deletePictureFromProperty(@PathVariable String propertyReference, @PathVariable String fileName){
		filesManagement.deleteFileFromContainer(propertyReference,fileName);
	}
	
	@DeleteMapping("/pictures/profile/delete/{profileReference}/{fileName}")
	@ResponseStatus(OK)
	public void deletePictureFromProfile(@PathVariable String profileReference, @PathVariable String fileName){
		filesManagement.deleteFileFromContainer(profileReference,fileName);
	}
	
	@PutMapping("/pictures/profile/update-picture/{profileReference}")
	@ResponseStatus(OK)
	public void updateProfilePic(@PathVariable String profileReference, @RequestParam("file") MultipartFile[] files	){
		filesManagement.updateProfilePicture(files,profileReference);
	}
}
