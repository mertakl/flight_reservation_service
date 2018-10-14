package com.mertakl.springboot.flight.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.mertakl.springboot.flight.exception.ResourceNotFoundException;
import com.mertakl.springboot.flight.model.User;
import com.mertakl.springboot.flight.payload.UserIdentityAvailability;
import com.mertakl.springboot.flight.payload.UserProfile;
import com.mertakl.springboot.flight.payload.UserSummary;
import com.mertakl.springboot.flight.repository.UserRepository;
import com.mertakl.springboot.flight.security.CurrentUser;
import com.mertakl.springboot.flight.security.UserPrincipal;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation getCurrentUser
	 */
	@GetMapping("/user/me")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(),
				currentUser.getFirst_name(), currentUser.getLast_name());
		return userSummary;
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation checkUsernameAvailability
	 */
	@GetMapping("/user/checkUsernameAvailability")
	public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
		Boolean isAvailable = !userRepository.existsByUsername(username);
		return new UserIdentityAvailability(isAvailable);
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation checkEmailAvailability
	 */
	@GetMapping("/user/checkEmailAvailability")
	public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
		Boolean isAvailable = !userRepository.existsByEmail(email);
		return new UserIdentityAvailability(isAvailable);
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation getUserProfile
	 */
	@GetMapping("/users/{username}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

		UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getFirst_name(),
				user.getLast_name(), user.getCreatedAt());

		return userProfile;
	}

}