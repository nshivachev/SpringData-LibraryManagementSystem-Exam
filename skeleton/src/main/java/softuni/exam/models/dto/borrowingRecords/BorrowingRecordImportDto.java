package softuni.exam.models.dto.borrowingRecords;

import softuni.exam.models.dto.books.BookWithTitleDto;
import softuni.exam.models.dto.libraryMembers.LibraryMemberWithIdDto;
import softuni.exam.util.LocalDateAdapter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name = "borrowing_record")
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingRecordImportDto {
    @NotNull
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlElement(name = "borrow_date")
    private LocalDate borrowDate;

    @NotNull
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlElement(name = "return_date")
    private LocalDate returnDate;

    @Size(min = 3, max = 100)
    @XmlElement
    private String remarks;

    @NotNull
    @XmlElement
    private BookWithTitleDto book;

    @NotNull
    @XmlElement
    private LibraryMemberWithIdDto member;

    public BorrowingRecordImportDto() {
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public BookWithTitleDto getBook() {
        return book;
    }

    public void setBook(BookWithTitleDto book) {
        this.book = book;
    }

    public LibraryMemberWithIdDto getMember() {
        return member;
    }

    public void setMember(LibraryMemberWithIdDto member) {
        this.member = member;
    }
}
