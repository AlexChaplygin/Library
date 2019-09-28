package com.alex.che.library.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ReaderDTO {
    private Long id;
    private String name;
    private Set<BookDTO> books;
}
