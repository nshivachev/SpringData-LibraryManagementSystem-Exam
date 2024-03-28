package softuni.exam.models.dto.libraryMembers;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "member")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibraryMemberWithIdDto {
    @NotNull
    @XmlElement
    private Long id;

    public LibraryMemberWithIdDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
