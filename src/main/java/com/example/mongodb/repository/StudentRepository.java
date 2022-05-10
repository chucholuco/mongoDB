package com.example.mongodb.repository;

import com.example.mongodb.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    List<Student> findByName(String name);

    Student findByNameAndEmail(String name, String mail);

    List<Student> findByNameOrEmail(String name, String mail);

    List<Student> findByDepartmentDepartmentName(String deptName);

    List<Student> findBySubjectsSubjectName(String subName);

    List<Student> findByEmailIsLike(String email);

    List<Student> findByNameStartsWith (String name);

    List<Student> findByDepartmentId(String deptId);

    @Query(value = "{\"name\":\"?0\"}")
    List<Student> getByName(String name);

}
