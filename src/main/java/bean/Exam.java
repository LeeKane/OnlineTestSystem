package bean;

import java.util.Date;

/**
 * Created by nick on 2017/12/6.
 */
public class Exam {
    private int examid;
    private String exam_title;
    private int question_number;
    private int question_score;
    private Date start_time;
    private Date end_time;

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

    public int getQuestion_number() {
        return question_number;
    }

    public void setQuestion_number(int question_number) {
        this.question_number = question_number;
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
}
