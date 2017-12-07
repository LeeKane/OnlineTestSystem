package service.impl;

import bean.Exam;
import bean.PossibleAnswer;
import bean.Question;
import dao.ExamDao;
import dao.impl.ExamDaoImpl;
import service.examService;

import java.sql.Date;
import java.util.List;

/**
 * Created by nick on 2017/12/6.
 */
public class examServiceImpl implements examService {
    public ExamDao getExamDao() {
        return examDao;
    }

    public void setExamDao(ExamDao examDao) {
        this.examDao = examDao;
    }

    private ExamDao examDao;

    @Override
    public Exam createExam(List<List<String>> questions, String courseid, String exam_title, String question_num, String question_score, Date start_time, Date end_time) {
        Exam exam = new Exam();
        exam.setCourseid(Integer.parseInt(courseid));
        exam.setExam_title(exam_title);
        exam.setQuestion_num(Integer.parseInt(question_num));
        exam.setQuestion_score(Integer.parseInt(question_score));
        exam.setStart_time(start_time);
        exam.setEnd_time(end_time);
        for (int i =0;i<questions.size();i++) {
            List<String> question = questions.get(i);
            Question q = new Question();

            String num = question.get(0);
            String description = question.get(1);
            String correct = question.get(6);
            q.setNum((int)(Double.parseDouble(num)));
            q.setDescription(description);
            q.setCorrect(correct);
            for(int k=2;k<=5;k++){
                PossibleAnswer pa = new PossibleAnswer(k-1,question.get(k));
                System.out.print(pa.getDescription()+"\t");
                q.addPossibleAnswer(pa);
            }
            System.out.println();

            exam.addQuestion(q);
        }

        examDao.createExam(exam);
//        exam.setExamid(id);
        return exam;
    }
}
