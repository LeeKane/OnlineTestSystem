package dao;

import bean.Exam;
import bean.Student;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * Created by nick on 2017/12/7.
 */
public interface ExamDao {
    public void createExam(Exam exam);

    public Exam updateExam(int examid, String exam_title, int question_num, int question_score, Timestamp start_time, Timestamp end_time);

    public void uploadStudent(Set<Student> students);
}
