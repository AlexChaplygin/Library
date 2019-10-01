package com.alex.che.library.service;

import com.alex.che.library.dto.BookDTO;
import com.alex.che.library.entity.Book;

import java.util.List;

public interface BookService {

    BookDTO findBookById(Long id);

    List<BookDTO> findAllBooks();

    Book saveBook(BookDTO bookDTO);

    void deleteBookById(Long id);
}
