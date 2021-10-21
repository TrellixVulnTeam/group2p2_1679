package com.revature.daos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Anime;
import com.revature.models.Review;
import com.revature.models.User;

@Repository
public interface ReviewDAO extends JpaRepository<Review, Integer> {
	

	public Optional<List<Review>> findByAnime(Anime anime);

	public Optional<List<Review>> findByAuthor(User user);
	
}
