package eu.pakseresht.springdatademo.repository;

import eu.pakseresht.springdatademo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllByCoursesRegisteredCourseNameOrderByName(String courseName);

    List<Student> findAllByCoursesRegisteredIsNullOrCoursesRegisteredCourseNameIsNot(String courseName);

}
