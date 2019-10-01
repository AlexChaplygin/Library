package com.alex.che.library.service;

import com.alex.che.library.configuration.ReaderBookMapper;
import com.alex.che.library.dto.BookDTO;
import com.alex.che.library.entity.Book;
import com.alex.che.library.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private ReaderBookMapper readerBookMapper;
    private BookRepository bookRepository;
    private ModelMapper mapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           ModelMapper mapper,
                           ReaderBookMapper readerBookMapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
        this.readerBookMapper = readerBookMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
    public BookDTO findBookById(Long id) {
        Book book = bookRepository.findBookById(id);
        return readerBookMapper.mapBook(book);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
    public List<BookDTO> findAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(readerBookMapper::mapBook).collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Book saveBook(BookDTO bookDTO) {
        bookDTO.setReleaseDate(new Date());
        return bookRepository.save(mapper.map(bookDTO, Book.class));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteBookById(Long id) {
        bookRepository.deleteBookById(id);
    }
}
