package ua.nure.lysiakov.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ua.nure.lysiakov.exception.StudentNotFoundException;
import ua.nure.lysiakov.model.Student;
import ua.nure.lysiakov.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;

  public StudentServiceImpl(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public List<Student> findAllStudents() {
    return studentRepository.findAll();
  }

  @Override
  public Student findStudentById(Long id) {
    return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
  }

  @Override
  public Student addStudent(Student student) {
    return studentRepository.save(student);
  }

  @Override
  public Student updateStudent(Student student) {
    Student dbStudent = findStudentById(student.getId());
    dbStudent.setName(student.getName());
    dbStudent.setEmail(student.getEmail());
    return studentRepository.save(student);
  }

  @Override
  public boolean deleteStudent(Long id) {
    studentRepository.deleteById(id);
    return true;
  }
}
