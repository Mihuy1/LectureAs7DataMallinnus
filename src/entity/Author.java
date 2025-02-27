package entity;

import jakarta.persistence.*;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "author", cascade = CascadeType.ALL)
    private Biography biography;

    public Author() {

    }

    public Author(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBiography(Biography biography) {
        this.biography = biography;
    }

    public Biography getBiography() {
        return biography;
    }

    @Override
    public String toString() {
        return "Author [id=" + id + ", name=" + name + "]";
    }
}
