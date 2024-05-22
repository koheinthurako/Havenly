//package com.Havenly.Backend.Controller;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.Base64;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.Havenly.Backend.Entity.FileUpload;
//import com.Havenly.Backend.Entity.Posts;
//import com.Havenly.Backend.Entity.SellPost;
//import com.Havenly.Backend.Repo.Posts_Repo;
//import com.Havenly.Backend.Repo.SellPost_Repo;
//import com.Havenly.Backend.Service.SellPost_Service;
//
//@RestController
//@RequestMapping("/")
//public class SellPost_Controller {
//	
//	@Autowired
//	SellPost_Repo sellRepo;
//	
//	@Autowired
//	SellPost_Service sellPostService;
//	
//	@Autowired
//	Posts_Repo postsRepo;
//	
//	private int sellCount;
//	
//	public SellPost_Controller(SellPost_Repo sellRepo) {
//		System.out.println("Sell Post Count Number : " + this.sellCount);
//    }
//	
//	@GetMapping("/getsellpost")
//	public ResponseEntity<List<SellPost>> getAllSellPosts() {
//		return new ResponseEntity<List<SellPost>>(sellRepo.getAllPosts(), HttpStatus.OK);
//	}
//	
//	@PostMapping("/savesellpost")
//	public SellPost saveSellPost(@RequestBody SellPost sellpost) {
//		
//		sellCount = (int) sellRepo.count();
//		sellCount++;
//		String customId = "s" + sellCount;
//		
//		sellpost.setSell_post_id(customId);
//		sellpost.setDate(LocalDate.now());
//		sellpost.setTime(LocalTime.now());
//		sellRepo.save(sellpost);
//		
//		Posts post = new Posts();
//		post.setPost_type(customId);
//		post.setStatus("pending");
//		postsRepo.save(post);
//		
//		return sellpost;
//	}
//	
////	@PostMapping(value = "/savesellpost", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
////    public ResponseEntity<?> saveSellPost(@RequestPart("photos") List<MultipartFile> photos, @RequestPart("sellPost") SellPost sellPost) throws IOException {
////		
////		sellCount = (int) sellRepo.count();
////		String customId = "s" + sellCount++;
////		
//////		Path storePath = Paths.get("/").resolve("src").resolve("main")
//////				.resolve("resources").resolve("static").resolve("File");
////		
////		String uploadDir = "./src/main/recources/static/File";
////		
////		for(MultipartFile photo : photos) {
////			String fileName = LocalDate.now().getYear() + "_" + photo.getOriginalFilename();
////			FileUpload.saveFile(uploadDir, fileName, photo);
////		}
////		
////		sellPost.setSell_post_id(customId);
////		sellPost.setDate(LocalDate.now());
////		sellPost.setTime(LocalTime.now());
////		sellRepo.save(sellPost);
////		
////		Posts post = new Posts();
////		post.setPost_type(customId);
////		post.setStatus("pending");
////		postsRepo.save(post);
////		
////		sellRepo.save(sellPost);
////        return ResponseEntity.ok("Sell post saved successfully.");
////    }
//	
//	
//	
////@PostMapping(value = "/savesellpost", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
////  public ResponseEntity<?> saveSellPost(@RequestParam("photos") List<MultipartFile> photos,
////		  @RequestParam("sellPost") SellPost sellPost) throws IOException {
////	
////	sellCount = (int) sellRepo.count();
////	String customId = "s" + sellCount++;
////	
//////		Path storePath = Paths.get("/").resolve("src").resolve("main")
//////				.resolve("resources").resolve("static").resolve("File");
////	
////	String uploadDir = "./src/main/recources/static/File";
////	
////	for(MultipartFile photo : photos) {
////		String fileName = LocalDate.now().getYear() + "_" + photo.getOriginalFilename();
////		FileUpload.saveFile(uploadDir, fileName, photo);
////	}
////	
////	sellPost.setSell_post_id(customId);
////	sellPost.setDate(LocalDate.now());
////	sellPost.setTime(LocalTime.now());
////	sellRepo.save(sellPost);
////	
////	Posts post = new Posts();
////	post.setPost_type(customId);
////	post.setStatus("pending");
////	postsRepo.save(post);
////	
////	sellRepo.save(sellPost);
////	return ResponseEntity.ok("Sell post saved successfully.");
////  }
//	
//	
//	
//	
////	@PostMapping(value = "/savesellpost", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
////	public ResponseEntity<?> saveSellPost(@RequestPart("photos") List<MultipartFile> photos, @RequestPart("sellPost") SellPost sellPost) throws IOException {
////		System.out.println(photos.toString());
////		return null;
////	}
//	
//	
//
//}






