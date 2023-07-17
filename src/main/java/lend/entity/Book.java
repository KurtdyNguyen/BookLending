package lend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "BOOK")
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String bookName;
    @Column(name = "AUTHOR")
    private String bookAuthor;
    @ManyToOne
    @JoinColumn(name = "LENDER_ID")
    private User lendUser;
    @ManyToOne
    @JoinColumn(name = "BORROWER_ID")
    private User borrowedUser;
    @Column(name = "DESCRIPTION")
    private String description;
}
