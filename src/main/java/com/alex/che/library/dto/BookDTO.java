package com.alex.che.library.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private Date releaseDate;
    private ReaderDTO reader;
}
