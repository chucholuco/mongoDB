package com.example.mongodb.service;

import com.example.mongodb.entity.Department;
import com.example.mongodb.entity.Student;
import com.example.mongodb.repository.DepartmentRepository;
import com.example.mongodb.repository.StudentRepository;
import com.example.mongodb.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final SubjectRepository subjectRepository;

    public Student createStudent(Student student) {
        if(student.getDepartment() != null) {
            departmentRepository.save(student.getDepartment());
        }
        if(student.getSubjects() != null && !student.getSubjects().isEmpty()) {
            subjectRepository.saveAll(student.getSubjects());
        }
        return studentRepository.save(student);
    }

    public Student getStudentById(String id) {
        return studentRepository.findById(id).get();
    }

    public List<Student> getAllStudents() {
        System.out.println("Inside the method getAllStudents");
        return studentRepository.findAll();
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public String deleteStudent(String id) {
        studentRepository.deleteById(id);
        return "Student with id: " + id + " has been deleted";
    }

    public List<Student> getStudentsByName(String name) {
        //return  studentRepository.findByName(name);
        return studentRepository.getByName(name);
    }

    public Student studentsByNameAndMail(String name, String mail) {
        return studentRepository.findByNameAndEmail(name, mail);
    }

    public List<Student> studentsByNameOrMail(String name, String mail) {
        return studentRepository.findByNameOrEmail(name, mail);
    }

    public List<Student> getAllWithPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return studentRepository.findAll(pageable).getContent();
    }

    public List<Student> allWithSorting() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name", "email");
        return studentRepository.findAll(sort);
    }

    public List<Student> byDepartmentName(String deptName) {
        return studentRepository.findByDepartmentDepartmentName(deptName);
    }

    public List<Student> bySubjectName(String subName) {
        return studentRepository.findBySubjectsSubjectName(subName);
    }

    public List<Student> emailLike(String email) {
        return studentRepository.findByEmailIsLike(email);
    }

    public List<Student> nameStartsWith(String name) {
        return studentRepository.findByNameStartsWith(name);
    }

    public List<Student> byDepartmentId(String deptId) {
        return studentRepository.findByDepartmentId(deptId);
    }
}
