package lend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import lend.entity.Book;
import lend.service.BookService;

@Controller
public class BookController {
	private final BookService bookService;
	
	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping("/browser/book")
	public String listBooks(Model model) {
		List<Book> Books = bookService.getBooks();
		model.addAttribute("books", Books);
		return "list_book";
	}
	
	
}
