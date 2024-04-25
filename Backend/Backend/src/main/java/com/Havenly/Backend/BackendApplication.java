package com.Havenly.Backend;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.Havenly.Backend.Entity.PackageTypes;
import com.Havenly.Backend.Repo.PackageTypesRepo;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BackendApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Connected to Database!");

	}

//	@Bean
//    CommandLineRunner init (PackageTypesRepo pTypeRepo){
//        return args -> {
//        	
//            List<String> packNames = Arrays.asList("Free Trial", "Normal", "Premium");
//            List<Integer> prices = Arrays.asList(0, 25, 50);
//            List<Integer> totalAds = Arrays.asList(2, 10, 20);
//            List<Integer> totalPosts = Arrays.asList(3, 15, 30); 
//
//          for(int i=0;i<packNames.size();i++) {
//        	  PackageTypes pType = new PackageTypes();
//        	  pType.setPackName(packNames.get(i));
//        	  pType.setPrice(prices.get(i));
//          		pType.setTotal_ads(totalAds.get(i));
//          		pType.setTotal_posts(totalPosts.get(i));
//        	pTypeRepo.save(pType);
//          }
//        };
//    }
}
