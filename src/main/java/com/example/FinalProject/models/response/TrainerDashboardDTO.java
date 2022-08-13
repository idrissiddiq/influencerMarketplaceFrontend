package com.example.FinalProject.models.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainerDashboardDTO {
    private Long jmlClass;
    private Long jmlSubject;
    private Long jmlPeserta;
}
