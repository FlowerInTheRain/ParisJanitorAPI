package fr.jypast.parisjanitorapi.domain.port.in.files;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FilesManagementApi {
	void createContainer(String containerName);
	
	void addFilesToContainer(MultipartFile[] files, String containerName);
	void updateProfilePicture(MultipartFile[] files, String containerName);
	void deleteFileFromContainer(String containerName, String fileName);
	void addCertificateToContainer(String containerName, MultipartFile[] files);
	void deleteContainer(String containerName);
	List<String> getUrlsFromContainer(String containerName);
}
