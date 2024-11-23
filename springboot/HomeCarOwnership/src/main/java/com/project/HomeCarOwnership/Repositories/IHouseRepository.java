package com.project.HomeCarOwnership.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.HomeCarOwnership.Entities.House;
import com.project.HomeCarOwnership.Entities.Owner;

import jakarta.annotation.Nullable;

public interface IHouseRepository extends JpaRepository<House,String> {

	 @Nullable
	 List<House> findByOwner(Owner owner);
	 
	 @Nullable
	 House findByHousenumber(String housenumber);

	 int countByHousenumber(String housenumber);

}
