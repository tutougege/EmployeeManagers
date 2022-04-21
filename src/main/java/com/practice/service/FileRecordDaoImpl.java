package com.practice.service;

import com.practice.pojo.FileRecord;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FileRecordDaoImpl implements FileRecordDao{
    @Autowired
    FileRecordDao fileRecordDao;
    @Override
    public int addRecord(FileRecord fileRecord) {
        return fileRecordDao.addRecord(fileRecord);
    }

    @Override
    public List<FileRecord> selectRecord(Integer empID) {
        return fileRecordDao.selectRecord(empID);
    }

    @Override
    public int deleteRecord(Integer recordID) {
        return fileRecordDao.deleteRecord(recordID);
    }


}
