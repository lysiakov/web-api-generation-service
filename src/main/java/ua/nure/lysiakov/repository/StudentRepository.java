package ua.nure.lysiakov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.lysiakov.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
