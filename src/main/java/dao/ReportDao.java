package dao;

import bean.PersonalReportInShort;
import bean.Student;
import bean.StudentExam;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by nick on 2017/12/9.
 */
public interface ReportDao {

    public void createReport(Set<Student> students, String examid);

    public void generateRandomQuestionAndCode(Set<Student> students, String examid);

    public void updateReport(int examID, int studentID, String answerString, int score);

    public StudentExam getIDByCode(String code) throws Exception;

    public StudentExam getQueryByIDs(int examID, int studentID);

    public int getQuestionScore(int examid);

    public void updateQuitStatus();

    public List<PersonalReportInShort> getScoreList(int examID);

    public Map<String, Object> getScoreAndAnswer(int examID, int studentID);

    public String getStudentName(int studentID);

    public String getExamTitle(int examID);

}
