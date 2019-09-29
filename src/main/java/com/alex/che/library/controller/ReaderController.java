package com.alex.che.library.controller;

import com.alex.che.library.dto.ReaderDTO;
import com.alex.che.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class ReaderController {

    private ReaderService readerService;

    @Autowired
    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping("/readers")
    public ResponseEntity<List<ReaderDTO>> getAllReaders() {
        return ResponseEntity.ok(readerService.findAllReaders());
    }

    @GetMapping("/reader/{id}")
    public ResponseEntity<ReaderDTO> getReaderById(@PathVariable Long id) {
        return ResponseEntity.ok(readerService.findReaderById(id));
    }

    @PostMapping("/reader/save")
    public ResponseEntity saveReader(@RequestBody ReaderDTO readerDTO) {
        readerService.saveReader(readerDTO);
        return ResponseEntity.ok("OK");
    }

    @RequestMapping(value = "/reader/delete/{id}", method = RequestMethod.DELETE)
    public void deleteReaderById(@PathVariable Long id) {
        readerService.deleteReaderById(id);
    }
}
