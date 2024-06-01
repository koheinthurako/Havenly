package com.Havenly.Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Havenly.Backend.Entity.Posts;
import com.Havenly.Backend.Service.Posts_Service;

@RestController
@RequestMapping("/posts")
public class Posts_Controller {
	
	@Autowired
	Posts_Service postService;
	
	@GetMapping("/allSubuserPosts")
	public ResponseEntity<List<Posts>> getAllPosts(@RequestParam int subUserId){
		return new ResponseEntity<List<Posts>>(postService.getAllSubuserPosts(subUserId),HttpStatus.OK);
	}
	
//	@GetMapping("/allSubuserRentPosts")
//	public ResponseEntity<List<Posts>> getAllSubuserRentPosts(@RequestParam int subUserId) {
//		return new ResponseEntity<List<Posts>>(service.getAllSubuserRentPosts(subUserId), HttpStatus.OK);
//	}

	@GetMapping("/getPostById/{postId}")
	public ResponseEntity<Posts> getPostById(@PathVariable int postId) {
		return new ResponseEntity<Posts>(postService.getPostById(postId), HttpStatus.OK);
	}

//	@GetMapping("/getPostByTypeId/{postId}")
//	public ResponseEntity<Posts> getPostByTypeId(@PathVariable String postId) {
//		return new ResponseEntity<Posts>(postService.getPostByTypeId(postId), HttpStatus.OK);
//	}
	
	@GetMapping("/allPending")
	public ResponseEntity<List<Posts>> getAllPendingPosts(){
		return new ResponseEntity<List<Posts>>(postService.getAllPendingPosts(),HttpStatus.OK);
	}
	
	@GetMapping("/allComplete")
	public ResponseEntity<List<Posts>> getAllCompletePosts(){
		return new ResponseEntity<List<Posts>>(postService.getAllCompletePosts(),HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Posts> update(@RequestBody Posts post){
		return new ResponseEntity<Posts>(postService.update(post),HttpStatus.OK);
	}
	
	@DeleteMapping("/deletepost/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable int postId){
		try {
            postService.deletePost(postId);
            return ResponseEntity.ok("Post deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting post: " + e.getMessage());
        }
	}


	@GetMapping("/getInterestedPostsByRegId/{reg_id}")
	public ResponseEntity<List<Posts>> getInterestedPosts(@PathVariable int reg_id) {
		return new ResponseEntity<>(postService.getInterestPostByRegId(reg_id), HttpStatus.OK);
	}



}
