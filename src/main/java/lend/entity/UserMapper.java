package lend.entity;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements Function<User, UserDto>{
	
	@Override
	public UserDto apply(User user) {
		return new UserDto(
				user.getId(),
				user.getUsername(),
				user.getEmail(),
				user.getRole(),
				user.getLentBooks(),
				user.getBorrowedBooks()
		);
	}
}
