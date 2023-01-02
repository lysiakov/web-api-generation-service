package ua.nure.lysiakov.mapper;

import org.springframework.stereotype.Service;
import ua.nure.lysiakov.model.Student;
import ua.nure.lysiakov.web_api_generation.AddStudentRequest;
import ua.nure.lysiakov.web_api_generation.StudentResponse;
import ua.nure.lysiakov.web_api_generation.UpdateStudentRequest;

@Service
public class StudentMapper {

  public StudentResponse mapToResponse(Student student) {
    StudentResponse response = new StudentResponse();
    response.setId(student.getId());
    response.setEmail(student.getEmail());
    response.setName(student.getName());
    response.setGroupId(student.getGroupId());
    return response;
  }

  public Student mapToModel(AddStudentRequest request) {
    Student student = new Student();
    student.setName(request.getName());
    student.setEmail(request.getEmail());
    student.setGroupId(request.getGroupId());
    return student;
  }

  public Student mapToModel(UpdateStudentRequest request) {
    Student student = new Student();
    student.setId(request.getId());
    student.setName(request.getName());
    student.setEmail(request.getEmail());
    return student;
  }

}
