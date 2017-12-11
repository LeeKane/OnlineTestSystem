package service.impl;

import bean.Exam;
import bean.PossibleAnswer;
import bean.Question;
import bean.Student;
import dao.ExamDao;
import dao.ReportDao;
import service.examService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private ReportDao reportDao;

    public ReportDao getReportDao() {
        return reportDao;
    }

    public void setReportDao(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Override
    public int uploadQuestions(List<List<String>> questions, String courseid) {
        Exam exam = new Exam();
        exam.setCourseid(Integer.parseInt(courseid));
        for (int i =0;i<questions.size();i++) {
            List<String> question = questions.get(i);
            Question q = new Question();

            String num = question.get(0);
            String description = question.get(1);
            String correct = question.get(6);
            q.setQuestion_num((int)(Double.parseDouble(num)));
            q.setQuestion_description(description);
            q.setCorrect_answer(correct);
            for(int k=2;k<=5;k++){
                PossibleAnswer pa = new PossibleAnswer(k-1,question.get(k));
                System.out.print(pa.getDescription()+"\t");
                q.addPossibleAnswer(pa);
            }
            System.out.println();

            exam.addQuestion(q);
        }

        examDao.createExam(exam);
        return exam.getExamid();
    }

    @Override
    public Exam updateExamInfo(String examid, String exam_title, String question_num, String question_score, Timestamp start_time, Timestamp end_time) {
        int id = Integer.parseInt(examid);
        int num = Integer.parseInt(question_num);
        int score = Integer.parseInt(question_score);
        return examDao.updateExam(id,exam_title,num,score,start_time,end_time);
    }

    @Override
    public void uploadStudent(List<List<String>> students, String examid) {
        Set<Student> studentList = new HashSet<>();
        for (int i=0;i<students.size();i++){
            List<String> student = students.get(i);
            if (student.size()>=5) {
                String name = student.get(0);
                String id_str = student.get(1);
                int id = (int) Double.parseDouble(id_str);
                String email = student.get(2);
                String grade_str = student.get(3);
                int grade = (int) Double.parseDouble(grade_str);
                String class_num_str=student.get(4);
                int class_num = (int) Double.parseDouble(class_num_str);
                Student st = new Student(id,name,email,grade,class_num);
                studentList.add(st);
            }
        }
        examDao.uploadStudent(studentList);
        reportDao.createReport(studentList,examid);
        reportDao.generateRandomQuestionAndCode(studentList,examid);
    }

}
