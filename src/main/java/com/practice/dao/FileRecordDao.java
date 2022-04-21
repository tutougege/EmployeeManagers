package com.practice.dao;

import com.practice.pojo.FileRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
@Mapper
public interface FileRecordDao {
    //插入文件上传下载记录
    int addRecord(FileRecord fileRecord);
    //查询文件上传下载记录
    List<FileRecord> selectRecord(Integer empID);
    //删除汇报
    int deleteRecord(@PathVariable("recordID") Integer recordID);
}
