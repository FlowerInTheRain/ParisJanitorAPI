package fr.jypast.parisjanitorapi.domain.functionnal.service.files;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FilesManagement {
	void createContainer(String containerName);
	
	void addFilesToContainer(MultipartFile[] files, String containerName);
	void updateProfilePicture(MultipartFile[] files, String containerName);
	void deleteFileFromContainer(String containerName, String fileName);
	
	void deleteContainer(String containerName);
	List<String> getUrlsFromContainer(String containerName);
}
