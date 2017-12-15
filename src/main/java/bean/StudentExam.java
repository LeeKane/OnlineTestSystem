package bean;

public class StudentExam {
    private int examID;
    private int studentID;
    private String questionQuery;
    private String answerQuery;

    public String getAnswerQuery() {
        return answerQuery;
    }

    public void setAnswerQuery(String answerQuery) {
        this.answerQuery = answerQuery;
    }

    public String getQuestionQuery() {
        return questionQuery;
    }

    public void setQuestionQuery(String questionQuery) {
        this.questionQuery = questionQuery;
    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
}
