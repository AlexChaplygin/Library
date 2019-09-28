package com.alex.che.library.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private Date date;
    private ReaderDTO reader;
}
