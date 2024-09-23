package fr.jypast.parisjanitorapi.client.controller;

import fr.jypast.parisjanitorapi.domain.functionnal.service.files.FilesManagement;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/files")
public class FilesController {
	private final FilesManagement filesManagement;
	private static final Logger LOGGER = LoggerFactory.getLogger(FilesController.class);
	
	@GetMapping("/pictures/property/list/{propertyReference}")
	@ResponseStatus(OK)
	public List<String> getUrlsForContainer(@PathVariable String propertyReference){
		LOGGER.info("Retrieving property pictures URLs");
		return filesManagement.getUrlsFromContainer(propertyReference);
	}
	
	@DeleteMapping("/pictures/property/delete/{propertyReference}/{fileName}")
	@ResponseStatus(OK)
	public void deletePictureFromProperty(@PathVariable String propertyReference, @PathVariable String fileName){
		LOGGER.info("Deleting property picture");
		filesManagement.deleteFileFromContainer(propertyReference,fileName);
	}
	
	@DeleteMapping("/pictures/profile/users/delete/{profileReference}/{fileName}")
	@ResponseStatus(OK)
	public void deletePictureFromProfile(@PathVariable String profileReference, @PathVariable String fileName){
		LOGGER.info("Deleting user profile picture");
		filesManagement.deleteFileFromContainer(profileReference,fileName);
	}
	
	@PutMapping("/pictures/profile/users/update-picture/{profileReference}")
	@ResponseStatus(OK)
	public void updateProfilePic(@PathVariable String profileReference, @RequestParam("file") MultipartFile[] files	){
		LOGGER.info("Updating user profile picture");
		filesManagement.updateProfilePicture(files,profileReference);
	}
	
	@PostMapping("/upload/profile/presta/upload-certification/{profileReference}")
	@ResponseStatus(OK)
	public void uploadCertificationForPerformer(@PathVariable String profileReference,
								  @RequestParam("file") MultipartFile[] files	){
		LOGGER.info("Updating user profile picture");
		filesManagement.updateProfilePicture(files,profileReference);
	}
}
