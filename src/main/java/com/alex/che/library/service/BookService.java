package com.alex.che.library.service;

import com.alex.che.library.dto.BookDTO;

import java.util.List;

public interface BookService {

    BookDTO findBookById(Long id);

    List<BookDTO> findAllBooks();

    void saveBook(BookDTO bookDTO);

    void deleteBookById(Long id);
}
