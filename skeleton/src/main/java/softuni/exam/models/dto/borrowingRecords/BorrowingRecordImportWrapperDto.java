package softuni.exam.models.dto.borrowingRecords;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "borrowing_records")
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingRecordImportWrapperDto {
    @NotNull
    @XmlElement(name = "borrowing_record")
    private List<BorrowingRecordImportDto> borrowingRecords;

    public BorrowingRecordImportWrapperDto() {
    }

    public List<BorrowingRecordImportDto> getBorrowingRecords() {
        return borrowingRecords;
    }

    public void setBorrowingRecords(List<BorrowingRecordImportDto> borrowingRecords) {
        this.borrowingRecords = borrowingRecords;
    }
}
