package com.Havenly.Backend.Service_Impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Havenly.Backend.Service.Reg_user_Service;
import com.Havenly.Backend.DTO.Reg_user_DD;
import com.Havenly.Backend.DTO.Reg_user_DTO;
import com.Havenly.Backend.Entity.Ban_user;
import com.Havenly.Backend.Entity.Posts;
import com.Havenly.Backend.Entity.Reg_user;
import com.Havenly.Backend.Entity.RentPost;
import com.Havenly.Backend.Entity.SellPost;
import com.Havenly.Backend.Repo.Ban_user_Repo;
import com.Havenly.Backend.Repo.Interest_Repo;
import com.Havenly.Backend.Repo.PackagesRepo;
import com.Havenly.Backend.Repo.Posts_Repo;
import com.Havenly.Backend.Repo.Reg_user_Repo;
import com.Havenly.Backend.Repo.RentPost_Repo;
import com.Havenly.Backend.Repo.SellPost_Repo;
import com.Havenly.Backend.Repo.SubscribeRepo;
import com.Havenly.Backend.util.EmailUtil;

import jakarta.mail.MessagingException;

@Configuration
public class Reg_user_Service_Impl implements Reg_user_Service{
	
//	@Autowired
//	SellPost sellpost;
//	
//	@Autowired
//	RentPost rentPost;
	
	
	@Autowired
	Reg_user_Repo regRepo;
	
	@Autowired
	PasswordEncoder pwencoder;
	
	@Autowired
	JavaMailSender mail;
	
	@Autowired
	Interest_Repo interestRepo;
	
	@Autowired
	SubscribeRepo subRepo;
	
	@Autowired
	SellPost_Repo sellPosetRepo;
	
	@Autowired
	PackagesRepo packrepo;
	
	@Autowired
	Posts_Repo postsRepo;
	
	@Autowired
	RentPost_Repo rentRepo;
	
	@Autowired
	EmailUtil emailUtil;
	
	@Autowired
	Ban_user_Repo banRepo;
	
//	@Autowired
//	TokenRepository tokenRepository;
	
	Reg_user_DTO user_dto= new Reg_user_DTO();
	Reg_user_DD user3= new Reg_user_DD();
	@Override
	public List<Reg_user_DTO> findAll() {
		List<Reg_user> users = regRepo.findAll();
		List<Reg_user_DTO> reg_user_list = new ArrayList<Reg_user_DTO>();
		reg_user_list.clear();
		for(Reg_user user : users) {
			reg_user_list.add(user_dto.covertToObject(user));
		}
		return reg_user_list;
	}
	@Override
	public Reg_user_DTO register(Reg_user_DTO dto) {
		Reg_user user=user_dto.covertToEntity(dto);
		user.setPassword(this.pwencoder.encode(user.getPassword()));
		Ban_user banUser=banRepo.findByEmail(user.getEmail());
		if(banUser==null) {
			Reg_user user1=regRepo.save(user);
			Reg_user_DTO user2=user_dto.covertToObject(user1);
			return user2;

				}
		
		
		
		return null;
	}
	@Override
	public Reg_user_DD Login(String gmail, String password) {
		Reg_user user = regRepo.findByEmail(gmail);
		
		if (user == null || !pwencoder.matches(password,user.getPassword())) {
			return null;
		}
//		if (!pwencoder.matches(password, user.getPassword())) {
//			return null;
//		}
		Reg_user_DD user1=user3.covertToObject(user);
		return user1;
	}
	@Override
	public Reg_user_DD update(String name,String phone,String gmail) {
		
		Reg_user updateUser=regRepo.findByEmail(gmail);
		if (updateUser == null) {
			return null;
		}
		updateUser.setName(name);
		updateUser.setPhone(phone);
		Reg_user user2=regRepo.save(updateUser);
		Reg_user_DD user4=user3.covertToObject(user2);
		

		return user4;
	}
	@Override
	public Reg_user_DTO pwdUpdate(String username, String password , String new_password) {
		Reg_user user = regRepo.findByEmail(username);

		if (user == null) {
			return null;
		}
		if (!pwencoder.matches(password, user.getPassword())) {
			return null;
		}
		String encodedNewPassword = pwencoder.encode(new_password);
		user.setPassword(encodedNewPassword);
		Reg_user user1=regRepo.save(user);
		Reg_user_DTO user2=user_dto.covertToObject(user1);
		
		return user2;
	}
	

	@Override
	public String deleteByEmail(String email) {
	    // Find the user by email
	    Reg_user user1 = regRepo.findByEmail(email);

	    // If the user is not found, return an error
	    if (user1 == null) {
	        return "error: user not found";
	    }

	    // Get the user ID
	    int user_id = user1.getRegister_id();

	    // Delete interests associated with the user
	    interestRepo.DeleteByregisterId2(user_id);
	    System.out.println("Deleted interests for user");

	    // Check if the user has a subscription
	    if (user1.getSub() != null) {
	        // Get the subscription ID
	        Integer sub_id = subRepo.getsubId(user_id);
	        if (sub_id != null) {
	            Posts post;

	            // Get post ID and delete associated interests if sell post exists
	            Integer post_id = postsRepo.getSPostId(sub_id);
	            if (post_id != null) {
	                post = postsRepo.findById(post_id).orElse(null);
	                if (post != null && post.getSellpost() != null) {
	                    interestRepo.DeleteByregisterId(post_id, user_id);
	                }
	            }

	            // Get post ID and delete associated interests if rent post exists
	            Integer post_id1 = postsRepo.getRPostId(sub_id);
	            if (post_id1 != null) {
	                post = postsRepo.findById(post_id1).orElse(null);
	                if (post != null && post.getRentpost() != null) {
	                    interestRepo.DeleteByregisterId(post_id1, user_id);
	                }
	            }

	            // Delete posts associated with the subscription ID
	            postsRepo.deleteFromposts(sub_id);
	            System.out.println("Deleted posts for subscription");

	            // Get sell and rent post IDs
	            String sell_id = postsRepo.getSellId(sub_id);
	            String rent_id = postsRepo.getRentId(sub_id);

	            // Delete the sell post if it exists
	            if (sell_id != null) {
	                sellPosetRepo.deleteFromSell_post(sell_id);
	                System.out.println("Deleted sell post");
	            }

	            // Delete the rent post if it exists
	            if (rent_id != null) {
	                rentRepo.deleteFromRentpost(rent_id);
	                System.out.println("Deleted rent post");
	            }

	            // Delete packages associated with the subscription ID
	            packrepo.deleteFrompackages(sub_id);
	            System.out.println("Deleted packages for subscription");

	            // Delete the subscription
	            subRepo.deleteFromSub(sub_id);
	            System.out.println("Deleted subscription");
	        }
	    }

	    // Finally, delete the user
	    regRepo.deleteByEmail(email);
	    return "Deleted!";
	}

	@Override
	public Reg_user getDataBySubId(int id) {
		return regRepo.findDataBySubId(id);
	}

	@Override
	public String forgotPassword(String email) {
		// TODO Auto-generated method stub
	Reg_user user=	regRepo.findByEmail(email);
	if(user!=null) {
			try {
				emailUtil.sendSetPasswordEmail(email);
				
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Unable to send set password email please try again");
			}
				
				return "Please check your email to set new password to your account";
			}
	return "failed to send msg";
	
	}

	
	@Override
	public String setPassword(String email, String newPassword) {
		// TODO Auto-generated method stub
		Reg_user user=regRepo.findByEmail(email);
		String encodedNewPassword = pwencoder.encode(newPassword);
		user.setPassword(encodedNewPassword);
		regRepo.save(user);
		return "New password is set succeessfully.";
	}
	
	
	
	

}
