package com.kaishengit.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


@Controller
public class Enter {
    @GetMapping("/enter/{pageNo:\\d+}")
    public String enter(@PathVariable int pageNo){

        return "user";
    }
    @PostMapping("/enter")
    public String enter(String userName,MultipartFile files){
        System.out.println(userName);
        return "redirect:/user";
    }
}
