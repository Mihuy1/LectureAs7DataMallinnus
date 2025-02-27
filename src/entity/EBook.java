package entity;

import jakarta.persistence.*;

@Entity
public class EBook extends Book {

    private String downloadLink;
}
