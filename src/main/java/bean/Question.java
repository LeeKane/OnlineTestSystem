package bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nick on 2017/12/6.
 */
public class Question {
    private int examid;

    public int getExamid() {
        return examid;
    }

    public void setExamid(int examid) {
        this.examid = examid;
    }

    private int question_num;
    private String question_description;
    private List<PossibleAnswer> possibleAnswers = new ArrayList<>();
    private String correct_answer;

    public int getQuestion_num() {
        return question_num;
    }

    public void setQuestion_num(int question_num) {
        this.question_num = question_num;
    }

    public String getQuestion_description() {
        return question_description;
    }

    public void setQuestion_description(String question_description) {
        this.question_description = question_description;
    }

    public void addPossibleAnswer(PossibleAnswer answer){
        possibleAnswers.add(answer);
    }

    public void clearAnswers(){
        possibleAnswers = new ArrayList<>();
    }

    public void addAll(List<PossibleAnswer> possibleAnswers){
        this.possibleAnswers.addAll(possibleAnswers);
    }

    public List<PossibleAnswer> getPossibleAnswers() {
        return possibleAnswers;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }
}
