package com.example.test.books;

import androidx.annotation.NonNull;

public class BookModel {

    private Long id;
    private String name;
    private Integer year;


    public BookModel() {

    }

    public BookModel(Long id, String name, Integer year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public BookModel(String name, Integer year) {
        this.name = name;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }


    @NonNull
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                '}';
    }

    public String getNameAndYear() {
        return this.name + ", year: " + this.year + ";";
    }

}
