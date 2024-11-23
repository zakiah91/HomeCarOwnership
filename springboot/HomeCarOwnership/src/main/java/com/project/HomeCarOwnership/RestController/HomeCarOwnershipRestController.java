package com.project.HomeCarOwnership.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.HomeCarOwnership.Repositories.ICarRepository;
import com.project.HomeCarOwnership.Repositories.IHouseRepository;
import com.project.HomeCarOwnership.Repositories.IOwnerRepository;

@RestController
@RequestMapping("/HomeCarOwnership")
public class HomeCarOwnershipRestController {
	
	private ICarRepository iCarRepository;
	
	private IHouseRepository iHouseRepository;
	
	private IOwnerRepository iOwnerRepository;
	

	@Autowired
	public HomeCarOwnershipRestController() {
		
	}
	
	
	@GetMapping
	public String test() {
		return "Server has started";
	}

}
