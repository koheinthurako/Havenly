package com.Havenly.Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Havenly.Backend.DTO.Packages_DTO;
import com.Havenly.Backend.DTO.Subscription_DTO;
import com.Havenly.Backend.Entity.PackageTypes;
import com.Havenly.Backend.Entity.Packages;
import com.Havenly.Backend.Repo.PackageTypesRepo;
import com.Havenly.Backend.Repo.PackagesRepo;
import com.Havenly.Backend.Repo.SubscribeRepo;
import com.Havenly.Backend.Service.PackagesService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/packages")
public class PackagesController {

	@Autowired
	PackageTypesRepo packTypesRepo;
	@Autowired
	PackagesRepo packRepo;
	@Autowired
	PackagesService packService;
	@Autowired
	SubscribeRepo subRepo;
	
	@GetMapping("/")
	public ResponseEntity <List<PackageTypes>> showPackages(){
		return new ResponseEntity <List<PackageTypes>>(packTypesRepo.findAll(),HttpStatus.OK);
	}

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
	
	@PostMapping("/purchase")
	public ResponseEntity <String> purchasePackage(@Valid @RequestBody Subscription_DTO dto){
		if(dto == null) {
			return ResponseEntity.badRequest().build();
		}
		String email = dto.getEmail();
		String packType = dto.getPackageType();
		if(subRepo.findByEmail(dto.getEmail())==null) {
			return ResponseEntity.notFound().build();
		}
//		if (pack.getPayment() == null) {
//			return ResponseEntity.badRequest().build();
//		}
		if (dto.getPackageType() == null) {
			return ResponseEntity.badRequest().build();
		}
		Packages_DTO pack = packService.buyPack(email, packType);
		if (pack == null) {
			return ResponseEntity.internalServerError().build();
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@DeleteMapping("/cancel-package")
	public ResponseEntity <?> cancelPack(@Valid @RequestBody int packId){
		Packages pack = packService.findById(packId);
		if (pack == null) {
			return ResponseEntity.notFound().build();
		}

		boolean isDeleted = packService.delete(packId);
		if (!isDeleted) {
			return new ResponseEntity<>(HttpStatus.OK);
		}

		return ResponseEntity.ok().build();
	}
	
}
