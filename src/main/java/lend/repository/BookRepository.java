package lend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lend.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByBookName(String name);
}
