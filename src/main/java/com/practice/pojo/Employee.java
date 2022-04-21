package com.practice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer empID;
    private String empName;
    private String empPassword;
    private String empPhone;
    private String empMail;
    private Integer deptID;
    private Department eDepartment;

}
