package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.SaleChance;
import com.kaishengit.crm.entity.SaleChanceRecord;
import com.kaishengit.crm.example.SaleChanceRecordExample;
import com.kaishengit.crm.mapper.CustomerMapper;
import com.kaishengit.crm.mapper.SaleChanceMapper;
import com.kaishengit.crm.mapper.SaleChanceRecordMapper;
import com.kaishengit.crm.service.SaleChanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SaleChanceRecordServiceImpl implements SaleChanceRecordService{
    @Autowired
    SaleChanceRecordMapper saleChanceRecordMapper;
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    SaleChanceMapper saleChanceMapper;
    /**
     * 根据saleId查找所对应的销售机会对象
     * @param saleChanceId
     * @return
     */
    @Override
    public List<SaleChanceRecord> findBySale_id(Integer saleChanceId) {
        SaleChanceRecordExample saleChanceRecordExample = new SaleChanceRecordExample();
        saleChanceRecordExample.createCriteria().andSaleIdEqualTo(saleChanceId);
        List<SaleChanceRecord> saleChanceRecordList = saleChanceRecordMapper.selectByExample(saleChanceRecordExample);
        return saleChanceRecordList;
    }

    @Override
    public void updateProgress(Integer saleId, String progress) {
        //1.新增进度信息
        SaleChanceRecord saleChanceRecord = new SaleChanceRecord();
        saleChanceRecord.setSaleId(saleId);
        String context = "将当前进度修改为 ["+progress+"]";
        saleChanceRecord.setContent(context);
        saleChanceRecord.setCreateTime(new Date());
        saleChanceRecordMapper.insertSelective(saleChanceRecord);
        //2.更新客户的progress以及跟进时间
        SaleChance saleChance = saleChanceMapper.selectByPrimaryKey(saleId);
        Customer customer = customerMapper.selectByPrimaryKey(saleChance.getAccountId());
        customer.setLastChatTime(saleChanceRecord.getCreateTime());
        customerMapper.updateByPrimaryKey(customer);
    }

    @Override
    public void saveContext(String context, Integer saleChanceId) {
        //1.新增进度信息
        SaleChanceRecord saleChanceRecord = new SaleChanceRecord();
        saleChanceRecord.setSaleId(saleChanceId);
        saleChanceRecord.setContent(context);
        saleChanceRecord.setCreateTime(new Date());
        saleChanceRecordMapper.insertSelective(saleChanceRecord);
        //2.更新客户的跟进时间
        SaleChance saleChance = saleChanceMapper.selectByPrimaryKey(saleChanceId);
        Customer customer = customerMapper.selectByPrimaryKey(saleChance.getAccountId());
        customer.setLastChatTime(saleChanceRecord.getCreateTime());
        customerMapper.updateByPrimaryKey(customer);
    }
}
