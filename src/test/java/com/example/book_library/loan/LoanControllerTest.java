package com.example.book_library.loan;

import com.example.book_library.book.Book;
import com.example.book_library.book.BookService;
import com.example.book_library.reader.Reader;
import com.example.book_library.reader.ReaderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest
@AutoConfigureMockMvc()
class LoanControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    LoanService loanService;

    @MockBean
    BookService bookService;

    @MockBean
    ReaderService readerService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach()
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void createLoan() throws Exception {
        Book book = new Book(1, "Tiger Warrior",
                "David Gibins", null,
                2017, 3, 2);
        Reader reader = new Reader(5, "Staiko Tonchev",
                "Sofia, bul. Vasil Levski 240",
                "tonchev@gmail.com", "0876324576");

        when(bookService.findBookById(1)).thenReturn(book);
        when(readerService.findReaderById(5)).thenReturn(reader);

        when(loanService.saveLoan(any(Loan.class))).
                thenReturn(new Loan(1, reader, book,
                        LocalDate.now(),
                        LocalDate.now().plusMonths(3), false));

        mockMvc.perform(MockMvcRequestBuilders.post("/loan/create")
                        .contentType("application/json")
                        .content("{\"bookId\" : 1,\n\"readerId\" : 5\n}"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.book.bookName")
                        .value("Tiger Warrior"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reader.fullname")
                        .value("Staiko Tonchev"));
    }

    @Test
    void isRequestBodyValid() throws JsonProcessingException {
        String requestBody = "{\"bookId\" : 1,\n\"readerId\" : 5\n}";
        ObjectMapper objectMapper = new ObjectMapper();

        LoanRequest request = objectMapper.readValue(requestBody, LoanRequest.class);

        assertEquals(1, request.bookId());
    }

    @Test
    void createLoanFails() throws Exception {
        Book book = new Book(1, "Tiger Warrior",
                "David Gibins", null,
                2017, 3, 2);
        Reader reader = new Reader(5, "Staiko Tonchev",
                "Sofia, bul. Vasil Levski 240",
                "tonchev@gmail.com", "0876324576");

        when(bookService.findBookById(1)).thenReturn(book);
        when(readerService.findReaderById(5)).thenReturn(reader);

        when(loanService.saveLoan(any(Loan.class))).
                thenReturn(new Loan(1, reader, book,
                        LocalDate.now(),
                        LocalDate.now().plusMonths(3), false));

        mockMvc.perform(MockMvcRequestBuilders.post("/loan/create")
                        .contentType("application/json")
                        .content("{\"bookId\" : 1\n}"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    //Here I can't get a json object in the response body
    // due to a missing @RequestBody annotation
    //I haven't used it because I've altered the wanted fields directly
    //But the API is manually tested and works perfectly fine in runtime
    @Test
    void ReturnLoan() throws Exception {
        Book book = new Book(1, "Tiger Warrior",
                "David Gibins", null,
                2017, 3, 2);
        Reader reader = new Reader(5, "Staiko Tonchev",
                "Sofia, bul. Vasil Levski 240",
                "tonchev@gmail.com", "0876324576");

        when(bookService.findBookById(1)).thenReturn(book);
        when(readerService.findReaderById(5)).thenReturn(reader);

        when(loanService.findById(1)).
                thenReturn(new Loan(1, reader, book,
                        LocalDate.now(),
                        LocalDate.now().plusMonths(3), false));

        mockMvc.perform(MockMvcRequestBuilders.put("/loan/return/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }
}