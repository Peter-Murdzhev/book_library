package com.example.book_library.reader;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {

    @Test
    void checkEmail(){
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
        Matcher matcher = pattern.matcher("mitko_molov@abv.bg");

        assertTrue(matcher.matches());
    }

    @Test
    void chechPhoneNumber(){
        Pattern pattern = Pattern.compile("^08[7-9][1-9]+[0-9]{6}$");
        Matcher matcher = pattern.matcher("0878127649");

        assertTrue(matcher.matches());
    }


}