package dao;

import bean.Student;
import bean.StudentExam;

import java.util.Set;

/**
 * Created by nick on 2017/12/9.
 */
public interface ReportDao {

    public void createReport(Set<Student> students, String examid);

    public void generateRandomQuestionAndCode(Set<Student> students, String examid);

    public void updateReport(int examID, int studentID, String answerString, int score);

    public StudentExam getIDByCode(String code);

    public int getQuestionScore(int examid);

    public void updateQuitStatus();
}
