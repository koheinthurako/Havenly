package com.Havenly.Backend.Controller;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.Havenly.Backend.Entity.Posts;
import com.Havenly.Backend.Entity.Reg_user;
import com.Havenly.Backend.Repo.Interest_Repo;
import com.Havenly.Backend.Repo.Posts_Repo;
import com.Havenly.Backend.Repo.Reg_user_Repo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.Havenly.Backend.Entity.Interest;
import com.Havenly.Backend.Service.Interest_Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/interest")
public class Interest_Controller {

	@Autowired
	Interest_Service in_service;

	@Autowired
	Interest_Repo interest_repo;

	@Autowired
	Reg_user_Repo reg_repo;

	@Autowired
	Posts_Repo posts_repo;


	@PostMapping("/addNew/{user_id}/{post_id}")
	public ResponseEntity<String> addInterest(@Valid @PathVariable int user_id, @PathVariable int post_id, @RequestBody Interest intData,BindingResult result) throws IOException {
		List<ObjectError> errors = result.getAllErrors();
		if(errors.size() != 0) {
			String err_st = errors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","+System.lineSeparator()));
			return new ResponseEntity<>(err_st, HttpStatus.FORBIDDEN);
		}

		// check duplicate or not
		Long count = interest_repo.countByPostIdAndUserId(user_id, post_id);
		if (count > 0) {
			return new ResponseEntity<>("already exists", HttpStatus.CONFLICT);
		}

		// Find Register user and post is Exist
		Optional<Reg_user> optionalRegUser = reg_repo.findById(user_id);
		Optional<Posts> optionalPost = posts_repo.findById(post_id);

		if (optionalRegUser.isPresent() && optionalPost.isPresent()) {

			Reg_user Reg_all_data=optionalRegUser.get();
			Posts post_all_data=optionalPost.get();

			Interest interest = new Interest();
			interest.setDescription(intData.getDescription());
			interest.setReg_user(Reg_all_data);
			interest.setPosts(post_all_data);
			interest.setInterest_date(LocalDate.now());
			interest.setInterest_time(LocalTime.now());
			in_service.save(interest);
		} else {
			return new ResponseEntity<>("No post or User Exist", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>("Saved", HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteByPostIdAndUserId/{post_id}/{user_id}")
	public ResponseEntity<String> deleteByPostIdAndUserId(@PathVariable int post_id, @PathVariable int user_id) {
		Boolean deleted = in_service.deleteByPostIdAndUserId(post_id, user_id);
		return(deleted) ?
				new ResponseEntity<>("Deleted successfully", HttpStatus.OK):
				new ResponseEntity<>("ID not found", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Interest>> get(){
		return new ResponseEntity<List<Interest>>(in_service.getAll(),HttpStatus.OK);
	}
	
	@GetMapping("/getAllNotiBySubId/{id}")
	public ResponseEntity<List<Interest>> getAllNotiBySubId(@PathVariable int id){
//		return new ResponseEntity<List<Interest>>(in_service.getAllInterestForNoti(id),HttpStatus.OK);
		List<Interest> data = in_service.getAllInterestForNoti(id);
		if (data == null || data.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("/getDataByPostId/{id}")
	public ResponseEntity<List<Interest>> getDataByPostId(@PathVariable int id) {
		List<Interest> data = in_service.getDataByPostId(id);
		if (data == null || data.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

}
