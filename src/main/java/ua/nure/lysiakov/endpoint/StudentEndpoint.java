package ua.nure.lysiakov.endpoint;

import java.util.List;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ua.nure.lysiakov.mapper.StudentMapper;
import ua.nure.lysiakov.model.Student;
import ua.nure.lysiakov.service.StudentService;
import ua.nure.lysiakov.web_api_generation.AddStudentRequest;
import ua.nure.lysiakov.web_api_generation.DeleteStudentRequest;
import ua.nure.lysiakov.web_api_generation.GetAllStudentsRequest;
import ua.nure.lysiakov.web_api_generation.GetAllStudentsResponse;
import ua.nure.lysiakov.web_api_generation.GetStudentByIdRequest;
import ua.nure.lysiakov.web_api_generation.ServiceStatus;
import ua.nure.lysiakov.web_api_generation.StudentResponse;
import ua.nure.lysiakov.web_api_generation.UpdateStudentRequest;

@Endpoint
public class StudentEndpoint {

  public static final String NAMESPACE_URI = "www.nure.ua/lysiakov/web-api-generation";

  private final StudentService studentService;
  private final StudentMapper studentMapper;

  public StudentEndpoint(StudentService studentService, StudentMapper studentMapper) {
    this.studentService = studentService;
    this.studentMapper = studentMapper;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllStudentsRequest")
  @ResponsePayload
  public GetAllStudentsResponse getAllStudents(@RequestPayload GetAllStudentsRequest request) {
    List<StudentResponse> studentResponses = studentService.findAllStudents().stream()
        .map(studentMapper::mapToResponse)
        .toList();
    return new GetAllStudentsResponse(studentResponses);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentByIdRequest")
  @ResponsePayload
  public StudentResponse getStudentById(@RequestPayload GetStudentByIdRequest request) {
    Student student = studentService.findStudentById(request.getStudentId());
    return studentMapper.mapToResponse(student);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addStudentRequest")
  @ResponsePayload
  public StudentResponse addStudent(@RequestPayload AddStudentRequest request) {
    Student student = studentMapper.mapToModel(request);
    Student newStudent = studentService.addStudent(student);
    return studentMapper.mapToResponse(newStudent);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateStudentRequest")
  @ResponsePayload
  public StudentResponse updateStudent(@RequestPayload UpdateStudentRequest request) {
    Student requestStudent = studentMapper.mapToModel(request);
    Student student = studentService.updateStudent(requestStudent);
    return studentMapper.mapToResponse(student);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteStudentRequest")
  @ResponsePayload
  public ServiceStatus deleteStudent(@RequestPayload DeleteStudentRequest request) {
    studentService.deleteStudent(request.getStudentId());
    return new ServiceStatus("200", "Student deleted");
  }

}
