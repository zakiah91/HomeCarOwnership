package com.project.HomeCarOwnership.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.project.HomeCarOwnership.Entities.Car;
import com.project.HomeCarOwnership.Entities.House;
import com.project.HomeCarOwnership.Entities.Owner;
import com.project.HomeCarOwnership.Gson.Profile;
import com.project.HomeCarOwnership.Repositories.ICarRepository;
import com.project.HomeCarOwnership.Repositories.IHouseRepository;
import com.project.HomeCarOwnership.Repositories.IOwnerRepository;

@RestController
@CrossOrigin
@RequestMapping("/HomeCarOwnership")
public class HomeCarOwnershipRestController {
	
	private ICarRepository iCarRepository;
	
	private IHouseRepository iHouseRepository;
	
	private IOwnerRepository iOwnerRepository;
	
	private Set<String> keySet = null;
	
	@Autowired
	public HomeCarOwnershipRestController(ICarRepository iCarRepository,
			                              IHouseRepository iHouseRepository,
			                              IOwnerRepository iOwnerRepository) {
		
		this.iCarRepository = iCarRepository;
		this.iHouseRepository = iHouseRepository;
		this.iOwnerRepository = iOwnerRepository;
		
	}
	
	
	@GetMapping
	/*zakiah25/11: A test just to know server has run*/
	public String test() {
		l("test test test");
		return "Server has started";
	}
	
	
	private void l(String log) {
		System.out.println(log);
	}
	
	private boolean checkAllKeysExist(String[] key) {
		
		for(int i=0; i<key.length;i++) {
			if(!keySet.contains(key[i])) {
				return false;
			}
		}
		
		return true;
		
	}
	
	@PostMapping("/profile")
	/**
	 * zakiah25/11:
	 * return user's profile in JSON when given a user id. 
	 * If it encounter error during the checking of the POST data, it will return ERR
	 * operations:
	 * 1) check all keys exist. if NOT ALL exist, it will return ERR
	 * 2) check if owner id is exist. if NOT exist, it will return ERR
	 * 3) get Owner's id. if owner's id is NULL, return ERR
	 * 4) get House List based on owner's id. If House List is NULL, return ERR
	 * 5) for each house, populate the Profile's class
	 * 6) convert the Profile to JSON using GSON
	 * @param reqOwner
	 * @return if OK, return JSON ; If NG, return ERR
	 */
	public String getOwnerProfile(@RequestBody String reqOwner) {
		
		if(reqOwner == null) {
			return "ERR";
		}
		
		l("run getOwnerProfile");
		
		int KEY_OID = 0;
		JsonObject ownerJson = JsonParser.parseString(reqOwner).getAsJsonObject();
		keySet = ownerJson.keySet();
		String[] key = {"oid"};
		
		if(checkAllKeysExist(key) == false){
			return "ERR";
		}
		
		if(iOwnerRepository.countByOwnerid(ownerJson.get(key[0]).getAsString()) == 0) {
			return "ERR";
		}
		
		l(ownerJson.get("oid").getAsString());

		
		String oid = ownerJson.get(key[KEY_OID]).getAsString();
		
		l(oid);
		Owner owner = iOwnerRepository.findByOwnerid(oid);
	
		l(owner.getOwnerId());
		
		if(owner == null) {
			l("owner is null");
			return "ERR";
		}
		
		List<House> house = iHouseRepository.findByOwner(owner);

		if(house == null) {
			l("house is null");
			return "ERR";
		}
		
		List<Profile> profileList = new ArrayList<Profile>();
		
		Profile profile;
		for(int i=0; i<house.size();i++) {
			profile = new Profile();
			profile.setOwnerDetails(oid, owner.getName(), owner.getAge());
			profile.setHousenumber(house.get(i).getHouseNumber());
			profile.setCarDetails(iCarRepository.findByHouse(house.get(i)));
			profileList.add(profile);
		}
		
        String str = new Gson().toJson(profileList);	
		return str;
	}
	
	
	@PostMapping("/registerOwner")
	/**
	 * zakiah 25/11:
	 * add owner's details to the Owner table and House table. 
	 * If it encounter error during the checking of the POST data, it will return ERR. 
	 * Else, it will return OK
	 * operations:
	 * 1) check all keys exist. IF NOT ALL keys exist, it will return ERR
	 * 2) check if ownerid exist. If exist, it will return ERR
	 * 3) check if housenumber exist. If exist, it will return ERR
	 * 4) save the new owner
	 * 5) save the new house
	 * @param reqOwner
	 * @return If OK, it will return OK; If NG, it will return ERR
	 */
	public String registerNewOwner(@RequestBody String reqOwner) {
		
		if(reqOwner == null) {
			return "ERR";
		}
		
		int KEY_NAME = 0;
		int KEY_AGE = 1;
		int KEY_OID = 2;
		int KEY_HOUSE = 3;
		
		JsonObject ownerJson = JsonParser.parseString(reqOwner).getAsJsonObject();
		String[] key = {"name","age","oid","housenumber"};
		keySet = ownerJson.keySet();
		
		if(checkAllKeysExist(key) == false){
			return "ERR";
		}
		
		if(iOwnerRepository.countByOwnerid(ownerJson.get(key[KEY_OID]).getAsString()) != 0) {
			return "ERR";
		}
		
		if(iHouseRepository.countByHousenumber(ownerJson.get(key[KEY_HOUSE]).getAsString()) != 0) {
			return "ERR";
		}
		
		Owner owner = new Owner(ownerJson.get(key[KEY_OID]).getAsString(),ownerJson.get(key[KEY_AGE]).getAsInt(),ownerJson.get(key[KEY_NAME]).getAsString());
		iOwnerRepository.save(owner);
		
		House house = new House(ownerJson.get(key[KEY_HOUSE]).getAsString(),owner);
		iHouseRepository.save(house);
		
		return "OK";
	}
	
	@PostMapping("/registerCars")
	/**
	 * zakiah25/11:
	 * add car details to the Car table based on the given owner's id and house id.
	 * If it encounter error during the checking of the POST data, it will return ERR.
	 * Else, it will return OK
	 * operations:
	 * 1) check all keys exist. If NOT ALL exist, return ERR
	 * 2) check owner's id exist. If NOT exist, return ERR
	 * 3) check whether the owner owns the house by checking the housenumber. If NOT, return ERR
	 * 4) get the house based on the housenumber. IF NOT exist, return ERR
	 * 5) convert the JSON Car into JSONArray, then for each element, add it to the Car table
	 * @param reqOwner
	 * @return IF OK, it will return OK; If NG, it will return ERR
	 */
	public String registerCars(@RequestBody String reqOwner) {
		
		if(reqOwner == null) {
			return "ERR";
		}
		
		int KEY_OID = 0;
		int KEY_HOUSE = 1;
		int KEY_CAR = 2;
		JsonObject ownerJson = JsonParser.parseString(reqOwner).getAsJsonObject();
		String[] key = {"oid","housenumber","cars"};
		keySet = ownerJson.keySet();
		
		if(checkAllKeysExist(key) == false){
			return "ERR";
		}		
		
		if(iOwnerRepository.countByOwnerid(ownerJson.get(key[KEY_OID]).getAsString()) != 1) {
			return "ERR";
		}
		
		List<House> houseList = iHouseRepository.findByOwner(iOwnerRepository.findByOwnerid(ownerJson.get(key[KEY_OID]).getAsString()));
		boolean isExist = false;
		for(int i=0;i<houseList.size();i++) {
			if(houseList.get(i).getHouseNumber().equals(ownerJson.get(key[KEY_HOUSE]).getAsString())) {
				isExist = true;
				break;
			}
		}
		
		if(isExist == false) {
			return "ERR";
		}
		
		if(iHouseRepository.countByHousenumber(ownerJson.get(key[KEY_HOUSE]).getAsString()) != 1) {
			return "ERR";
		}
		
		House house = iHouseRepository.findByHousenumber(ownerJson.get(key[KEY_HOUSE]).getAsString());
		if(house == null) {
			return "ERR";
		}
		
		
		JsonArray carArray = ownerJson.get(key[KEY_CAR]).getAsJsonArray();
		JsonObject carJson;
		
		int KEY_COLOUR = 0;
		int KEY_PLATE = 1;
		int KEY_TYPE = 2;
		KEY_HOUSE = 3;
		String[] carKey = {"colour","platenumber","type","housenumber"};
		Car car;
		
		for(int i=0;i<carArray.size();i++) {
			carJson = carArray.get(i).getAsJsonObject();
			car = new Car(carJson.get(carKey[KEY_PLATE]).getAsString(),
					     carJson.get(carKey[KEY_COLOUR]).getAsString(),
					     carJson.get(carKey[KEY_TYPE]).getAsString(),
					      house);
			iCarRepository.save(car);
		}
		//String str = ownerJson.get(key[KEY_CAR]).getAsString();
		
		return "OK";
	}
	
	
	
	
	
	

}
