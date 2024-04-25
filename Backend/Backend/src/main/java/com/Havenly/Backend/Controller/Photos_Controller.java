package com.Havenly.Backend.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Havenly.Backend.Entity.Photos;
import com.Havenly.Backend.Service.Photos_Service;

@RestController
@RequestMapping("/photo")
public class Photos_Controller {
	
	@Autowired
	Photos_Service photoService;
	
//	@PostMapping("/upload/{fileType}/{file}")
//	public ResponseEntity<String> upload(@PathVariable String fileType, @PathVariable MultipartFile file) throws IOException {
//		String result = photoService.upload(file, fileType);
//		return ResponseEntity.ok().body(result);
//	}
	
	 @PostMapping("/upload")
	    public ResponseEntity<String> handleFileUpload(@RequestParam("files") List<MultipartFile> files) throws IOException {
	        if (files.isEmpty()) {
	            return ResponseEntity.badRequest().body("No files provided");
	        }

	        List<MultipartFile> photoList = new ArrayList<MultipartFile>();
	        HttpHeaders responseHeaders = new HttpHeaders();
	        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
	        for (MultipartFile file : files) {
	            // Process each file as needed
	            System.out.println("File name: " + file.getOriginalFilename());
	            photoList.add(photoService.upload(file));
	        }

	        return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).body("Files uploaded successfully");
	    }
	
//	@GetMapping("/media/{fileType}/{fileName}")
//	public ResponseEntity<?> download(@PathVariable("fileType") String fileType, @PathVariable String fileName) throws IOException {
//		MediaType type = MediaType.IMAGE_PNG;
//		switch (fileType) {
//		case "mp4":
//			type = MediaType.APPLICATION_OCTET_STREAM;
//			break;
//		case "jpg":
//		case "jpeg":
//			type = MediaType.IMAGE_JPEG;
//			break;
//		case "txt":
//			type = MediaType.TEXT_PLAIN;
//			break;
//		case "png":
//			type = MediaType.IMAGE_PNG;
//			break;
//		default:
//			return ResponseEntity.badRequest().body("Unsupported File Type");
//		}
//		List<Photos> photos = photoService.getAllPhotos();
//		if (photos.size()==0) {
//			return ResponseEntity.notFound().build();
//		}
//		return ResponseEntity.ok().contentType(type).body(photos);
//	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<Photos>> getAllPhotos() {
	    List<Photos> photos = photoService.getAllPhotos();
	    return ResponseEntity.ok(photos);
	}

}
