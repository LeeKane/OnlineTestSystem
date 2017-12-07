package bean;

/**
 * Created by nick on 2017/12/6.
 */
public class PossibleAnswer {
    private int num;
    private String description;

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

    public PossibleAnswer(int num, String description) {
        this.num = num;
        this.description = description;
    }
}
