package com.biblioteca.Biblioteca.MapperDtos;

import com.biblioteca.Biblioteca.Dtos.BookDto;
import com.biblioteca.Biblioteca.Entities.Author;
import com.biblioteca.Biblioteca.Entities.Book;
import com.biblioteca.Biblioteca.Entities.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public BookDto toDto(Book book) {
        BookDto dto = new BookDto();
        dto.setName(book.getName());
        dto.setYearOfPublications(book.getYearOfPublications());

        if (book.getAuthors() != null) {
            List<Author> authors = book.getAuthors().stream()
                    .map(author -> {
                        Author a = new Author();
                        a.setId(author.getId());
                        a.setName(author.getName());
                        a.setLastName(author.getLastName());
                        return a;
                    })
                    .collect(Collectors.toList());
            dto.setAuthors(authors);
        }

        if (book.getCategories() != null) {
            List<Category> categories = book.getCategories().stream()
                    .map(category -> {
                        Category c = new Category();
                        c.setId(category.getId());
                        c.setCategory(category.getCategory());
                        return c;
                    })
                    .collect(Collectors.toList());
            dto.setCategories(categories);
        }

        return dto;
    }

    public Book toEntity(BookDto dto) {
        Book book = new Book();
        book.setName(dto.getName());
        book.setYearOfPublications(dto.getYearOfPublications());

        return book;
    }

}
