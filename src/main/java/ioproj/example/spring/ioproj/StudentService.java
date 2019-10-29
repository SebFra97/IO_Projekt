package ioproj.example.spring.ioproj;


import io.vavr.collection.List;

public class StudentService {

    private List<Student> students = List.empty();

    List<Student> getStudents() {
        return this.students;
    }

    Student addStudent(NewStudent student){
        Student created = new Student((long) (students.size()  + 1), student.name, student.number, student.grupa);
        students=students.prepend(created);
        return created;
    }



}
