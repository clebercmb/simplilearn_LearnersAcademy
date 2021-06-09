package com.example.modules.teacher.adapter.in.web;

import com.example.modules.teacher.domain.Teacher;
import com.example.modules.teacher.services.CreateTeacherService;
import com.example.modules.teacher.services.DeleteTeacherService;
import com.example.modules.teacher.services.GetAllTeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TeacherController {

    private final CreateTeacherService createTeacherService;
    private final GetAllTeacherService getAllTeacherService;
    private final DeleteTeacherService deleteTeacherService;

    public TeacherController(CreateTeacherService createTeacherService, GetAllTeacherService getAllTeacherService, DeleteTeacherService deleteTeacherService) {
        this.createTeacherService = createTeacherService;
        this.getAllTeacherService = getAllTeacherService;
        this.deleteTeacherService = deleteTeacherService;
    }


    @RequestMapping("registerTeacher")
    public String showRegisterTeacher(@ModelAttribute("teacherCommand") Teacher teacher, Model map) {

        return "registerTeacher";
    }

    @RequestMapping("teacherDashboard")
    public String get(Model model) {

        System.out.println("TeacherController.showTeacherDashBoard");
        List<Teacher> teachers = getAllTeacherService.execute();
        System.out.println("TeacherController.showTeacherDashBoard.Teacher.size="+ teachers.size());

        model.addAttribute("teachers", teachers);

        return "teacherDashboard";
    }


    @RequestMapping(value = "addTeacher", method = RequestMethod.POST)
    public String post(@ModelAttribute("teacher") Teacher teacher, Model map) {
        Teacher newTeacher = createTeacherService.execute(teacher);
        map.addAttribute("teacher", teacher);

        return "successTeacher";

    }

    @RequestMapping("deleteTeacher")  // default GET
    public String delete(@RequestParam String id) {
        System.out.println("TeacherController.delete");
        System.out.println("%%%% id=" + id);

        deleteTeacherService.delete(Integer.parseInt(id));

        return "redirect:/teacherDashboard";
    }



}
