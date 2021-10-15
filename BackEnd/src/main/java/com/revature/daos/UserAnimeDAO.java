package com.revature.daos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.UserAnime;

@Repository
public interface UserAnimeDAO extends JpaRepository<UserAnime, Integer> {

	public Optional<List<UserAnime>> findByName(String name);

}
