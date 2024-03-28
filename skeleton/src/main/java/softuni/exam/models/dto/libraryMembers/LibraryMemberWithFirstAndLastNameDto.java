package softuni.exam.models.dto.libraryMembers;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LibraryMemberWithFirstAndLastNameDto {
    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;

    public LibraryMemberWithFirstAndLastNameDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
