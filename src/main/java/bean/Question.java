package bean;

/**
 * Created by nick on 2017/12/6.
 */
public class Question {
    private int num;
    private String description;
    private PossibleAnswer answers[] = new PossibleAnswer[4];
    private int correct;

    private int index=0;

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
        if (index < 4) {
            answers[index] = answer;
            index++;
        }
    }

    public void clearAnswers(){
        answers = new PossibleAnswer[4];
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }
}
