package com.biblioteca.Biblioteca.Dtos;

import com.biblioteca.Biblioteca.Entities.Author;
import com.biblioteca.Biblioteca.Entities.Category;

import java.time.LocalDate;
import java.util.List;


public class BookDto {

    private String name;
    private LocalDate yearOfPublications;
    private List<Author> authors;
    private List<Category> categories;

    public BookDto() {

    }

    public BookDto(String name, LocalDate yearOfPublications, List<Author> authors, List<Category> categories) {
        this.name = name;
        this.yearOfPublications = yearOfPublications;
        this.authors = authors;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getYearOfPublications() {
        return yearOfPublications;
    }

    public void setYearOfPublications(LocalDate yearOfPublications) {
        this.yearOfPublications = yearOfPublications;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
