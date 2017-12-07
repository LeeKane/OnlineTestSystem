package controller;

import Util.ExcelImportUtil;
import bean.Exam;
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
import java.sql.Date;
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
    @Autowired
    private examService examService;

    public service.examService getExamService() {
        return examService;
    }

    public void setExamService(service.examService examService) {
        this.examService = examService;
    }

    @ResponseBody
    @RequestMapping(value = "/uploadQuestion")
    public Map postUser (@RequestParam("file") MultipartFile excel,HttpServletRequest request,HttpServletResponse response,@RequestParam("start_time") Date start_time,@RequestParam("end_time") Date end_time){
        Map<String, Object> map = new HashMap<>();
        String courseid= request.getParameter("courseid");
        String exam_title=request.getParameter("exam_title");
        String question_num = request.getParameter("question_num");
        String question_score = request.getParameter("question_score");

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
        Exam result=examService.createExam(data,courseid,exam_title,question_num,question_score,start_time,end_time);

        map.put("exam",result);

        response.setHeader("Access-Control-Allow-Origin", "*");
        return map;
    }
}
