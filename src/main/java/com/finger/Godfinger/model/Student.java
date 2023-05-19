package com.finger.Godfinger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    @JsonIgnore
    @OneToMany(mappedBy = "student",
           orphanRemoval = true
           ,cascade = {CascadeType.ALL})
   private List <BorrowEBooks> listOfBorrowed;

}
