package dao.impl;

import bean.Course;
import bean.Exam;
import bean.PossibleAnswer;
import bean.Question;
import dao.ExamDao;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                map.put("question_num",question.getNum());
                map.put("possible_answer_num",pa.getNum());
                map.put("description",pa.getDescription());
                sqlSession.insert("exam.insertPossibleAnswer",map);
            }
        }
    }
}
