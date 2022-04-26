package com.practice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class FileRecord {
    private Integer recordID;
    private Integer empID;
    private String uploadFileName;
    private String uploadDate;
    private String downloadFile;
    private String downloadDate;

}
