package com.example.modules.teacher.adapter.in.web;

import com.example.modules.classes.adapter.in.web.AssignSubjectToClassCommand;
import com.example.modules.classes.domain.Class;
import com.example.modules.teacher.domain.Teacher;
import com.example.modules.teacher.services.*;
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
    private final GetTeacherWithSubjectsService getTeacherWithSubjectsService;
    private final AssignSubjectToTeacherService assignSubjectToTeacherService;


    public TeacherController(CreateTeacherService createTeacherService,
                             GetAllTeacherService getAllTeacherService,
                             DeleteTeacherService deleteTeacherService,
                             GetTeacherWithSubjectsService getTeacherWithSubjectsService,
                             AssignSubjectToTeacherService assignSubjectToTeacherService) {
        this.createTeacherService = createTeacherService;
        this.getAllTeacherService = getAllTeacherService;
        this.deleteTeacherService = deleteTeacherService;
        this.getTeacherWithSubjectsService = getTeacherWithSubjectsService;
        this.assignSubjectToTeacherService = assignSubjectToTeacherService;
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

        return "redirect:/teacherDashboard";

    }

    @RequestMapping("deleteTeacher")  // default GET
    public String delete(@RequestParam String id) {
        System.out.println("TeacherController.delete");
        System.out.println("%%%% id=" + id);

        deleteTeacherService.delete(Integer.parseInt(id));

        return "redirect:/teacherDashboard";
    }


    @RequestMapping("showAssignTeacherToSubject")
    public String showAssignSubjectToClass(Model map, @RequestParam String id) {
        System.out.println("ClassController.showAssignSubjectToClass.id=" + id);

        AssignTeacherToSubjectCommand assignTeacherToSubjectCommand;
        assignTeacherToSubjectCommand = getTeacherWithSubjectsService.execute(Integer.parseInt(id));

        map.addAttribute("assignTeacherToSubjectCommand", assignTeacherToSubjectCommand);

        return "showAssignTeacherToSubject";
    }


    @RequestMapping("assignTeacherToSubject")
    public String addAssignSubjectToClass(@ModelAttribute("assignTeacherToSubjectCommand") AssignTeacherToSubjectCommand assignTeacherToSubjectCommand, Model map){
        System.out.println(">>>>>>ClassController.addAssignSubjectToClass");
        System.out.println("ClassController.addAssignSubjectToClass="+ assignTeacherToSubjectCommand);

        Teacher teacher = assignSubjectToTeacherService.execute(assignTeacherToSubjectCommand.getIdTeacher(), assignTeacherToSubjectCommand.getSubjectListIds());

        map.addAttribute("teacher", teacher);

        return "redirect:/teacherDashboard";
    }

}
