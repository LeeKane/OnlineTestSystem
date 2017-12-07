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

    private int num;
    private String description;
    private List<PossibleAnswer> possibleAnswers = new ArrayList<>();
    private String correct;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addPossibleAnswer(PossibleAnswer answer){
        possibleAnswers.add(answer);
    }

    public void clearAnswers(){
        possibleAnswers = new ArrayList<>();
    }

    public List<PossibleAnswer> getPossibleAnswers() {
        return possibleAnswers;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }
}
