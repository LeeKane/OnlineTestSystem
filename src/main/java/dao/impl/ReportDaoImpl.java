package dao.impl;

import bean.Exam;
import bean.PersonalReportInShort;
import bean.Student;
import bean.StudentExam;
import dao.ReportDao;
import org.springframework.transaction.annotation.Transactional;
import util.EmailUtil;
import util.RandomUtil;

import java.util.*;

/**
 * Created by nick on 2017/12/9.
 */
public class ReportDaoImpl extends BaseDaoImpl implements ReportDao {

    @Override
    public void createReport(Set<Student> students, String examid) {
        for (Student student : students) {
            Map<String, Object> map = new HashMap<>();
            map.put("examid", examid);
            map.put("studentid", student.getStudentid());
            sqlSession.insert("report.createReport", map);
        }
    }

    @Override
    public void generateRandomQuestionAndCode(Set<Student> students, String examid) {
        Map<String, Object> m = sqlSession.selectOne("report.getExamQuestionInfo", examid);
        int question_num = (int) m.get("num");
        long question_count = (long) m.get("coun");
        Exam exam = sqlSession.selectOne("exam.getExamById", Integer.parseInt(examid));

        for (Student s : students) {
            String questions = RandomUtil.randomQuestion(question_num, question_count);
            String possible_answers = RandomUtil.randomPossibleAnswer(question_num);
            String code = RandomUtil.randomCode(s.getStudentid() + "", examid);
            System.out.println(questions + "))))))))))" + possible_answers + "(((((((((" + code);
            Map<String, Object> map = new HashMap<>();
            map.put("examid", Integer.parseInt(examid));
            map.put("studentid", s.getStudentid());
            map.put("questions", questions);
            map.put("possible_answers", possible_answers);
            map.put("exam_code", code);
            sqlSession.update("report.updateReportInfo", map);
            EmailUtil.sendCode(s.getEmail(), code, exam, s);
        }
    }

    @Override
    public void updateReport(int examID, int studentID, String answerString, int score) {
        Map<String, Object> map = new HashMap<>();
        map.put("examid", examID);
        map.put("studentid", studentID);
        map.put("answers", answerString);
        map.put("score", (int) score);
        sqlSession.update("report.updateReportAnswer", map);
    }

    @Override
    public int getQuestionScore(int examid) {
        return sqlSession.selectOne("report.getQuestionScore", examid);
    }

    @Override
    public void updateQuitStatus() {

    }

    @Override
    public List<PersonalReportInShort> getScoreList(int examID) {
        List<PersonalReportInShort> scoreList = new ArrayList<>();
        List<Map<String, Object>> mList = sqlSession.selectList("report.getScoreList", examID);
        for (Map<String, Object> m : mList) {
            PersonalReportInShort report = new PersonalReportInShort();
            int studentID = (int) m.get("studentid");
            report.setStudentID(studentID);
            report.setGrade((int) m.get("score"));
            report.setStudentName(getStudentName(studentID));
            scoreList.add(report);
        }

        return scoreList;
    }

    @Override
    public Map<String, Object> getScoreAndAnswer(int examID, int studentID) {
        Map<String, Object> map = new HashMap<>();
        map.put("examid", examID);
        map.put("studentid", studentID);
        return sqlSession.selectOne("report.getScoreAndAnswer", map);
    }

    @Override
    public String getStudentName(int studentID) {
        return sqlSession.selectOne("report.getStudentName", studentID);
    }

    @Override
    public String getExamTitle(int examID) {
        return sqlSession.selectOne("report.getExamTitle", examID);
    }

    @Transactional
    @Override
    public StudentExam getIDByCode(String code) throws Exception{
        Map<String, Object> m = sqlSession.selectOne("report.getID", code);
        int examID = (int) m.get("examid");
        int studentid = (int) m.get("studentid");
        String questionQuery = (String) m.get("questions");
        String answerQuery = (String) m.get("possible_answers");

        StudentExam exam = new StudentExam();
        exam.setExamID(examID);
        exam.setStudentID(studentid);
        exam.setQuestionQuery(questionQuery);
        exam.setAnswerQuery(answerQuery);
        return exam;
    }

    @Transactional
    @Override
    public StudentExam getQueryByIDs(int examID, int studentID) {
        Map<String, Object> map = new HashMap<>();
        map.put("examid", examID);
        map.put("studentid", studentID);

        Map<String, Object> m = sqlSession.selectOne("report.getQuery", map);
        String questionQuery = (String) m.get("questions");
        String answerQuery = (String) m.get("possible_answers");

        StudentExam exam = new StudentExam();
        exam.setExamID(examID);
        exam.setStudentID(studentID);
        exam.setQuestionQuery(questionQuery);
        exam.setAnswerQuery(answerQuery);

        return exam;
    }

}
