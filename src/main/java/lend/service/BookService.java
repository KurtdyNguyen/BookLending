package lend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lend.entity.Book;
import lend.repository.BookRepository;

@Service
public class BookService{
	private final BookRepository bookRepository;
	
	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public List<Book> getBooks(){
		return bookRepository.findAll();
	}
	
	public void addBook(Book book) {
		bookRepository.save(book);		
	}
	
	public void deleteBook(Long id) {
		boolean exist = bookRepository.existsById(id);
		if(!exist) {
			throw new IllegalStateException("The book you want to delete doesn't exist.");
		}
		bookRepository.deleteById(id);
	}
}
