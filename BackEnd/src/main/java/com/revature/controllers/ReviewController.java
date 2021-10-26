package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Anime;
import com.revature.models.Review;
import com.revature.models.User;
import com.revature.services.AnimeService;
import com.revature.services.ReviewService;
import com.revature.services.UserService;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials ="true")
@RequestMapping(value="/review")
public class ReviewController {

	private ReviewService rService;
	private AnimeService aService;
	private UserService uService;

	@Autowired
	public ReviewController(ReviewService rService, AnimeService aService, UserService uService) {
		super();
		this.rService = rService;
		this.aService = aService;
		this.uService = uService;
	}




	@GetMapping(value = "/anime/{id}")  
	public ResponseEntity<List<Review>> findByAnime(@PathVariable int id) {

		Optional<Anime> anime = aService.findById(id);



		Optional<List<Review>> opt = rService.findByAnime(anime);


		List<Review> reviewList = null;

		if(opt.isPresent()) { 
			reviewList = opt.get(); 
		}

		return ResponseEntity.ok(reviewList);

	}

	@GetMapping(value = "/user/{id}")  
	public ResponseEntity<List<Review>> findByUser(@PathVariable int id) {

		User user = uService.findById(id);


		Optional<List<Review>> opt = rService.findByUser(user);


		List<Review> reviewList = null;

		if(opt.isPresent()) { 
			reviewList = opt.get(); 
		}

		return ResponseEntity.ok(reviewList);

	}

	@PostMapping(value = "/user/{userId}/anime/{animeId}")
	public ResponseEntity addReview(@RequestBody Review review, @PathVariable int userId, @PathVariable int animeId) {
		
		if(review == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		//Receive our anime and user from the path
		User u = uService.findById(userId);
		Anime a = aService.findById(animeId).get();
		
		//Place them into the review
		review.setAnime(a);
		review.setUser(u);
		
		rService.save(review);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}



}
