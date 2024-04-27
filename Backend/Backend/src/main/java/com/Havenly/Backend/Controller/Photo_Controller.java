//package com.Havenly.Backend.Controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.Havenly.Backend.Entity.Photo;
//import com.Havenly.Backend.Service.Photo_Service;
//
//@RestController
//@RequestMapping("/photo")
//public class Photo_Controller {
//	
//	@Autowired
//	Photo_Service photoService;
//	
//	@PostMapping("/upload")
//	public ResponseEntity<List<Photo>> savePhoto(@RequestBody List<Photo> testPhoto) {
//		return new ResponseEntity<List<Photo>>(photoService.savePhotos(testPhoto), HttpStatus.OK);
//	}
//	
//	@GetMapping("/getall")
//	public ResponseEntity<List<Photo>> getPhotos() {
//		return new ResponseEntity<List<Photo>>(photoService.getPhotos(), HttpStatus.OK);
//	}
//
//}
