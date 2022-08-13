/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.models;

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
public class SubSubjectTranscript {
    private Long id;
    private double nilai;
    private SubjectTranscript subjectTranscript;
    private SubSubject subsubject;
    private Grade grade;
}
