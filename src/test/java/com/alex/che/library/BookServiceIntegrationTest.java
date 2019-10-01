package com.alex.che.library;

import com.alex.che.library.dto.BookDTO;
import com.alex.che.library.entity.Book;
import com.alex.che.library.service.BookService;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@DatabaseSetup("/data.xml")
public class BookServiceIntegrationTest extends BaseTest {

    @Autowired
    private BookService bookService;

    @Test
    public void findAllBooksTest() {
        List<BookDTO> bookDTOS = bookService.findAllBooks();
        Assert.assertEquals(4, bookDTOS.size());
    }

    @Test
    public void findBookByIdTest() {
        BookDTO bookDTO = bookService.findBookById(1L);
        Assert.assertNotNull(bookDTO);
        Assert.assertEquals(1L, (long) bookDTO.getId());
    }

    @Test
    @Commit
    public void saveBookTest() {
        BookDTO book = BookDTO.builder().title("title10").author("author10").releaseDate(new Date()).build();
        Book b = bookService.saveBook(book);
        BookDTO bookDTO = bookService.findBookById(b.getId());
        Assert.assertNotNull(bookDTO);
        Assert.assertEquals((long) b.getId(), (long) bookDTO.getId());
    }

    @Test
    @Commit
    public void deleteBookByIdTest() {
        List<BookDTO> bookDTOSBeforeDelete = bookService.findAllBooks();
        bookService.deleteBookById(2L);
        List<BookDTO> bookDTOSAfterDelete = bookService.findAllBooks();
        Assert.assertEquals(bookDTOSBeforeDelete.size() - 1, bookDTOSAfterDelete.size());
    }

}
