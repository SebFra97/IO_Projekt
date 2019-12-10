package ioproj.example.spring.ioproj;


import io.vavr.collection.List;
import ioproj.example.spring.ioproj.db.ScoreRepository;
import ioproj.example.spring.ioproj.db.ScoreRow;
import ioproj.example.spring.ioproj.db.StudentRepository;
import ioproj.example.spring.ioproj.db.StudentRow;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class StudentService {

    //private List<Student> students = List.empty();
    private final StudentRepository studentRepository;
    private final ScoreRepository scoreRepository;

    public StudentService(StudentRepository studentRepository, ScoreRepository scoreRepository) {
        this.studentRepository = studentRepository;
        this.scoreRepository = scoreRepository;
    }

    public List<Student> getStudents() {
        return List.ofAll(this.studentRepository.findAll())
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
            return this.studentRepository.save(new StudentRow(
                    newStudent.name,
                    newStudent.number,
                    newStudent.group1)).toStudent();
        }

    @Transactional
    public Optional<Student> changeNumber(long studentId, String newNumber) {
        final Optional<StudentRow> student =
                this.studentRepository.findById(studentId);
        return student.map(c -> {
            c.setNumber(newNumber);
            studentRepository.save(c);
            return c.toStudent();
        });
    }

    @Transactional
    public Optional<Integer> addScore(final long studentId, final Score score) {
        final Optional<StudentRow> student =
                this.studentRepository.findById(studentId);
        return student.map(c->{
            int existingScore=List.ofAll(c.getScores())
                    .foldLeft(0,(p,s)->p+s.getScore());
            final ScoreRow newScore=new ScoreRow(score.score,score.comment,c);
            this.scoreRepository.save(newScore);
            return existingScore+score.score;});}
}

