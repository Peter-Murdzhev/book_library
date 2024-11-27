package com.example.book_library.reader;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @GetMapping(value = "/reader/get/{id}")
    public ResponseEntity<?> findReaderById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(readerService.findReaderById(id));
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>("reader doesn't exist", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/reader/get/all")
    public List<Reader> getAllReaders(){
        return readerService.findAllReaders();
    }

    @PostMapping(value = "/reader/add")
    public ResponseEntity<?> addReader(@RequestBody @Valid Reader reader, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            HashMap<String, String> errors = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(readerService.addReader(reader));
        }
    }

    @PutMapping(value = "/reader/alter/{id}")
    public ResponseEntity<?> alterReader(@PathVariable Integer id, @RequestBody @Valid Reader reader,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            HashMap<String, String> errors = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try{
            return ResponseEntity.ok(readerService.alterReader(id, reader));
        }catch (IllegalArgumentException iae){
            return new ResponseEntity<>("this reader doesn't exist", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(value = "/reader/delete/{id}")
    public ResponseEntity<String> deleteReader(@PathVariable Integer id) {
        readerService.deleteReader(id);
        return ResponseEntity.ok("reader deleted");

    }
}
