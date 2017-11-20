package com.kaishengit.controller;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {
    String accessKey = "AopW6jDWoGJM_HjV3zaA1Rr77Tl_pgkK2KnNy5ZK";
    String secreKey = "o1zzCmG_JalqW2QcGuXI8oec1HSq0KJfflJOZp0D";
    String bucket = "qiniu";

    @GetMapping("/index")
    public String index (Model model){
        Auth auth = Auth.create(accessKey,secreKey);
        StringMap putPolicy = new StringMap();
//        魔法变量 七牛给出有解释
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        long expireSeconds = 3600;
        String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
        model.addAttribute("upToken",upToken);
        return "index";
    }
}
