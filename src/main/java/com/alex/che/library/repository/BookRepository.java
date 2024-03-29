package com.alex.che.library.repository;

import com.alex.che.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookById(Long id);

    void deleteBookById(Long id);
}
