package dao;

import bean.Student;

import java.util.List;
import java.util.Set;

/**
 * Created by nick on 2017/12/9.
 */
public interface ReportDao {

    public void createReport(Set<Student> students, String examid);

    public void generateRandomQuestionAndCode(Set<Student> students, String examid);
}
