package com.alex.che.library.service;

import com.alex.che.library.dto.ReaderDTO;
import com.alex.che.library.entity.Reader;
import com.alex.che.library.repository.ReaderRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService {

    private ReaderRepository readerRepository;
    private ModelMapper mapper;

    @Autowired
    public ReaderServiceImpl(ReaderRepository readerRepository,
                             ModelMapper mapper) {
        this.readerRepository = readerRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
    public ReaderDTO findReaderById(Long id) {
        Reader reader = readerRepository.findReaderById(id);
        return mapper.map(reader, ReaderDTO.class);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
    public List<ReaderDTO> findAllReaders() {
        List<Reader> readers = readerRepository.findAll();
        return mapper.map(readers, new TypeToken<List<ReaderDTO>>() {
        }.getType());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveReader(ReaderDTO readerDTO) {
        readerRepository.save(mapper.map(readerDTO, Reader.class));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteReaderById(Long id) {
        readerRepository.deleteReaderById(id);
    }
}
