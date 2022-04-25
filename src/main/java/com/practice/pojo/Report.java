package com.practice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.Date;
import java.util.Scanner;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report  {
    private Integer reporterID;
    private String reporterName;
    private String reportName;
    private String  reportDate;
    private String reportFile;

    public static void main(String[] args) {
    }
}
