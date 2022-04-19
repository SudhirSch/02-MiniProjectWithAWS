package com.ashok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashok.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer>{

}
