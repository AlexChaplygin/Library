package com.alex.che.library.configuration;

import com.alex.che.library.dto.BookDTO;
import com.alex.che.library.dto.ReaderDTO;
import com.alex.che.library.entity.Book;
import com.alex.che.library.entity.Reader;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ReaderBookMapper {

    public ReaderDTO mapReader(Reader reader) {

        if (reader.getBooks() != null)
            reader.getBooks().forEach(val -> val.setReader(null));

        return ReaderDTO.builder()
                .id(reader.getId())
                .books(reader.getBooks() != null ? mapBooks(reader.getBooks()) : null)
                .name(reader.getName())
                .build();
    }

    public Set<ReaderDTO> mapReaders(Set<Reader> readers) {
        return readers.stream().map(this::mapReader).collect(Collectors.toSet());
    }

    public BookDTO mapBook(Book book) {
        if (book.getReader() != null)
            book.getReader().setBooks(null);

        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .releaseDate(book.getReleaseDate())
                .reader(book.getReader() != null ? mapReader(book.getReader()) : null)
                .build();
    }

    public Set<BookDTO> mapBooks(Set<Book> books) {
        return books.stream().map(this::mapBook).collect(Collectors.toSet());
    }
}
