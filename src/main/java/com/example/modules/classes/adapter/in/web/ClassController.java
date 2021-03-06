package com.example.modules.classes.adapter.in.web;

import com.example.modules.classes.domain.Class;
import com.example.modules.classes.dto.ClassDto;
import com.example.modules.classes.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ClassController {

    private final CreateClassService createClassService;
    private final GetAllClassService getAllClassService;
    private final DeleteClassService deleteClassService;
    private final AssignSubjectToClassService assignSubjectToClassService;
    private final GetClassWithSubjectsService getClassWithSubjectsService;

    private final AssignStudentToClassService assignStudentToClassService;
    private final GetClassWithStudentsService getClassWithStudentsService;

    private final GetSubjectsToAssignTeachersToClassService getSubjectsToAssignTeachersToClassService;

    private final AssignTeacherToClassService assignTeacherToClassService;

    private final ClassReportService classReportService;

    public ClassController(CreateClassService createClassService,
                           GetAllClassService getAllClassService,
                           DeleteClassService deleteClassService,
                           AssignSubjectToClassService assignSubjectToClassService,
                           GetClassWithSubjectsService getClassWithSubjectsService,
                           AssignStudentToClassService assignStudentToClassService,
                           GetClassWithStudentsService getClassWithStudentsService,
                           GetSubjectsToAssignTeachersToClassService getSubjectsToAssignTeachersToClassService,
                           AssignTeacherToClassService assignTeacherToClassService,
                           ClassReportService classReportService) {
        this.createClassService = createClassService;
        this.getAllClassService = getAllClassService;
        this.deleteClassService = deleteClassService;
        this.assignSubjectToClassService = assignSubjectToClassService;
        this.getClassWithSubjectsService = getClassWithSubjectsService;
        this.assignStudentToClassService = assignStudentToClassService;
        this.getClassWithStudentsService = getClassWithStudentsService;
        this.getSubjectsToAssignTeachersToClassService = getSubjectsToAssignTeachersToClassService;
        this.assignTeacherToClassService = assignTeacherToClassService;
        this.classReportService = classReportService;
    }

    @RequestMapping("registerClass")
    public String showRegisterClass(@ModelAttribute("classCommand") Class aClass, Model map) {

        return "registerClass";
    }

    @RequestMapping("classDashboard")
    public String get(Model model) {

        System.out.println("ClassController.get");
        List<Class> aClasses = getAllClassService.execute();
        System.out.println("ClassController.get.aClasses.size="+ aClasses.size());

        model.addAttribute("classes", aClasses);

        return "classDashboard";
    }


    @RequestMapping(value = "addClass", method = RequestMethod.POST)
    public String post(@ModelAttribute("class") Class aClass, Model map) {
        Class newClass = createClassService.execute(aClass);
        map.addAttribute("aClass", aClass);

        return "redirect:/classDashboard";

    }

    @RequestMapping("deleteClass")  // default GET
    public String delete(@RequestParam String id) {
        System.out.println("ClassController.delete");
        System.out.println("%%%% id=" + id);

        deleteClassService.delete(Integer.parseInt(id));

        return "redirect:/classDashboard";
    }


    @RequestMapping("showAssignSubjectToClass")
    public String showAssignSubjectToClass(Model map, @RequestParam String id) {
        System.out.println("ClassController.showAssignSubjectToClass.id=" + id);

        AssignSubjectToClassCommand assignSubjectToClassCommand;
        assignSubjectToClassCommand = getClassWithSubjectsService.execute(Integer.parseInt(id));

        map.addAttribute("assignSubjectToClassCommand", assignSubjectToClassCommand);

        return "showAssignSubjectToClass";
    }


    @RequestMapping("addAssignSubjectToClass")
    public String addAssignSubjectToClass(@ModelAttribute("assignSubjectToClassCommand") AssignSubjectToClassCommand assignSubjectToClassCommand, Model map){
        System.out.println(">>>>>>ClassController.addAssignSubjectToClass");
        System.out.println("ClassController.addAssignSubjectToClass="+ assignSubjectToClassCommand);

        Class aClass = assignSubjectToClassService.execute(assignSubjectToClassCommand.getIdClass(), assignSubjectToClassCommand.getSubjectListIds());

        map.addAttribute("aClass", aClass);

        return "redirect:/classDashboard";
    }

    @RequestMapping("showAssignStudentToClass")
    public String showAssignStudentToClass(Model map, @RequestParam String id) {
        System.out.println("ClassController.showAssignStudentToClass.id=" + id);

        AssignStudentToClassCommand assignStudentToClassCommand;
        assignStudentToClassCommand = getClassWithStudentsService.execute(Integer.parseInt(id));

        map.addAttribute("assignStudentToClassCommand", assignStudentToClassCommand);

        return "showAssignStudentToClass";
    }


    @RequestMapping("addAssignStudentToClass")
    public String addAssignStudentToClass(@ModelAttribute("assignStudentToClassCommand") AssignStudentToClassCommand assignStudentToClassCommand, Model map){
        System.out.println(">>>>>>ClassController.addAssignStudentToClass");
        System.out.println("ClassController.addAssignStudentToClass="+ assignStudentToClassCommand);

        Class aClass = assignStudentToClassService.execute(assignStudentToClassCommand.getIdClass(), assignStudentToClassCommand.getStudentListIds());

        map.addAttribute("aClass", aClass);

        return "redirect:/classDashboard";
    }


    @RequestMapping("showAssignTeacherToClass")
    public String showAssignTeacherToClass(Model map, @RequestParam String id) {
        System.out.println("ClassController.showAssignSubjectToClass.id=" + id);

        AssignTeacherToClassCommand assignTeacherToClassCommand;
        assignTeacherToClassCommand = getSubjectsToAssignTeachersToClassService.execute(Integer.parseInt(id));

        map.addAttribute("assignTeacherToClassCommand", assignTeacherToClassCommand);

        return "showAssignTeacherToClass";
    }


    @RequestMapping("addAssignTeacherToClass")
    public String addAssignTeacherToClass(@ModelAttribute("assignTeacherToClassCommand") AssignTeacherToClassCommand assignTeacherToClassCommand, Model map){
        System.out.println(">>>>>>ClassController.addAssignStudentToClass");
        System.out.println("ClassController.assignTeacherToClassCommand="+ assignTeacherToClassCommand);

        Class aClass = assignTeacherToClassService.execute(assignTeacherToClassCommand);

        map.addAttribute("aClass", aClass);

        return "redirect:/classDashboard";
    }

    @RequestMapping("classReport")
    public String classReport(Model model, @RequestParam String id) {
        System.out.println("classReport.id="+ id);

        ClassDto classDto = classReportService.execute(Integer.parseInt(id));
        model.addAttribute("aClass", classDto);

        return "classReport";
    }





}
