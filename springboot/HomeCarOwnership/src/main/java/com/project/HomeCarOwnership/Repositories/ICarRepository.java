package com.project.HomeCarOwnership.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.HomeCarOwnership.Entities.Car;

public interface ICarRepository extends JpaRepository<Car,String> {

}
