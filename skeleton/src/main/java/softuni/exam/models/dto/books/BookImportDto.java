package softuni.exam.models.dto.books;

import softuni.exam.models.entity.Genres;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookImportDto {
    @NotNull
    @Size(min = 3, max = 40)
    private String title;

    @NotNull
    @Size(min = 3, max = 40)
    private String author;

    @NotNull
    @Size(min = 5)
    private String description;

    @NotNull
    private boolean available;

    @NotNull
    private Genres genre;

    @NotNull
    @Min(1)
    private Double rating;

    public BookImportDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Genres getGenre() {
        return genre;
    }

    public void setGenre(Genres genre) {
        this.genre = genre;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
