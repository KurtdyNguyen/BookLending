package lend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lend.entity.UserDto;
import lend.entity.User;

public interface UserService {
	List<User> getAllUsers();
	void saveUser(User user);
	User getUserById(Integer id);
	void deleteUserById(Integer id);
}
