package eu.pakseresht.springdatademo.repository;

import eu.pakseresht.springdatademo.model.Course;
import eu.pakseresht.springdatademo.model.Registration;
import eu.pakseresht.springdatademo.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class StudentDAOTest {

    /**
     * This test class doesn't aim to test Spring Data / JPA / Hibernate but rather to provide a way to interact with
     * the JPA model and DAOs to ensure it fits with the DB schema.sql file.
     * Some initial datas are presents in the data.sql file
     * We also verify that our model corresponds to the specs.
     *
     * Hibernate is very powerful and makes a lot easier to write DB access than writing SQL by ourselves, but it doesn't
     * mean we could avoid to design our DB model because the data is actually persisted in a Relationnal database with
     * tables, columns, foreign keys and we have to think about it when we design our Hibernate entities and mappings.
     */

    @Autowired
    StudentDAO studentDAO;

    @Autowired
    CourseDAO courseDAO;

    @Autowired
    private EntityManager entityManager;

    /**
     * Query detail (generated by Hibernate) :
     *
     * INSERT INTO student (name, id) VALUES (?, ?)
     *
     * INSERT INTO registration (course_id, score, student_id, id) VALUES (?, ?, ?, ?)
     *
     * SELECT student0_.id as id1_2_0_, student0_.name as name2_2_0_
     * FROM student student0_
     * WHERE student0_.id=?
     *
     * SELECT coursesreg0_.student_id as student_4_1_0_, coursesreg0_.id as id1_1_0_, coursesreg0_.id as id1_1_1_, coursesreg0_.course_id as course_i3_1_1_, coursesreg0_.score as score2_1_1_, coursesreg0_.student_id as student_4_1_1_, course1_.id as id1_0_2_, course1_.name as name2_0_2_
     * FROM registration coursesreg0_
     * LEFT OUTER JOIN course course1_ ON coursesreg0_.course_id=course1_.id
     * WHERE coursesreg0_.student_id=?
     *
     */
    @Test
    public void it_should_be_possible_to_add_a_student_with_their_course_registrations(){
        //GIVEN
        Student student = new Student();
        student.setName("Héraclès");

        //WHEN
        Student savedStudent = studentDAO.save(student);
        Course savedCourse = courseDAO.getById(1);
        Registration registration = new Registration();
        registration.setCourse(savedCourse);
        registration.setStudent(savedStudent);

        savedStudent.getCoursesRegistered().add(registration);
        savedStudent = studentDAO.save(savedStudent);

        /**
         * Flush and clear to commit the previous transaction (insert on student and registration) before the next DB request
         */
        this.entityManager.flush();
        this.entityManager.clear();

        Student studentFromNewDBAccess = studentDAO.getById(savedStudent.getId());

        //THEN
        assertThat(studentFromNewDBAccess.getName()).isEqualTo("Héraclès");
        assertThat(studentFromNewDBAccess.getCoursesRegistered().get(0).getCourse().getName()).isEqualTo("Wood-Horse construction");
    }

    /**
     * Query detail (generated by Hibernate) :
     * SELECT student0_.id AS id1_2_, student0_.name AS name2_2_
     * FROM student student0_
     * LEFT OUTER JOIN registration coursesreg1_ ON student0_.id=coursesreg1_.student_id
     * LEFT OUTER JOIN course course2_ ON coursesreg1_.course_id=course2_.id
     * WHERE course2_.name=?
     * ORDER BY student0_.name ASC
     */
    @Test
    public void it_should_return_students_given_course_name_sorted_by_student_name(){
        //GIVEN, WHEN
        List<Student> navigationStudents = studentDAO.findAllByCoursesRegisteredCourseNameOrderByName("Aegean Sea navigation");
        List<Student> woodHorseStudents = studentDAO.findAllByCoursesRegisteredCourseNameOrderByName("Wood-Horse construction");

        //THEN
        assertThat(navigationStudents.size()).isEqualTo(2);
        assertThat(woodHorseStudents.size()).isEqualTo(1);
        assertThat(navigationStudents.stream().anyMatch(student -> student.getName().equals("Ajax"))).isTrue();
        assertThat(navigationStudents.stream().anyMatch(student -> student.getName().equals("Anticlée"))).isTrue();
        assertThat(woodHorseStudents.stream().anyMatch(student -> student.getName().equals("Achille"))).isTrue();
    }

    /**
     * Query detail (generated by Hibernate) :
     * SELECT student0_.id AS id1_2_, student0_.name AS name2_2_
     * FROM student student0_
     * LEFT OUTER JOIN registration coursesreg1_ ON student0_.id=coursesreg1_.student_id
     * LEFT OUTER JOIN course course2_ ON coursesreg1_.course_id=course2_.id
     * WHERE coursesreg1_.id IS NULL
     * OR course2_.name<>?
     */
    @Test
    public void it_should_return_students_not_registered_for_course(){
        //GIVEN, WHEN
        List<Student> navigationStudents = studentDAO.findAllByCoursesRegisteredIsNullOrCoursesRegisteredCourseNameIsNot("Aegean Sea navigation");

        //THEN
        assertThat(navigationStudents.size()).isEqualTo(3);
        assertThat(navigationStudents.stream().anyMatch(student -> student.getName().equals("Achille"))).isTrue();
        assertThat(navigationStudents.stream().anyMatch(student -> student.getName().equals("Agamemnon"))).isTrue();
        assertThat(navigationStudents.stream().anyMatch(student -> student.getName().equals("Patrocle"))).isTrue();
    }

}
