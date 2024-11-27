package com.example.book_library.reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService {
    @Autowired
    private ReaderRepository readerRepository;

    public Reader findReaderById(Integer id){
        return readerRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("this reader doesn't exist"));
    }

    public List<Reader> findAllReaders(){
        return readerRepository.findAll();
    }

    public Reader addReader(Reader reader){
        return readerRepository.save(reader);
    }

    public Reader alterReader(Integer id, Reader reader){
        Reader currentReader = readerRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("this reader doesn't exist"));

        currentReader.setFullname(reader.getFullname());
        currentReader.setAddress(reader.getAddress());
        currentReader.setEmail(reader.getEmail());
        currentReader.setPhoneNumber(reader.getPhoneNumber());

        return readerRepository.save(currentReader);
    }

    public void deleteReader(Integer id){
        readerRepository.deleteById(id);
    }
}
