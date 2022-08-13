/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class SSTSRapot {

    private Long SubsubjectId;

    private String SubsubjectName;

    private double Nilai;

    private String GradeName;

    private Long SubjectId;

}
