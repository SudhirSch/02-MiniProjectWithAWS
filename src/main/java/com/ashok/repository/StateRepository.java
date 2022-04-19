package com.ashok.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashok.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{
	
	public List<State> findByCountryId(int countryid);

}
