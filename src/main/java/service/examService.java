package service;

import bean.Exam;

import java.sql.Date;
import java.util.List;

/**
 * Created by nick on 2017/12/6.
 */
public interface examService {
    public Exam createExam(List<List<String>> questions,String courseid,String exam_title,String question_num,String question_score,Date start_time,Date end_time);
}
