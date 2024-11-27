package com.example.book_library.reader;

import com.example.book_library.book.Book;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Reader {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            nullable = false
    )
    @Size(min = 8, message = "fullname must be at least 8 letters long")
    private String fullname;

    @Column(
            nullable = false,
            unique = true
    )
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "Please enter a valid Email!")
    private String email;

    @Column(
            nullable = false
    )
    @Size(min = 10)
    private String address;

    //"^08[7-9][1-9]+[0-9]{6}$" great regex if only bulgarian mobile phones are available
    //but readers could be with stationary number or foreign mobile number,
    //so I prefer using this below
    @Column(
            nullable = false
    )
    @Pattern(regexp = "^[0-9]+", message = "Please enter a valid phone number!")
    private String phoneNumber;

    public Reader(Integer id, String fullname, String address, String email,
                  String phoneNumber) {
        this.id = id;
        this.fullname = fullname;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Reader(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if(id != null){
            this.id = id;
        }
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        if(fullname != null){
            this.fullname = fullname;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if(address != null){
            this.address = address;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email != null){
            this.email = email;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber != null){
            this.phoneNumber = phoneNumber;
        }
    }
}
