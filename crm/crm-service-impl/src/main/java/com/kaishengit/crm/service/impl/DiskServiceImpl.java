package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Disk;
import com.kaishengit.crm.example.DiskExample;
import com.kaishengit.crm.mapper.DiskMapper;
import com.kaishengit.crm.service.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiskServiceImpl implements DiskService{
    @Autowired
    DiskMapper diskMapper;
    @Override
    public List<Disk> findAllFile() {
        return diskMapper.selectByExample(new DiskExample());
    }

    @Override
    public List<Disk> findFileByPid(Integer pid) {
        DiskExample diskExample = new DiskExample();
        diskExample.createCriteria().andPidEqualTo(pid);
        return diskMapper.selectByExample(diskExample);
    }
}
