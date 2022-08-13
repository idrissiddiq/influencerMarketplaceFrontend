package com.example.FinalProject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sendy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Grade {
    private String name;

    private Long minScore;

    private Long maxScore;
}
