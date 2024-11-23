package com.project.HomeCarOwnership;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.project.HomeCarOwnership.Entities.Car;
import com.project.HomeCarOwnership.Entities.House;
import com.project.HomeCarOwnership.Entities.Owner;
import com.project.HomeCarOwnership.Repositories.ICarRepository;
import com.project.HomeCarOwnership.Repositories.IHouseRepository;
import com.project.HomeCarOwnership.Repositories.IOwnerRepository;

@SpringBootApplication
public class HomeCarOwnershipApplication {

	
	private IHouseRepository iHouseRepository;
    private IOwnerRepository iOwnerRepository;
    private ICarRepository iCarRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(HomeCarOwnershipApplication.class, args);
	}

	
	private void saveOwnerCarHouse(Owner owner, House house, Car car) {
		iOwnerRepository.save(owner);
		iHouseRepository.save(house);
		if(car != null) {
			iCarRepository.save(car);
		}
	}
	

    //zakiah23/11: Fill the database
    @Bean
    CommandLineRunner commandLineRunner( IHouseRepository iHouseRepository,
									     IOwnerRepository iOwnerRepository,
									     ICarRepository iCarRepository) {
		
		this.iHouseRepository = iHouseRepository;
		this.iOwnerRepository = iOwnerRepository;
		this.iCarRepository = iCarRepository;
    	
		return (args)->{
			
			Owner owner = new Owner();
			House house = new House();
			Car   car   = new Car();
					
		
			/**zakiah23/11: init data for owner 1**/
			owner = new Owner("o01",30,"owner1");			
			house = new House("A1-02",owner);			
			car = new Car("ABC 1234","red","viva",house);
			saveOwnerCarHouse(owner,house,car);
			
			car = new Car("ADC 5678","blue","viva",house);
			saveOwnerCarHouse(owner,house,car);
			
			house = new House("A01-03",owner);
			saveOwnerCarHouse(owner,house,null);
			
			
			/**zakiah23/11: init data for owner 2**/
			owner = new Owner("o02",34,"owner2");
			house = new House("B03-05",owner);
			car   = new Car("UAJ 2345","blue","myvi",house);
			saveOwnerCarHouse(owner,house,car);
			
			
			
			
			
			
		};
		
	}

}
