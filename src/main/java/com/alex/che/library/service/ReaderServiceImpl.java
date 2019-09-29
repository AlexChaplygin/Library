package com.alex.che.library.service;

import com.alex.che.library.configuration.ReaderBookMapper;
import com.alex.che.library.dto.ReaderDTO;
import com.alex.che.library.entity.Reader;
import com.alex.che.library.repository.ReaderRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReaderServiceImpl implements ReaderService {

    private ReaderRepository readerRepository;
    private ReaderBookMapper readerBookMapper;
    private ModelMapper mapper;

    @Autowired
    public ReaderServiceImpl(ReaderRepository readerRepository,
                             ModelMapper mapper,
                             ReaderBookMapper readerBookMapper) {
        this.readerRepository = readerRepository;
        this.mapper = mapper;
        this.readerBookMapper = readerBookMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
    public ReaderDTO findReaderById(Long id) {
        Reader reader = readerRepository.findReaderById(id);
        return readerBookMapper.mapReader(reader);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
    public List<ReaderDTO> findAllReaders() {
        List<Reader> readers = readerRepository.findAll();
        return readers.stream().map(readerBookMapper::mapReader).collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveReader(ReaderDTO readerDTO) {
        readerRepository.save(mapper.map(readerDTO, Reader.class));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteReaderById(Long id) {
        Reader reader = readerRepository.findReaderById(id);
        reader.getBooks().forEach(val -> val.setReader(null));
        readerRepository.delete(reader);
    }
}
