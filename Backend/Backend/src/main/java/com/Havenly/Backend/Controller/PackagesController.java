package com.Havenly.Backend.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Havenly.Backend.DTO.Packages_DTO;
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
	public ResponseEntity <?> purchasePackage(@Valid @RequestBody String nrc, Packages_DTO pack){
		if(nrc == null) {
			return ResponseEntity.badRequest().body("Invalid nrc.");
		}
		if(subRepo.findByNrc(nrc)==null) {
			return ResponseEntity.notFound().build();
		}
		if (pack.getPayment() == null) {
			return ResponseEntity.badRequest().body("Invalid payment.");
		}
		if (pack.getPackType() == null) {
			return ResponseEntity.badRequest().body("Invalid package.");
		}
		pack.setPackDate(LocalDate.now());
		pack.setPackTime(LocalDateTime.now());
		return new ResponseEntity <>(packService.buyPack(nrc, pack),HttpStatus.OK);
	}
	
	@DeleteMapping("/cancel-package")
	public ResponseEntity <?> cancelPack(@Valid @RequestBody int packId){
		Packages pack = packService.findById(packId);
		if (pack == null) {
			return ResponseEntity.notFound().build();
		}

		boolean isDeleted = packService.delete(packId);
		if (!isDeleted) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}

		return ResponseEntity.ok().build();
	}
	
}
