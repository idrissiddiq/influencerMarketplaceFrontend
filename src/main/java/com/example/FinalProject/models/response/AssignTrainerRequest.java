package com.example.FinalProject.models.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Idris Siddiq
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AssignTrainerRequest {
    private Long employeeId;
    private String subjectId;
    private String classId;
}
