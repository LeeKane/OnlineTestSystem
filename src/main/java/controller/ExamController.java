package controller;

import Util.ExcelImportUtil;
import bean.user;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.examService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Util.ExcelImportUtil.parseExcel;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * Created by nick on 2017/12/6.
 */
@Controller
public class ExamController {
//    @Autowired
//    private examService examService;

    //TODO GET改成POST
    @ResponseBody
    @RequestMapping(value = "/uploadQuestion")
    public Map postUser (@RequestParam("file") MultipartFile excel,HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();

        String path = "/data/wwwroot/default/data";
        //容错处理
        File dir = new File(path);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = excel.getOriginalFilename();//report.xls
        String fileName2 = excel.getName();//excelFile

        InputStream fis = null;
        try {
            fis = excel.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }



        List<List<String>> data = ExcelImportUtil.parseExcel(fis);

        map.put("data",data);

        response.setHeader("Access-Control-Allow-Origin", "*");
        return map;
    }
}
