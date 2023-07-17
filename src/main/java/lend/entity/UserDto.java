package lend.entity;
import java.util.List;

import lend.security.Role;

public record UserDto(
	Integer id,
	String userName,
	String email,
	Role role,
	List<Book> lentBooks,
	List<Book> borrowedBooks
) {
	
}