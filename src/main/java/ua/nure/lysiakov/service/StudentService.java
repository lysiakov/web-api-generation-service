package ua.nure.lysiakov.service;

import org.springframework.stereotype.Service;
import ua.nure.lysiakov.repository.StudentRepository;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

}
