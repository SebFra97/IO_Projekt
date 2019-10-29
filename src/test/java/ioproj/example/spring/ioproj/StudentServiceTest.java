package ioproj.example.spring.ioproj;

import io.vavr.collection.List;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentServiceTest {

@Test
    public void getEmptyList() {

    final StudentService service = new StudentService();
    List<Student> students = service.getStudents();
    assertTrue(students.isEmpty());
    }

@Test
    public void addStudent() {
    final StudentService service = new StudentService();
    final Student created = service.addStudent(new NewStudent("Student1","1-2-3","IP"));
    assertNotNull(created);

}

@Test
    public void addStudentIsReturned() {

    final StudentService service = new StudentService();
    final Student created = service.addStudent(new NewStudent("StudentTestowy","1-2-3","IP31"));
    final List<Student> all = service.getStudents();

    assertEquals("StudentTestowy",all.get(0).name);
}

@Test
    public void addStudentHasNewId() {
    final StudentService service = new StudentService();
    final Student created = service.addStudent(new NewStudent("StudentTestowy","1-2-3","IP31"));
    final Student created2 = service.addStudent(new NewStudent("StudentTestowy2","3-5-8","IP32"));

    assertEquals(2,service.getStudents().size());
    assertNotEquals(created.id,created2.id);
}

}