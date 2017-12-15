package controller;

import util.ExcelImportUtil;
import bean.Answer;
import bean.Answers;
import bean.Exam;
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
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nick on 2017/12/6.
 */

//TODO excel科学计数
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
    public Map uploadQuestion(@RequestParam("file") MultipartFile excel, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        String courseid = request.getParameter("courseid");


        String path = "/data/wwwroot/default/data";
        //容错处理
        File dir = new File(path);
        if (!dir.exists()) {
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
        int id = examService.uploadQuestions(data, courseid);

        int rowNum = data.size();
        map.put("examid", id);
        map.put("question_number", rowNum);

        response.setHeader("Access-Control-Allow-Origin", "*");
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/examInfo")
    public Map examInfo(HttpServletRequest request, HttpServletResponse response, @RequestParam("start_time") Timestamp start_time, @RequestParam("end_time") Timestamp end_time) {
        Map<String, Object> map = new HashMap<>();
        String examid = request.getParameter("examid");
        String exam_title = request.getParameter("exam_title");
        String question_num = request.getParameter("question_num");
        String question_score = request.getParameter("question_score");

        Exam exam = examService.updateExamInfo(examid, exam_title, question_num, question_score, start_time, end_time);

        map.put("result", exam);

        response.setHeader("Access-Control-Allow-Origin", "*");
        return map;
    }


    @ResponseBody
    @RequestMapping(value = "/uploadStudent")
    public Map uploadStudent(@RequestParam("file") MultipartFile students, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        String examid = request.getParameter("examid");
        String path = "/data/wwwroot/default/data";
        //容错处理
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = students.getOriginalFilename();//report.xls
        String fileName2 = students.getName();//excelFile

        InputStream fis = null;
        try {
            fis = students.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<List<String>> data = ExcelImportUtil.parseExcel(fis);

        examService.uploadStudent(data, examid);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/uploadAnswers", method = RequestMethod.POST)
    public Map postExam(@RequestBody Answers answers, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        int examID = answers.getExamID();
        int studentID = answers.getStudentID();
        List<Answer> answerList = answers.getAnswerList();
        int score = examService.updateAnswers(examID, studentID, answerList);
        result.put("score", score);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getExam", method = RequestMethod.GET)
    public Exam getExamByCode(@RequestParam("code") String code, HttpServletResponse response) {
        Exam exam = examService.getExamByCode(code);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return exam;
    }

    @ResponseBody
    @RequestMapping(value = "/getExamOverview", method = RequestMethod.GET)
    public Exam getExamOverview(@RequestParam("examID") int examID, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return null;
    }

}
