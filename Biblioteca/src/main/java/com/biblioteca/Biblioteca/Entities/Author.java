package com.biblioteca.Biblioteca.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "nationality_id")
    private AuthorsNationality nationality;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public Author() {
    }

    public Author(long id, String name, String lastName, AuthorsNationality nationality, List<Book> books) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.nationality = nationality;
        this.books = books;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AuthorsNationality getNationality() {
        return nationality;
    }

    public void setNationality(AuthorsNationality nationality) {
        this.nationality = nationality;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
