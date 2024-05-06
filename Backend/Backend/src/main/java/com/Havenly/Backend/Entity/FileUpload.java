package com.Havenly.Backend.Entity;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	
	public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
		Path storePath = Paths.get(uploadDir);
		if(!Files.exists(storePath)) {
			Files.createDirectories(storePath);
		}
		try(InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = storePath.resolve(fileName);
	        Files.copy(inputStream, filePath);
		} catch (IOException e) {            
            throw new IOException("Could not save image file: " + fileName, e);
        }
	}

}
