package ioproj.example.spring.ioproj;


import io.vavr.collection.List;
import ioproj.example.spring.ioproj.db.StudentRepository;
import ioproj.example.spring.ioproj.db.StudentRow;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class StudentService {

   //private List<Student> students = List.empty();
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    List<Student> getStudents() {
        return List.ofAll(this.repository.findAll())
                .map(getStudentRowStudentFunction()
                );
    }

    private Function<StudentRow, Student> getStudentRowStudentFunction() {
        return dbObj->
                new Student(
                        dbObj.getId(),
                        dbObj.getName(),
                        dbObj.getNumber(),
                        dbObj.getGroup1());
    }


    Student addStudent(final NewStudent newStudent) {

        StudentRow created = this.repository.save(new StudentRow(
                newStudent.name,
                newStudent.number,
                newStudent.group1));

        return getStudentRowStudentFunction().apply(created);

    }



}
