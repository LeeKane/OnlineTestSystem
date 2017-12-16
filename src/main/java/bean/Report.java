package bean;

import java.util.List;

public class Report {
    private int examid;
    private String examTitle;
    List<PersonalReportInShort> scoreList;
    
    public int getExamid() {
        return examid;
    }

    public void setExamid(int examid) {
        this.examid = examid;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public List<PersonalReportInShort> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<PersonalReportInShort> scoreList) {
        this.scoreList = scoreList;
    }
}
