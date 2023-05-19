package com.finger.Godfinger.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BorrowEBooks {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private  Long borrowedBookId;
    @ManyToOne()
    @JoinColumn( name = "student id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey( name="student_book_id"))
    private Student student;

}
