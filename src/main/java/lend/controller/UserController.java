package lend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lend.dto.UserDto;
import lend.entity.User;
import lend.service.UserService;

@Controller
public class UserController {
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/index")
	public String showHomePage() {
		return "index";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@GetMapping("/login/denied")
	public String showAccessDenied() {
		return "access_denied";
	}
	
	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return "register";
		
	}
	
	@PostMapping("/register/save")
	public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model){
		User existingUser = userService.findUserByEmail(userDto.getEmail());
		
		if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
			result.rejectValue("email", null, "There is already an account registered with the same email");
		}
		
		if(result.hasErrors()){
			model.addAttribute("user", userDto);
			return "/register";
		}
		
		userService.saveUser(userDto);
		return "redirect:/register?success";
	}
	
	@GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
