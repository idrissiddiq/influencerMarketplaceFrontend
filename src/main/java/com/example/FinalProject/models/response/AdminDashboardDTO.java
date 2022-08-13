package com.example.FinalProject.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminDashboardDTO {
    private Long jmlClass;
    private Long jmlTrainer;
    private Long jmlPeserta;
    private Long jmlSilabus;
}
