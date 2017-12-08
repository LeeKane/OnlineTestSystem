package dao;

import bean.Exam;

import java.sql.Date;

/**
 * Created by nick on 2017/12/7.
 */
public interface ExamDao {
    public void createExam(Exam exam);

    public Exam updateExam(int examid, String exam_title, int question_num, int question_score, Date start_time, Date end_time);
}
