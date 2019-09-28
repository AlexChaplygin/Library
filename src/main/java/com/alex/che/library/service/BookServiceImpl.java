package com.alex.che.library.service;

import com.alex.che.library.dto.BookDTO;
import com.alex.che.library.entity.Book;
import com.alex.che.library.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private ModelMapper mapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           ModelMapper mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
    public BookDTO findBookById(Long id) {
        Book book = bookRepository.findBookById(id);
        return mapper.map(book, BookDTO.class);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
    public List<BookDTO> findAllBooks() {
        List<Book> books = bookRepository.findAll();
        return mapper.map(books, new TypeToken<List<BookDTO>>() {
        }.getType());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveBook(BookDTO bookDTO) {
        bookDTO.setDate(new Date());
        bookRepository.save(mapper.map(bookDTO, Book.class));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteBookById(Long id) {
        bookRepository.deleteBookById(id);
    }
}
