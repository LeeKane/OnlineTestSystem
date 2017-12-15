package dao.impl;

import bean.Exam;
import bean.Student;
import bean.StudentExam;
import dao.ReportDao;
import org.springframework.transaction.annotation.Transactional;
import util.EmailUtil;
import util.RandomUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    @Transactional
    @Override
    public StudentExam getIDByCode(String code) {
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

}
