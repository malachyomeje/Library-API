package com.finger.Godfinger.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentDto {
    private Long id;
    private String name;
    private String email;
    private String role;
}
