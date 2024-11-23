package com.project.HomeCarOwnership.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.HomeCarOwnership.Entities.House;

public interface IHouseRepository extends JpaRepository<House,String> {

}
