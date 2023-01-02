package ua.nure.lysiakov.service;

import java.util.List;
import ua.nure.lysiakov.model.Student;

public interface StudentService {

  List<Student> findAllStudents();

  Student findStudentById(Long id);

  Student addStudent(Student student);

  Student updateStudent(Student student);

  boolean deleteStudent(Long id);

}
