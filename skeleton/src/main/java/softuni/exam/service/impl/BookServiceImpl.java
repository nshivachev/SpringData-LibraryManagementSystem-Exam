package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.books.BookImportDto;
import softuni.exam.models.entity.Book;
import softuni.exam.repository.BookRepository;
import softuni.exam.service.BookService;
import softuni.exam.util.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import static softuni.exam.util.Messages.INVALID_IMPORT_MESSAGE_FORMAT;
import static softuni.exam.util.Messages.VALID_IMPORT_MESSAGE_FORMAT;
import static softuni.exam.util.Paths.BOOKS_JSON_IMPORT_PATH;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtils validationUtils;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper, Gson gson, ValidationUtils validationUtils) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtils = validationUtils;
    }


    @Override
    public boolean areImported() {
        return this.bookRepository.count() > 0;
    }

    @Override
    public String readBooksFromFile() throws IOException {
        return Files.readString(BOOKS_JSON_IMPORT_PATH);
    }

    @Override
    public String importBooks() throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();

        Arrays.stream(this.gson.fromJson(readBooksFromFile(), BookImportDto[].class))
                .forEach(bookDto -> {
                    if (this.validationUtils.isValid(bookDto)
                            && this.bookRepository.findByTitle(bookDto.getTitle()).isEmpty()) {

                        this.bookRepository.saveAndFlush(modelMapper.map(bookDto, Book.class));

                        stringBuilder.append(String.format(VALID_IMPORT_MESSAGE_FORMAT,
                                "book",
                                bookDto.getAuthor(),
                                bookDto.getTitle()));

                        return;
                    }

                    stringBuilder.append(String.format(INVALID_IMPORT_MESSAGE_FORMAT, "book"));
                });

        return stringBuilder.toString().trim();
    }
}
