package lend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lend.dto.UserDto;
import lend.entity.User;

@Service
public interface UserService {
	void saveUser(UserDto userDto);
	
	User findUserByEmail(String email);
	
    List<UserDto> findAllUsers();
}
