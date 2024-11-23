package com.project.HomeCarOwnership.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.HomeCarOwnership.Entities.Owner;

public interface IOwnerRepository extends JpaRepository<Owner,String>{

}
