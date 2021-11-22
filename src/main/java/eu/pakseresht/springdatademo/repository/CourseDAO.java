package eu.pakseresht.springdatademo.repository;

import eu.pakseresht.springdatademo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDAO extends JpaRepository<Course, Integer> {
}
