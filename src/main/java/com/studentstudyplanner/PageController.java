package com.studentstudyplanner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    	if (!ces.isEmpty()) {
    		for (CourseEntity entity : ces) {
    			cms.add(coursesService.EntityToModel(entity));
    		}
    		model.addAttribute("courses", cms);
    	}
    	
    	List<AssignmentEntity> aes = assignmentsService.findAll();
    	List<AssignmentModel> ams = new ArrayList<>();
    	if (!aes.isEmpty()) {
    		for (AssignmentEntity entity : aes) {
    			ams.add(assignmentsService.EntityToModel(entity));
    		}
    		model.addAttribute("assignments", ams);
    	}

    	List<TaskEntity> tes = tasksService.findAll();
    	List<TaskModel> tms = new ArrayList<>();
    	if (!tes.isEmpty()) {
    		for (TaskEntity entity : tes) {
    			tms.add(tasksService.EntityToModel(entity));
    		}
    		model.addAttribute("tasks", tms);
    	}

    	List<NoteEntity> nes = notesService.findAll();
    	List<NoteModel> nms = new ArrayList<>();
    	if (!nes.isEmpty()) {
    		for (NoteEntity entity : nes) {
    			nms.add(notesService.EntityToModel(entity));
    		}
    		model.addAttribute("notes", nms);
    	}
    	
        return "index";
    }

    @GetMapping("/courses")
    public String courses(Model model) {
    	List<CourseEntity> ces = coursesService.findAll();
    	List<CourseModel> cms = new ArrayList<>();
    	if (!ces.isEmpty()) {
    		for (CourseEntity entity : ces) {
    			cms.add(coursesService.EntityToModel(entity));
    		}
    		model.addAttribute("courses", cms);
    	}
        return "courses";
    }

    @GetMapping("/assignments")
    public String assignments(Model model) {
    	List<AssignmentEntity> aes = assignmentsService.findAll();
    	List<AssignmentModel> ams = new ArrayList<>();
    	if (!aes.isEmpty()) {
    		for (AssignmentEntity entity : aes) {
    			ams.add(assignmentsService.EntityToModel(entity));
    		}
    		model.addAttribute("assignments", ams);
    	}
        return "assignments";
    }

    @GetMapping("/tasks")
    public String tasks(Model model) {
    	List<TaskEntity> tes = tasksService.findAll();
    	List<TaskModel> tms = new ArrayList<>();
    	if (!tes.isEmpty()) {
    		for (TaskEntity entity : tes) {
    			tms.add(tasksService.EntityToModel(entity));
    		}
    		model.addAttribute("tasks", tms);
    	}
        return "tasks";
    }

    @GetMapping("/notes")
    public String notes(Model model) {
    	List<NoteEntity> nes = notesService.findAll();
    	List<NoteModel> nms = new ArrayList<>();
    	if (!nes.isEmpty()) {
    		for (NoteEntity entity : nes) {
    			nms.add(notesService.EntityToModel(entity));
    		}
    		model.addAttribute("notes", nms);
    	}
        return "notes";
    }
}