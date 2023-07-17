package lend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lend.entity.*;
import lend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void saveUser(User user) {
		
		this.userRepository.save(user);		
	}

	@Override
	public User getUserById(Integer id) {
		Optional<User> temp = userRepository.findById(id);
		User user = null;
		if(temp.isPresent()) {
			user = temp.get();
		} else {
			throw new RuntimeException("User not found for id " + id);
		}
		return user;
	}

	@Override
	public void deleteUserById(Integer id) {
		this.userRepository.deleteById(id);		
	}
	
}
