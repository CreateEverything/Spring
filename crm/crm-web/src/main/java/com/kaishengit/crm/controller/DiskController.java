package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Disk;
import com.kaishengit.crm.service.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/disk")
public class DiskController {
    @Autowired
    DiskService diskService;

    @GetMapping("/home")
    public String toMyDisk(Model model){
        //获取数据库中的所有文件
        List<Disk> diskList = diskService.findAllFile();
        model.addAttribute("diskList",diskList);
        return "/disk/disk";
    }

}
