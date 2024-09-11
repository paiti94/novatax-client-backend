package com.novatax.client.portal.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.novatax.client.portal.entities.Files;
import com.novatax.client.portal.repository.FileRepository;
import com.novatax.client.portal.services.FileStorageService;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/files")
public class FileUploadController {

	@Autowired
    private final FileStorageService fileStorageService;
	
	@Autowired
	private FileRepository fileRepository;

    public FileUploadController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }
    
    
    @GetMapping("/folder-structure")
    public List<Files> getFolderStructure(@RequestParam Integer clientId, @RequestParam(required = false) Integer parentId) {
        if (parentId == null) {
            return fileRepository.findByClientIdAndFileType(clientId, "folder");
        } else {
            return fileRepository.findByClientIdAndParentId(clientId, parentId);
        }
    }
    
    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "jobId", required = false) Integer jobId,
            @RequestParam(value = "clientId", required = false) Integer clientId,
            @RequestParam("fileType") String fileType,
            @RequestParam("guiLocation") String guiLocation,
            @RequestParam(value = "parentId", required = false) Integer parentId) {
        try {
            String fileName = fileStorageService.storeFile(file, jobId, clientId, fileType, guiLocation, parentId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "File uploaded successfully");
            response.put("fileName", fileName);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Failed to upload file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/folder")
    public ResponseEntity<Map<String, String>> createFolder(
            @RequestParam("folderName") String folderName,
            @RequestParam(value = "jobId", required = false) Integer jobId,
            @RequestParam(value = "clientId", required = false) Integer clientId,
            @RequestParam("guiLocation") String guiLocation,
            @RequestParam(value = "parentId", required = false) Integer parentId) {
        try {
            String fName = fileStorageService.storeFolder(folderName, jobId, clientId, guiLocation, parentId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Folder created successfully");
            response.put("folderName", fName);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Failed to create folder: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            Path filePath = fileStorageService.loadFile(fileName);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not download the file!", e);
        }
    }

}
