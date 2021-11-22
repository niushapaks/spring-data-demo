package eu.pakseresht.springdatademo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    @Id
    private int id;
    private String name;

    public List<Registration> getCoursesRegistered() {
        return coursesRegistered;
    }

    /**
     * I chose Cascade ALL because I don't want to keep registrations linked to my student, when I create a student
     * and when I delete it (to avoid orphan registration records).
     **/
    @OneToMany(mappedBy = "student", cascade = {CascadeType.ALL})
    private List<Registration> coursesRegistered= new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

}
