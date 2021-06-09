package com.example.modules.student.adapter.in.web;

import com.example.modules.student.domain.Student;
import com.example.modules.student.services.CreateStudentService;
import com.example.modules.student.services.DeleteStudentService;
import com.example.modules.student.services.GetAllStudentsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {

    private final CreateStudentService createStudentService;
    private final GetAllStudentsService getAllStudentsService;
    private final DeleteStudentService deleteStudentService;

    public StudentController(CreateStudentService createStudentService, GetAllStudentsService getAllStudentsService, DeleteStudentService deleteStudentService) {
        this.createStudentService = createStudentService;
        this.getAllStudentsService = getAllStudentsService;
        this.deleteStudentService = deleteStudentService;
    }


    @RequestMapping("registerStudent")
    public String showRegisterStudent(@ModelAttribute("studentCommand") Student student, Model map) {

        return "registerStudent";
    }

    @RequestMapping("studentDashboard")
    public String showStudentDashBoard(Model model) {

        System.out.println("StudentController.showStudentDashBoard");
        List<Student> students = getAllStudentsService.execute();
        System.out.println("StudentController.showStudentDashBoard.students.size="+ students.size());

        model.addAttribute("$students",students);

        return "studentDashboard";
    }


    @RequestMapping(value = "addStudent", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("student") Student student, Model map) {
        Student newStudent = createStudentService.execute(student);
        map.addAttribute("student", student);

        return "redirect:/studentDashboard";

    }

    @RequestMapping("deleteStudent")  // default GET
    public String deleteStudent(@RequestParam String id) {
        System.out.println("deleteStudent");
        System.out.println("%%%% id=" + id);

        deleteStudentService.delete(Integer.parseInt(id));

        return "redirect:/studentDashboard";
    }

}
