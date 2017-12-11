package bean;

/**
 * Created by nick on 2017/12/6.
 */
public class Student {
    private int studentid;
    private String student_name;
    private String email;
    private int grade;
    private int class_num;

    public Student(int studentid, String student_name, String email, int grade, int class_num) {
        this.studentid = studentid;
        this.student_name = student_name;
        this.email = email;
        this.grade = grade;
        this.class_num = class_num;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getClass_num() {
        return class_num;
    }

    public void setClass_num(int class_num) {
        this.class_num = class_num;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        return studentid;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Student){
            Student s = (Student) obj;
            return this.studentid==s.getStudentid();
        }else
            return false;
    }
}
