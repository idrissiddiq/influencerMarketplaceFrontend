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
public class SubSTSTrainer {
    private String fullname;
    private double SelectStatement;
    private double SortingData ;
    private double SingleRowFunction ; 
    private double GroupFunction ;
    private double Subquery;
    private double RestTemplate;
    private double Ajax;
    private double Thymleaf;
    private double Security;
    private double JPA;
    private double MVC;
    private double DTO;
    private double TugasAkhir;
    private String gradeName;
}
