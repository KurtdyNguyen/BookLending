package lend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import lend.entity.Book;
import lend.service.BookService;
import lend.service.UserService;

@Controller
public class BookController {
	private final BookService bookService;
	private final UserService userService;
	
	@Autowired
	public BookController(BookService bookService, UserService userService) {
		this.bookService = bookService;
		this.userService = userService;
	}
	
	@GetMapping("/book")
	public String listBooks(Model model) {
		List<Book> Books = bookService.getBooks();
		model.addAttribute("books", Books);
		return "book";
	}
	
	
}
