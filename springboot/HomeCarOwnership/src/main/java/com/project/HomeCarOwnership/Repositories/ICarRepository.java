package com.project.HomeCarOwnership.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.HomeCarOwnership.Entities.Car;
import com.project.HomeCarOwnership.Entities.House;

import jakarta.annotation.Nullable;

public interface ICarRepository extends JpaRepository<Car,String> {
	
	@Nullable
	public List<Car> findByHouse(House house);
	
}
