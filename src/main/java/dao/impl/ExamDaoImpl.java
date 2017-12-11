package dao.impl;

import bean.Exam;
import bean.PossibleAnswer;
import bean.Question;
import bean.Student;
import dao.ExamDao;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by nick on 2017/12/7.
 */
public class ExamDaoImpl extends BaseDaoImpl implements ExamDao {
    @Transactional
    @Override
    public void createExam(Exam exam) {
        sqlSession.insert("exam.insertExam",exam);
        List<Question> questions = exam.getQuestions();
        int id = sqlSession.selectOne("exam.maxId");
        for (Question question:questions){
            question.setExamid(id);
            sqlSession.insert("exam.insertQuestion",question);
            List<PossibleAnswer> possibleAnswers = question.getPossibleAnswers();
            for (PossibleAnswer pa:possibleAnswers){
                Map<String,Object> map = new HashMap<>();
                map.put("examid",id);
                map.put("question_num",question.getQuestion_num());
                map.put("possible_answer_num",pa.getPossible_answer_num());
                map.put("description",pa.getDescription());
                sqlSession.insert("exam.insertPossibleAnswer",map);
            }
        }
    }

    @Transactional
    @Override
    public Exam updateExam(int examid, String exam_title, int question_num, int question_score, Timestamp start_time, Timestamp end_time) {
        Map<String,Object> map = new HashMap<>();
        map.put("examid",examid);
        map.put("exam_title",exam_title);
        System.out.println(exam_title);
        map.put("question_num",question_num);
        map.put("question_score",question_score);
        map.put("start_time",start_time);
        map.put("end_time",end_time);
        sqlSession.update("exam.updateExamInfo",map);
        Exam exam =sqlSession.selectOne("exam.getExamById",examid);
        List<Question> questions = sqlSession.selectList("exam.getAllQuestions",examid);
        for(Question question:questions){
            Map<String,Object> map2 = new HashMap<>();
            map2.put("examid",examid);
            map2.put("question_num",question.getQuestion_num());
            List<Map<String,Object>> pa=sqlSession.selectList("exam.getAllPossibleAnswer",map2);
            for (Map<String,Object> m:pa){
                question.addPossibleAnswer(new PossibleAnswer((int)(m.get("possible_answer_num")),(String)(m.get("description"))));
            }
        }
        exam.addAll(questions);
        return exam;
    }

    @Override
    public void uploadStudent(Set<Student> students) {
        for (Student student: students) {
            sqlSession.insert("exam.insertStudent",student);
        }
    }


}
