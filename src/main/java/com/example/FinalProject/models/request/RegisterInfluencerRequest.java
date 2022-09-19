package com.example.FinalProject.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterInfluencerRequest {
    private Long id;

    private String fullname;

    private String email;
    private String city;
    private LocalDate birthDate;
    private String influenceType;
    private String username;
}
