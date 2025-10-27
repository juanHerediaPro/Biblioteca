package com.biblioteca.Biblioteca.Services;

import com.biblioteca.Biblioteca.Dtos.BookDto;
import com.biblioteca.Biblioteca.Entities.Author;
import com.biblioteca.Biblioteca.Entities.Book;
import com.biblioteca.Biblioteca.Entities.Category;
import com.biblioteca.Biblioteca.MapperDtos.BookMapper;
import com.biblioteca.Biblioteca.Repository.IAuthorRepository;
import com.biblioteca.Biblioteca.Repository.IBookRepository;
import com.biblioteca.Biblioteca.Repository.ICategoryRepository;
import com.biblioteca.Biblioteca.ServiceInterfaces.IAuthorInterface;
import com.biblioteca.Biblioteca.ServiceInterfaces.IBookInterface;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookInterface {

    private final IBookRepository iBookRepository;
    private final IAuthorRepository iAuthorRepository;
    private final ICategoryRepository iCategoryRepository;
    private final BookMapper bookMapper;

    Map<String, Object> response = new HashMap<>();

    public BookService(IBookRepository iBookRepository, IAuthorRepository iAuthorRepository, ICategoryRepository iCategoryRepository, BookMapper bookMapper) {
        this.iBookRepository = iBookRepository;
        this.iAuthorRepository = iAuthorRepository;
        this.iCategoryRepository = iCategoryRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> createBook(BookDto bookDto) {
        try {

            if (bookDto == null) {
                return ResponseEntity.badRequest().body("The book data cannot be null");
            }

            if (bookDto.getName() == null || bookDto.getName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("The name of the book is required");
            }

            if (iBookRepository.existsByName(bookDto.getName().trim())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("There is already a book with this name");
            }

            Book book = bookMapper.toEntity(bookDto);

            if (bookDto.getAuthors() != null && !bookDto.getAuthors().isEmpty()) {
                List<Long> authorIds = bookDto.getAuthors().stream()
                        .map(Author::getId)
                        .collect(Collectors.toList());

                List<Author> authors = iAuthorRepository.findAllById(authorIds);

                if (authors.size() != authorIds.size()) {
                    return ResponseEntity.badRequest()
                            .body("Some authors do not exist");
                }

                book.setAuthors(authors);
            }

            if (bookDto.getCategories() != null && !bookDto.getCategories().isEmpty()) {
                List<Long> categoryIds = bookDto.getCategories().stream()
                        .map(Category::getId)
                        .collect(Collectors.toList());

                List<Category> categories = iCategoryRepository.findAllById(categoryIds);

                if (categories.size() != categoryIds.size()) {
                    return ResponseEntity.badRequest()
                            .body("Some categories do not exist");
                }

                book.setCategories(categories);
            }

            Book savedBook = iBookRepository.save(book);
            BookDto responseDto = bookMapper.toDto(savedBook);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error creating book: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> getAllBooks(){
        List<Book> books = iBookRepository.findAll();
        if (books.isEmpty()) {
            return new ResponseEntity<>("No books found", HttpStatus.NOT_FOUND);
        }

        List<BookDto> bookDtos = books.stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(bookDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getBookByName(String name){
        Optional<Book> book = iBookRepository.findByName(name.trim());
        if (book.isEmpty()) {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
        BookDto bookDtos = bookMapper.toDto(book.get());
        return new ResponseEntity<>(bookDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteBookByName(String name){
        Optional<Book> book = iBookRepository.findByName(name.trim());
        if (book.isEmpty()) {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
        iBookRepository.delete(book.get());
        response.put("message", "Book deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
