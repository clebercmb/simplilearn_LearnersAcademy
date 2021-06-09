package com.example.modules.subject.adapter.in.web;

import com.example.modules.classes.domain.Class;
import com.example.modules.subject.domain.Subject;
import com.example.modules.subject.services.CreateSubjectService;
import com.example.modules.subject.services.DeleteSubjectService;
import com.example.modules.subject.services.GetAllSubjectsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SubjectController {

    private final CreateSubjectService createSubjectService;
    private final GetAllSubjectsService getAllSubjectsService;
    private final DeleteSubjectService deleteSubjectService;

    public SubjectController(CreateSubjectService createSubjectService, GetAllSubjectsService getAllSubjectsService, DeleteSubjectService deleteSubjectService) {
        this.createSubjectService = createSubjectService;
        this.getAllSubjectsService = getAllSubjectsService;
        this.deleteSubjectService = deleteSubjectService;
    }


    @RequestMapping("registerSubject")
    public String showRegisterSubject(@ModelAttribute("subjectCommand")Subject subject, Model map) {

        return "registerSubject";
    }

    @RequestMapping("subjectDashboard")
    public String showSubjectDashBoard(Model model) {

        System.out.println("SubjectController.showSubjectDashBoard");
        List<Subject> subjects = getAllSubjectsService.execute();
        System.out.println("SubjectController.showSubjectDashBoard.Subjects.size="+ subjects.size());

        model.addAttribute("subjects",subjects);

        return "subjectDashboard";
    }


    @RequestMapping(value = "addSubject", method = RequestMethod.POST)
    public String addSubject(@ModelAttribute("subject") Subject subject, Model map) {
        Subject newSubject = createSubjectService.execute(subject);
        map.addAttribute("subject", subject);

        return "successSubject";

    }

    @RequestMapping("deleteSubject")  // default GET
    public String deleteSubject(@RequestParam String id) {
        System.out.println("deleteSubject");
        System.out.println("%%%% id=" + id);

        deleteSubjectService.delete(Integer.parseInt(id));

        return "redirect:/subjectDashboard";
    }


}
