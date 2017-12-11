package service;

import bean.Exam;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by nick on 2017/12/6.
 */
public interface examService {
    public int uploadQuestions(List<List<String>> questions,String courseid);

    public Exam updateExamInfo(String examid, String exam_title, String question_num, String question_score, Timestamp start_time, Timestamp end_time);

    public void uploadStudent(List<List<String>> students,String examid);

}
