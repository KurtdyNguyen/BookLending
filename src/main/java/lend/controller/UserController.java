package lend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lend.entity.User;
import lend.security.AuthenticationRequest;
import lend.security.AuthenticationResponse;
import lend.security.RegisterRequest;
import lend.service.AuthenticationService;
import lend.service.JwtService;
import lend.service.UserService;

@Controller
public class UserController {
	private final UserService userService;
	private final AuthenticationService authService;
	private final JwtService jwtService;
	
	@Autowired
	public UserController(UserService userService, AuthenticationService authService, JwtService jwtService) {
		this.userService = userService;
		this.authService = authService;
		this.jwtService = jwtService;
	}
	
	@ModelAttribute("registerRequest")
	public RegisterRequest registerRequest() {
		return new RegisterRequest();
	}
	
	@GetMapping("/index")
	public String showHomePage(@ModelAttribute("authenticatedUser") String email) {
		return "index";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(
			@ModelAttribute("authenticationRequest") AuthenticationRequest request
	) {
		return ResponseEntity.ok(authService.authenticate(request));
	}
	
	@GetMapping("/auth/login/denied")
	public String showAccessDenied() {
		return "access_denied";
	}
	
	@GetMapping("/register_form")
	public String register() {
		return "register";
	}
	
	@PostMapping("/register_form")
	public String register(@ModelAttribute("registerRequest") RegisterRequest request) {
		authService.register(request).getRefreshToken();
		return "redirect:/register_form?success";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users",	users);
		return "users";
	}
}
