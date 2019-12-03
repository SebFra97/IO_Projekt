package ioproj.example.spring.ioproj;


import io.vavr.collection.List;
import ioproj.example.spring.ioproj.db.StudentRepository;
import ioproj.example.spring.ioproj.db.StudentRow;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.function.Function;

@Service
public class StudentService {

    //private List<Student> students = List.empty();
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getStudents() {
        return List.ofAll(this.repository.findAll())
                // .map(getStudentRowStudentFunction());
                .map(StudentRow::toStudent);

    }

   /* private Function<StudentRow, Student> getStudentRowStudentFunction() {
        return dbObj->
                new Student(
                        dbObj.getId(),
                        dbObj.getName(),
                        dbObj.getNumber(),
                        dbObj.getGroup1());
    }
    */

    /* public Student addStudent(final NewStudent newStudent) {

        StudentRow created = this.repository.save(new StudentRow(
                newStudent.name,
                newStudent.number,
                newStudent.group1));

        return getStudentRowStudentFunction().apply(created);
}
        */

        public Student addStudent(final NewStudent newStudent) {
            return this.repository.save(new StudentRow(
                    newStudent.name,
                    newStudent.number,
                    newStudent.group1)).toStudent();
        }

    @Transactional
    public Optional<Student> changeNumber(long studentId, String newNumber) {
        final Optional<StudentRow> student =
                this.repository.findById(studentId);
        return student.map(c -> {
            c.setNumber(newNumber);
            repository.save(c);
            return c.toStudent();
        });
    }
}
