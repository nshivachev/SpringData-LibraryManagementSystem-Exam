package softuni.exam.models.dto.books;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class BookWithTitleDto {
    @NotNull
    @Size(min = 3, max = 40)
    @XmlElement
    private String title;

    public BookWithTitleDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
