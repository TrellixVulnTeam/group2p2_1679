package com.revature.daos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Genre;

@Repository
public interface GenreDAO extends JpaRepository<Genre, Integer> {
	
//	public Optional<List<Genre>> findByName(String name);
	
}