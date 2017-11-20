package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Disk;
import com.kaishengit.crm.service.DiskService;
import com.kaishengit.web.result.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/disk")
public class DiskController {
    @Autowired
    DiskService diskService;

    /**
     * 跳转到公司网盘页面
     * @param model
     * @param pid
     * @return
     */
    @GetMapping("/company/{pid:\\d+}")
    public String toMyDisk(Model model, @PathVariable Integer pid){
        //获取数据库中的文件
        List<Disk> diskList = diskService.findFileByPid(pid);
        model.addAttribute("diskList",diskList);
        model.addAttribute("pid",pid);
        return "disk/disk";
    }
    @GetMapping("/company/create/{pid:\\d+}/dir")
    @ResponseBody
    public AjaxResult createDir(@PathVariable Integer pid, @RequestParam String folderName, HttpSession httpSession){
        Account account = (Account) httpSession.getAttribute("account");
        //创建该文件夹
        diskService.createDir(pid,folderName,account.getUserId());
        //根据pid获取所有文件夹信息
        List diskList = diskService.findFileByPid(pid);
        return AjaxResult.successWithData(diskList);
    }

}
