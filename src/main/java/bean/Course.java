package bean;

/**
 * Created by nick on 2017/12/6.
 */
public class Course {
    private int courseid;
    private String course_name;

    public Course(int courseid, String course_name) {
        this.courseid = courseid;
        this.course_name = course_name;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
}
