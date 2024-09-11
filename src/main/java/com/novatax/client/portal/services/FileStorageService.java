package com.novatax.client.portal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.novatax.client.portal.entities.Clients;
import com.novatax.client.portal.entities.Files;
import com.novatax.client.portal.entities.Job;
import com.novatax.client.portal.repository.ClientRepository;
import com.novatax.client.portal.repository.FileRepository;
import com.novatax.client.portal.repository.JobRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Service
public class FileStorageService {

	 private final Path fileStorageLocation;

	    @Autowired
	    private FileRepository fileRepository;
	    
	    @Autowired
	    private JobRepository jobRepository;
	    
	    @Autowired
	    private ClientRepository clientRepository;

	    public FileStorageService(@Value("${file.upload-dir}") String uploadDir) {
	        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
	        try {
	        	java.nio.file.Files.createDirectories(this.fileStorageLocation);
	        } catch (Exception ex) {
	            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
	        }
	    }


	    public String storeFile(MultipartFile file, Integer jobId, Integer clientId, String fileType, String guiLocation, Integer parentId) throws Exception {
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	        Job job = null;
	        Clients client = null;
	        Files parent = null;

	        if (fileName.contains("..")) {
	            throw new Exception("Filename contains invalid path sequence " + fileName);
	        }

	        Path targetLocation = this.fileStorageLocation.resolve(fileName);
	        java.nio.file.Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

	        if (jobId != null) {
	            job = jobRepository.findById(jobId).orElse(null);
	            if (job == null) {
	                throw new Exception("Job ID " + jobId + " not found");
	            }
	        }
	        if (clientId != null) {
	            client = clientRepository.findById(clientId).orElse(null);
	            if (client == null) {
	                throw new Exception("Client ID " + clientId + " not found");
	            }
	        }
	        if (parentId != null) {
	            parent = fileRepository.findById(parentId).orElse(null);
	            if (parent == null) {
	                throw new Exception("Parent folder ID " + parentId + " not found");
	            }
	        }

	        Files fileRecord = new Files(fileName, fileType, targetLocation.toString(), guiLocation, job, client, parent, new Date());
	        fileRepository.save(fileRecord);

	        return fileName;
	    }
	    
	    public String storeFolder(String folderName, Integer jobId, Integer clientId, String guiLocation, Integer parentId) throws Exception {
	        Job job = null;
	        Clients client = null;
	        Files parent = null;

	        if (jobId != null) {
	            job = jobRepository.findById(jobId).orElse(null);
	            if (job == null) {
	                throw new Exception("Job ID " + jobId + " not found");
	            }
	        }
	        if (clientId != null) {
	            client = clientRepository.findById(clientId).orElse(null);
	            if (client == null) {
	                throw new Exception("Client ID " + clientId + " not found");
	            }
	        }
	        if (parentId != null) {
	            parent = fileRepository.findById(parentId).orElse(null);
	            if (parent == null) {
	                throw new Exception("Parent folder ID " + parentId + " not found");
	            }
	        }

	        boolean folderExists = fileRepository.existsByClientIdAndGuiLocation(clientId, guiLocation);
	        if (!folderExists) {
	            Files fileRecord = new Files(folderName, "folder", null, guiLocation, job, client, parent, new Date());
	            fileRepository.save(fileRecord);
	            return folderName;
	        }

	        return "the folder is already existing";
	    }

	    public Path loadFile(String fileName) {
	        return this.fileStorageLocation.resolve(fileName).normalize();
	    }
}
