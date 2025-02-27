package entity;

import jakarta.persistence.*;

@Entity
public class Biography {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String details;

    @OneToOne
    @MapsId
    @JoinColumn(name = "author_id")
    private Author author;

    public Biography() {

    }

    public Biography(String details) {
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Biography [id=" + id + ", details=" + details + "]";
    }
}
