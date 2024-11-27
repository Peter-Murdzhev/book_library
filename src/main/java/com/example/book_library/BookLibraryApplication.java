package com.example.book_library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//proxyTargetClass = true
@EnableAsync()
//the app starts only with initiated maildev server
public class BookLibraryApplication {
	//that's not a real library name
	public static final String BOOK_LIBRARY_NAME = "Vasil Levski's Library, town of Smolyan";

	public static void main(String[] args) {
		SpringApplication.run(BookLibraryApplication.class, args);
	}

}
