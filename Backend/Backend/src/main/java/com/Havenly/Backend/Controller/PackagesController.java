package com.Havenly.Backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Havenly.Backend.DTO.Packages_DD;
import com.Havenly.Backend.DTO.Packages_DTO;
import com.Havenly.Backend.DTO.Subscription_DTO;
import com.Havenly.Backend.Entity.Packages;
import com.Havenly.Backend.Entity.Subscription;
import com.Havenly.Backend.Repo.PackagesRepo;
import com.Havenly.Backend.Repo.SubscribeRepo;
import com.Havenly.Backend.Service.PackagesService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/packages")
public class PackagesController {

	@Autowired
	PackagesRepo packRepo;
	@Autowired
	PackagesService packService;
	@Autowired
	SubscribeRepo subRepo;

	@GetMapping("/my-package")
	public ResponseEntity <Packages_DTO> showPurchased(@Valid @RequestBody Packages_DTO pack){
		if(pack.getPackageId()<=0) {
			return ResponseEntity.badRequest().build();
		}
		if(packRepo.findById(pack.getPackageId())==null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity <Packages_DTO>(packService.showPackage(pack),HttpStatus.OK);
	}
	
	@PostMapping("/payment")
	public ResponseEntity <String> purchasePackage(@Valid @RequestBody Packages_DD dto){
		if(dto == null) {
			return ResponseEntity.badRequest().build();
		}
		
		Packages_DD pack = packService.buyPack(dto.getEmail(), dto.getPackageType(), dto.getAmount());
		if (pack == null) {
			return ResponseEntity.internalServerError().build();
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@DeleteMapping("/cancel-package")
	public ResponseEntity <String> cancelPack(@Valid @RequestBody Subscription_DTO dto){
		if(dto == null) {
			return ResponseEntity.badRequest().build();
		}
		String nrc = dto.getNrc();
		Subscription sub = subRepo.findByNrc(nrc);		

		if (sub == null) {
			return ResponseEntity.notFound().build();
		}
		Packages pack = sub.getPackages();
		boolean isDeleted = packService.delete(pack);
		if (isDeleted) {
			return new ResponseEntity<String>(HttpStatus.OK);
		}

		return ResponseEntity.ok().build();
	}
	
}
