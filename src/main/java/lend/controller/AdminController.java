package lend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
	
	@GetMapping("/admin/users")
	public String editUser() {
		return "users_admin";
	}
}
