package com.alex.che.library.service;

import com.alex.che.library.dto.ReaderDTO;

import java.util.List;

public interface ReaderService {

    ReaderDTO findReaderById(Long id);

    List<ReaderDTO> findAllReaders();

    void saveReader(ReaderDTO readerDTO);

    void deleteReaderById(Long id);
}
