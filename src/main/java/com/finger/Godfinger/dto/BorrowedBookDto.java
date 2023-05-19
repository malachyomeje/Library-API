package com.finger.Godfinger.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BorrowedBookDto {
    private  String title;
    private String isbn;
    private String studentEmail;
}
