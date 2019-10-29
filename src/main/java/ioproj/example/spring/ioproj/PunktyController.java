package ioproj.example.spring.ioproj;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/punkty")
public class PunktyController {

    //private CopyOnWriteArrayList<String> students = new CopyOnWriteArrayList<>(Arrays.asList("Student 1" , "Student 2", "Student 3"));
    private StudentService service=new StudentService();

//    @RequestMapping(value = "/students", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    List<Student> getUsers() {
//        return this.service.getStudents().asJava();
//    }
//
//    @RequestMapping(value = "/users", method = RequestMethod.POST)
//    int addStudent(@RequestBody String name) {
//        students.add(name);
//        return students.size();
//    }

}
