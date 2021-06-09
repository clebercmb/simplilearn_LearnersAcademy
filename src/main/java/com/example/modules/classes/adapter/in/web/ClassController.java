package com.example.modules.classes.adapter.in.web;

import com.example.modules.classes.domain.Class;
import com.example.modules.classes.services.*;
import com.example.modules.subject.dto.SubjectSelectedViewDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClassController {

    private final CreateClassService createClassService;
    private final GetAllClassService getAllClassService;
    private final DeleteClassService deleteClassService;
    private final AssignSubjectToClassService assignSubjectToClassService;
    private final GetClassWithSubjectsService getClassWithSubjectsService;


    public ClassController(CreateClassService createClassService, GetAllClassService getAllClassService, DeleteClassService deleteClassService, AssignSubjectToClassService assignSubjectToClassService, GetClassWithSubjectsService getClassWithSubjectsService) {
        this.createClassService = createClassService;
        this.getAllClassService = getAllClassService;
        this.deleteClassService = deleteClassService;
        this.assignSubjectToClassService = assignSubjectToClassService;
        this.getClassWithSubjectsService = getClassWithSubjectsService;
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

        return "successClass";

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

        AssignSubjectToClassCommand assignSubjectToClassCommand = new AssignSubjectToClassCommand();

        Class aClass = new Class();
        aClass.setId(Integer.parseInt(id));
        aClass.setName("Class 1");

        List<SubjectSelectedViewDto> subjectList = new ArrayList<>();

        SubjectSelectedViewDto s1 = new SubjectSelectedViewDto(1, "Subject 1", "");
        SubjectSelectedViewDto s2 = new SubjectSelectedViewDto(1, "Subject 2", "checked");
        SubjectSelectedViewDto s3 = new SubjectSelectedViewDto(1, "Subject 3", "");
        SubjectSelectedViewDto s4 = new SubjectSelectedViewDto(1, "Subject 4", "checked");

        subjectList.add(s1);
        subjectList.add(s2);
        subjectList.add(s3);
        subjectList.add(s4);

        assignSubjectToClassCommand.setaClass(aClass);
        assignSubjectToClassCommand.setSubjectList(subjectList);


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

        return "successSubjectAssignToClass";
    }


    /**
     * This creates a new address object for the empty form and stuffs it into
     * the model
     */
    @ModelAttribute("assignSubjectToClassCommand")
    public AssignSubjectToClassCommand populateUser() {
        AssignSubjectToClassCommand assignSubjectToClassCommand = new AssignSubjectToClassCommand();

        return assignSubjectToClassCommand;
    }


}
