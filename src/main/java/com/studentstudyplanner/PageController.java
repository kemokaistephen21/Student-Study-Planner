package com.studentstudyplanner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.studentstudyplanner.data.AssignmentService;
import com.studentstudyplanner.data.CoursesService;
import com.studentstudyplanner.data.NotesService;
import com.studentstudyplanner.data.TasksService;

import com.studentstudyplanner.data.entity.AssignmentEntity;
import com.studentstudyplanner.data.entity.CourseEntity;
import com.studentstudyplanner.data.entity.NoteEntity;
import com.studentstudyplanner.data.entity.TaskEntity;

import com.studentstudyplanner.model.AssignmentModel;
import com.studentstudyplanner.model.CourseModel;
import com.studentstudyplanner.model.NoteModel;
import com.studentstudyplanner.model.TaskModel;

@Controller
public class PageController {
	
	final CoursesService coursesService;
	final AssignmentService assignmentsService;
	final TasksService tasksService;
	final NotesService notesService;

    PageController(CoursesService coursesService, AssignmentService assignmentsService,
    		TasksService tasksService, NotesService notesService) {
        this.coursesService = coursesService;
		this.assignmentsService = assignmentsService;
		this.tasksService = tasksService;
		this.notesService = notesService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
    	List<CourseEntity> ces = coursesService.findAll();
    	List<CourseModel> cms = new ArrayList<>();
    	for (CourseEntity entity : ces) 
		{
            cms.add(coursesService.EntityToModel(entity));
        }
        model.addAttribute("courses", cms);
    	
    	
    	List<AssignmentEntity> aes = assignmentsService.findAll();
        List<AssignmentModel> ams = new ArrayList<>();

        for (AssignmentEntity entity : aes) 
        {
            ams.add(assignmentsService.EntityToModel(entity));
        }
        model.addAttribute("assignments", ams);

    	
    	List<TaskEntity> tes = tasksService.findAll();
        List<TaskModel> tms = new ArrayList<>();

        for (TaskEntity entity : tes) {
            tms.add(tasksService.EntityToModel(entity));
        }

        model.addAttribute("tasks", tms);

    	
    	List<NoteEntity> nes = notesService.findAll();
        List<NoteModel> nms = new ArrayList<>();

        for (NoteEntity entity : nes) 
        {
            nms.add(notesService.EntityToModel(entity));
        }

        model.addAttribute("notes", nms);
    	
        return "index";
    }

    @GetMapping("/courses")
    public String courses(Model model) 
    {
        List<CourseEntity> ces = coursesService.findAll();
        List<CourseModel> cms = new ArrayList<>();

        for (CourseEntity entity : ces) {
            cms.add(coursesService.EntityToModel(entity));
        }

        model.addAttribute("courses", cms);

        return "courses";
    }

    @PostMapping("/courses")
    public String addCourse
    (
        @org.springframework.web.bind.annotation.RequestParam("courseName") String name,
        @org.springframework.web.bind.annotation.RequestParam("courseCode") String code,
        @org.springframework.web.bind.annotation.RequestParam("instructor") String instructor
    )
    {
        CourseModel model = new CourseModel(null, name, code, instructor, 0L);
        CourseEntity course = coursesService.ModelToEntity(model);

        if (course != null) {
            coursesService.create(course);
        }

        return "redirect:/courses";
    }

    @PostMapping("/courses/delete")
    public String deleteCourse(
            @org.springframework.web.bind.annotation.RequestParam("id") Long id)
    {
        CourseEntity course = coursesService.findById(id);

        if (course != null) {
            coursesService.delete(course);
        }

        return "redirect:/courses";
    }

    @PostMapping("/courses/update")
    public String updateCourse
    (
        @org.springframework.web.bind.annotation.RequestParam("id") Long id,
        @org.springframework.web.bind.annotation.RequestParam("courseName") String name,
        @org.springframework.web.bind.annotation.RequestParam("courseCode") String code,
        @org.springframework.web.bind.annotation.RequestParam("instructor") String instructor
    )
    {
        CourseEntity course = coursesService.findById(id);

        if (course != null) {
            course.setName(name);
            course.setCode(code);
            course.setInstructor(instructor);

            coursesService.update(course);
        }

        return "redirect:/courses";
    }

    @GetMapping("/assignments")
    public String assignments(Model model) 
    {
        List<AssignmentEntity> aes = assignmentsService.findAll();
        List<AssignmentModel> ams = new ArrayList<>();

        for (AssignmentEntity entity : aes) {
            ams.add(assignmentsService.EntityToModel(entity));
        }

        model.addAttribute("assignments", ams);

        return "assignments";
    }

    @PostMapping("/assignments")
    public String addAssignment
    (
        @org.springframework.web.bind.annotation.RequestParam("assignmentName") String name,
        @org.springframework.web.bind.annotation.RequestParam("course") String courseName,
        @org.springframework.web.bind.annotation.RequestParam("dueDate") String dueDate,
        @org.springframework.web.bind.annotation.RequestParam("category") String category
    )
    {
        java.time.LocalDateTime dateTime =
                java.time.LocalDate.parse(dueDate).atStartOfDay();

        AssignmentModel model = new AssignmentModel(
                null,
                name,
                category,
                dateTime,
                false,
                courseName
        );

        AssignmentEntity assignment = assignmentsService.ModelToEntity(model);

        if (assignment != null) {
            assignmentsService.create(assignment);
        }

        return "redirect:/assignments";
    }

    @PostMapping("/assignments/delete")
    public String deleteAssignment(
            @org.springframework.web.bind.annotation.RequestParam("id") Long id)
    {
        AssignmentEntity assignment = assignmentsService.findById(id);

        if (assignment != null) {
            assignmentsService.delete(assignment);
        }

        return "redirect:/assignments";
    }

    @PostMapping("/assignments/update")
    public String updateAssignment
    (
        @org.springframework.web.bind.annotation.RequestParam("id") Long id,
        @org.springframework.web.bind.annotation.RequestParam("assignmentName") String name,
        @org.springframework.web.bind.annotation.RequestParam("course") String courseName,
        @org.springframework.web.bind.annotation.RequestParam("dueDate") String dueDate,
        @org.springframework.web.bind.annotation.RequestParam("category") String category
    )
    {
        AssignmentEntity assignment = assignmentsService.findById(id);

        if (assignment != null) {
            java.time.LocalDateTime dateTime =
                    java.time.LocalDate.parse(dueDate).atStartOfDay();

            assignment.setName(name);
            assignment.setDueDate(dateTime);
            assignment.setCategory(category);

            assignmentsService.update(assignment);
        }

        return "redirect:/assignments";
    }

    @PostMapping("/tasks")
    public String addTask
    (
        @org.springframework.web.bind.annotation.RequestParam("taskName") String name,
        @org.springframework.web.bind.annotation.RequestParam("course") String courseName,
        @org.springframework.web.bind.annotation.RequestParam("dueDate") String dueDate,
        @org.springframework.web.bind.annotation.RequestParam("category") String category,
        @org.springframework.web.bind.annotation.RequestParam("description") String description
    )
    {
        java.time.LocalDateTime dateTime =
                java.time.LocalDate.parse(dueDate).atStartOfDay();

        TaskModel model = new TaskModel(
                null,
                name,
                dateTime,
                category,
                description,
                courseName
        );

        TaskEntity task = tasksService.ModelToEntity(model);

        if (task != null) {
            tasksService.create(task);
        }

        return "redirect:/tasks";
    }

    @GetMapping("/tasks")
    public String tasks(Model model) 
    {
        List<TaskEntity> tes = tasksService.findAll();
        List<TaskModel> tms = new ArrayList<>();

        for (TaskEntity entity : tes) {
            tms.add(tasksService.EntityToModel(entity));
        }

        model.addAttribute("tasks", tms);

        return "tasks";
    }

    @PostMapping("/tasks/update")
    public String updateTask
    (
        @org.springframework.web.bind.annotation.RequestParam("id") Long id,
        @org.springframework.web.bind.annotation.RequestParam("taskName") String name,
        @org.springframework.web.bind.annotation.RequestParam("dueDate") String dueDate,
        @org.springframework.web.bind.annotation.RequestParam("category") String category,
        @org.springframework.web.bind.annotation.RequestParam("description") String description
    )
    {
        TaskEntity task = tasksService.findById(id);

        if (task != null) {
            java.time.LocalDateTime dateTime =
                    java.time.LocalDate.parse(dueDate).atStartOfDay();

            task.setName(name);
            task.setDueDate(dateTime);
            task.setCategory(category);
            task.setDescription(description);

            tasksService.update(task);
        }

        return "redirect:/tasks";
    }

    @PostMapping("/tasks/delete")
    public String deleteTask(
            @org.springframework.web.bind.annotation.RequestParam("id") Long id)
    {
        TaskEntity task = tasksService.findById(id);

        if (task != null) {
            tasksService.delete(task);
        }

        return "redirect:/tasks";
    }

    @GetMapping("/notes")
    public String notes(Model model) 
    {
        List<NoteEntity> nes = notesService.findAll();
        List<NoteModel> nms = new ArrayList<>();

        for (NoteEntity entity : nes) {
            nms.add(notesService.EntityToModel(entity));
        }

        model.addAttribute("notes", nms);

        return "notes";
    }
    
    @PostMapping("/notes")
    public String addNote
    (
        @org.springframework.web.bind.annotation.RequestParam("noteTitle") String title,
        @org.springframework.web.bind.annotation.RequestParam("course") String courseName,
        @org.springframework.web.bind.annotation.RequestParam("category") String category,
        @org.springframework.web.bind.annotation.RequestParam("noteContent") String content
    ) 
    {

        NoteModel model = new NoteModel(null, title, category, content, courseName);
        NoteEntity note = notesService.ModelToEntity(model);

        if (note != null) {
            notesService.create(note);
        }

        return "redirect:/notes";
    }

    @PostMapping("/notes/update")
    public String updateNote
    (
        @org.springframework.web.bind.annotation.RequestParam("id") Long id,
        @org.springframework.web.bind.annotation.RequestParam("title") String title,
        @org.springframework.web.bind.annotation.RequestParam("category") String category,
        @org.springframework.web.bind.annotation.RequestParam("content") String content
    )
    {
        NoteEntity note = notesService.findById(id);

        if (note != null) {
            note.setTitle(title);
            note.setCategory(category);
            note.setContent(content);

            notesService.update(note);
        }

        return "redirect:/notes";
    }

    @PostMapping("/notes/delete")
    public String deleteNote(
            @org.springframework.web.bind.annotation.RequestParam("id") Long id)
    {
        NoteEntity note = notesService.findById(id);

        if (note != null) {
            notesService.delete(note);
        }

        return "redirect:/notes";
    }
}