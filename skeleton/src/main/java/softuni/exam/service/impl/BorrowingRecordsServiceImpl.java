package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.borrowingRecords.BorrowingRecordExportDto;
import softuni.exam.models.dto.borrowingRecords.BorrowingRecordImportWrapperDto;
import softuni.exam.models.entity.Book;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.BookRepository;
import softuni.exam.repository.BorrowingRecordRepository;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.BorrowingRecordsService;
import softuni.exam.util.ValidationUtils;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

import static softuni.exam.models.entity.Genres.SCIENCE_FICTION;
import static softuni.exam.util.Messages.INVALID_IMPORT_MESSAGE_FORMAT;
import static softuni.exam.util.Messages.VALID_IMPORT_MESSAGE_FORMAT;
import static softuni.exam.util.Paths.BORROWING_RECORD_XML_IMPORT_PATH;

@Service
public class BorrowingRecordsServiceImpl implements BorrowingRecordsService {
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookRepository bookRepository;
    private final LibraryMemberRepository libraryMemberRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtils validationUtils;

    @Autowired
    public BorrowingRecordsServiceImpl(BorrowingRecordRepository borrowingRecordRepository, BookRepository bookRepository, LibraryMemberRepository libraryMemberRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtils validationUtils) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.libraryMemberRepository = libraryMemberRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtils = validationUtils;
    }

    @Override
    public boolean areImported() {
        return borrowingRecordRepository.count() > 0;
    }

    @Override
    public String readBorrowingRecordsFromFile() throws IOException {
        return Files.readString(BORROWING_RECORD_XML_IMPORT_PATH);
    }

    @Override
    public String importBorrowingRecords() throws IOException, JAXBException {
        final StringBuilder stringBuilder = new StringBuilder();

        this.xmlParser.fromFile(BORROWING_RECORD_XML_IMPORT_PATH, BorrowingRecordImportWrapperDto.class).getBorrowingRecords()
                .forEach(recordDto -> {
                    final String bookTitle = recordDto.getBook().getTitle();
                    final LocalDate borrowDate = recordDto.getBorrowDate();
                    final long memberId = recordDto.getMember().getId();

                    final Optional<Book> book = this.bookRepository.findByTitle(bookTitle);
                    final Optional<LibraryMember> libraryMember = this.libraryMemberRepository.findById(memberId);

                    if (this.validationUtils.isValid(recordDto) && book.isPresent() && libraryMember.isPresent()) {

                        final BorrowingRecord borrowingRecord = modelMapper.map(recordDto, BorrowingRecord.class);
                        borrowingRecord.setBook(book.get());
                        borrowingRecord.setMember(libraryMember.get());

                        this.borrowingRecordRepository.saveAndFlush(borrowingRecord);

                        stringBuilder.append(String.format(VALID_IMPORT_MESSAGE_FORMAT,
                                "borrowing record",
                                bookTitle,
                                borrowDate));

                        return;
                    }

                    stringBuilder.append(String.format(INVALID_IMPORT_MESSAGE_FORMAT, "borrowing record"));
                });

        return stringBuilder.toString().trim();
    }

    @Override
    public String exportBorrowingRecords() {
        return borrowingRecordRepository.findAllByBorrowDateBeforeAndBookGenreOrderByBorrowDateDesc(LocalDate.of(2021, 9, 10), SCIENCE_FICTION)
                .stream()
                .map(record -> modelMapper.map(record, BorrowingRecordExportDto.class))
                .map(BorrowingRecordExportDto::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
