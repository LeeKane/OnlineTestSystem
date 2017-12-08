package bean;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 * Created by nick on 2017/12/6.
 */
public class Exam {
    private int examid;
    private int courseid;

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }


    private String exam_title;
    private int question_num;
    private int question_score;
    private Date start_time;
    private Date end_time;
    private List<Question> questions = new ArrayList<>();

    public List<Question> getQuestions() {
        return questions;
    }

    public int getExamid() {
        return examid;
    }

    public void setExamid(int examid) {
        this.examid = examid;
    }

    public String getExam_title() {
        return exam_title;
    }

    public void setExam_title(String exam_title) {
        this.exam_title = exam_title;
    }

    public int getQuestion_num() {
        return question_num;
    }

    public void setQuestion_num(int question_num) {
        this.question_num = question_num;
    }

    public int getQuestion_score() {
        return question_score;
    }

    public void setQuestion_score(int question_score) {
        this.question_score = question_score;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public void addQuestion(Question question){
        questions.add(question);
    }

    public void clearQuestion(){
        questions= new ArrayList<>();
    }

    public void addAll(List<Question> questions){
        this.questions.addAll(questions);
    }
}
