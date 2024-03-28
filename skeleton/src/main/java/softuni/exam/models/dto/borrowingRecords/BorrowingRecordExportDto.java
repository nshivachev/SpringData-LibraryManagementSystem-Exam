package softuni.exam.models.dto.borrowingRecords;

import softuni.exam.models.dto.books.BookWithTitleAndAuthorDto;
import softuni.exam.models.dto.libraryMembers.LibraryMemberWithFirstAndLastNameDto;

import java.time.LocalDate;

public class BorrowingRecordExportDto {
    private LocalDate borrowDate;
    private BookWithTitleAndAuthorDto book;
    private LibraryMemberWithFirstAndLastNameDto member;

    public BorrowingRecordExportDto() {
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public BookWithTitleAndAuthorDto getBook() {
        return book;
    }

    public void setBook(BookWithTitleAndAuthorDto book) {
        this.book = book;
    }

    public LibraryMemberWithFirstAndLastNameDto getMember() {
        return member;
    }

    public void setMember(LibraryMemberWithFirstAndLastNameDto member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "Book title: " + book.getTitle() + System.lineSeparator() +
                "*Book author: " + book.getAuthor() + System.lineSeparator() +
                "**Date borrowed: " + this.borrowDate + System.lineSeparator() +
                "***Borrowed by: " + member.getFirstName() + " " + member.getLastName();
    }
}
