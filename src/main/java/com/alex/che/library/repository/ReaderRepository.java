package com.alex.che.library.repository;

import com.alex.che.library.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
    Reader findReaderById(Long id);

    void deleteReaderById(Long id);
}
