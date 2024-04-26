package com.Havenly.Backend.Service_Impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Havenly.Backend.Entity.Photos;
import com.Havenly.Backend.Repo.Photos_Repo;
import com.Havenly.Backend.Service.Photos_Service;

@Configuration
public class Photos_Service_Impl implements Photos_Service{
	
	@Autowired
	Photos_Repo photoRepo;
	
	private final Path savePath;
	
	@Autowired
	public Photos_Service_Impl() throws IOException {
		Path savePath = Paths.get("/").resolve("src").resolve("main").resolve("resources")
						.resolve("static").resolve("File");
		if(!Files.exists(savePath)) {
			Files.createDirectories(savePath);
		}
		this.savePath = savePath;
	}
	
	@Override
	public MultipartFile upload(MultipartFile file) throws IOException {
		String fileName = LocalDate.now()+"_"+StringUtils.cleanPath(file.getOriginalFilename());
		Files.copy(file.getInputStream(), this.savePath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
		Photos photo = new Photos();
	    photo.setPhotoData(Files.readAllBytes(this.savePath.resolve(fileName)));
	    photoRepo.save(photo);
		return file;
	}

	@Override
	public List<Photos> getAllPhotos() {
		return photoRepo.findAll();
	}

//	@Override
//	public byte[] getPhoto(String fileName) throws IOException, MalformedURLException {
//		byte[] photoByte = null;
//		Path path = this.savePath.resolve(fileName);
//		Resource resource = new UrlResource(path.toUri());
//		if(resource.exists() && resource.isReadable()) {
//			photoByte = StreamUtils.copyToByteArray(resource.getInputStream());
//		}
//		return photoByte;
//	}
	

}
