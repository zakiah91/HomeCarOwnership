package com.project.HomeCarOwnership.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.HomeCarOwnership.Entities.Owner;

import jakarta.annotation.Nullable;

public interface IOwnerRepository extends JpaRepository<Owner,String>{
	
	@Nullable
	public Owner findByOwnerid(String ownerid);

	public int countByOwnerid(String ownerid);
	
}
