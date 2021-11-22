package eu.pakseresht.springdatademo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Course {

    @Id
    private int id;
    private String name;

    @OneToMany(mappedBy = "course")
    private List<Registration> registeredStudents;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
