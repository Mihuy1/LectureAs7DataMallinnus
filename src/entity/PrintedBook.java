package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class PrintedBook extends Book {

    private int pageCount;
}
