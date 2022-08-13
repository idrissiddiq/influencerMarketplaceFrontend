package com.example.FinalProject.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KelasRequest {
    private String id;
    private String tipe;
    private Long silabusId;
    private String startDate;
    private String endDate;
}
