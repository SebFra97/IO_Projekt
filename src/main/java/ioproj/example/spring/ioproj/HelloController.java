package ioproj.example.spring.ioproj;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String returnHello()
    {
        return "Hello!" + LocalDateTime.now();
    }
}
