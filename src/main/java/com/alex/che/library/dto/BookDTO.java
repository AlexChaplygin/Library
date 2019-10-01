package com.alex.che.library.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Europe/Moscow")
    private Date releaseDate;

    private ReaderDTO reader;
}
