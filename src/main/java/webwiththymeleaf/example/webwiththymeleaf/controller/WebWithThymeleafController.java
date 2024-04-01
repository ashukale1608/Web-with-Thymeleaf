package webwiththymeleaf.example.webwiththymeleaf.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import webwiththymeleaf.example.webwiththymeleaf.dov.StudentRepo;
import webwiththymeleaf.example.webwiththymeleaf.model.Student;

@Controller
public class WebWithThymeleafController {

    @Autowired
    StudentRepo studentRepo;

    @RequestMapping("/home")
    public String home(Model model, @Valid Student student, BindingResult result) {
        if (result.hasErrors()) {

            // System.out.println(result);
            return "hello";
        }
        System.out.println(student);
        studentRepo.save(student);
        model.addAttribute("name", student.getName());
        model.addAttribute("title", "home");
        return "home";
    }

    @RequestMapping("/getAbout")
    public String getBase() {
        return "about";
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("title", "Log In");
        model.addAttribute("student", new Student());
        // System.out.println("in form");
        return "hello";
    }

    @GetMapping("/printArr")
    public String printArr(Model model) {
        // String[] name={"ashu","swati"};
        // List<String> name = List.of("ashu","swti");
        // String name="qewd";
        List<Student> students = studentRepo.findAll();
        model.addAttribute("students", students);
        return "iterate";
    }

    @GetMapping("/conditional")
    public String useConditionalElvisOperator(Model model, @RequestParam("name") String name) {
        Optional<Student> student = studentRepo.findByName(name);
        boolean exist = false;
        if (student.isPresent()) {
            exist = true;
        }
        model.addAttribute("active", exist);
        model.addAttribute("gendar", "f");
        return "conditional";
    }

    @DeleteMapping("/deleteAllData")
    public ResponseEntity<Object> deleteAllData() {
        try {
            studentRepo.deleteAll();
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(" Failed ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
