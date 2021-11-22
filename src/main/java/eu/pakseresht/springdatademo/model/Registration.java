package eu.pakseresht.springdatademo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Registration {

    /**
     * I could have used ManyToMany or a Composite-Key to map Student and Course, but I find a "real" Entity more
     * corresponding to the actual database model, and more convenient to add the Student's score for their Course.
     */
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    public Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    public Course course;

    /**
     * We can recorde Student's course score here
     */
    private int score;

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

}
