package bean;

/**
 * Created by nick on 2017/12/6.
 */
public class PossibleAnswer {
    private int possible_answer_num;
    private String description;

    public int getPossible_answer_num() {
        return possible_answer_num;
    }

    public void setPossible_answer_num(int possible_answer_num) {
        this.possible_answer_num = possible_answer_num;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PossibleAnswer(int num, String description) {
        this.possible_answer_num = num;
        this.description = description;
    }


}
