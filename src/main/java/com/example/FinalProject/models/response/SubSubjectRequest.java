package com.example.FinalProject.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ASUS
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubSubjectRequest {
    private Long id;

    private String name;

    private double persentase;

    private Long subjectId;
}
