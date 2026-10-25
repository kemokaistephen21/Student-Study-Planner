
package com.studentstudyplanner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.studentstudyplanner.data.AssignmentService;
import com.studentstudyplanner.data.CoursesService;
import com.studentstudyplanner.data.NotesService;
import com.studentstudyplanner.data.TasksService;
import com.studentstudyplanner.data.UserService;
import com.studentstudyplanner.data.entity.AssignmentEntity;
import com.studentstudyplanner.data.entity.CourseEntity;
import com.studentstudyplanner.data.entity.NoteEntity;
import com.studentstudyplanner.data.entity.TaskEntity;

import com.studentstudyplanner.model.AssignmentModel;
import com.studentstudyplanner.model.CourseModel;
import com.studentstudyplanner.model.CustomUserDetails;
import com.studentstudyplanner.model.NoteModel;
import com.studentstudyplanner.model.TaskModel;
import com.studentstudyplanner.model.UserModel;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

/**
 * Handles web requests for the Student Study Planner application.
 * Displays the main pages and processes requests to create, update,
 * and delete courses, assignments, study tasks, and notes.
 * Also handles login, logout, and user registration pages.
 */
@Controller
public class PageController {

    final CoursesService coursesService;
    final AssignmentService assignmentsService;
    final TasksService tasksService;
    final NotesService notesService;
    final UserService userService;

    /**
     * Creates the controller with the services needed to manage
     * application data and user accounts.
     *
     * @param coursesService service for managing courses
     * @param assignmentsService service for managing assignments
     * @param tasksService service for managing study tasks
     * @param notesService service for managing notes
     * @param userService service for managing user accounts
     */
    PageController(CoursesService coursesService,
                   AssignmentService assignmentsService,
                   TasksService tasksService,
                   NotesService notesService,
                   UserService userService) {
        this.coursesService = coursesService;
        this.assignmentsService = assignmentsService;
        this.tasksService = tasksService;
        this.notesService = notesService;
        this.userService = userService;
    }

    /**
     * Displays the dashboard with the current user's courses,
     * assignments, study tasks, and notes.
     *
     * @param model stores information displayed on the dashboard
     * @return the dashboard template named index
     */
    @GetMapping("/")
    public String dashboard(Model model) {
        List<CourseEntity> ces = getAllCoursesByCurrentUser();
        List<CourseModel> cms = new ArrayList<>();

        List<AssignmentEntity> aes = new ArrayList<>();
        List<AssignmentModel> ams = new ArrayList<>();

        List<TaskEntity> tes = new ArrayList<>();
        List<TaskModel> tms = new ArrayList<>();

        List<NoteEntity> nes = new ArrayList<>();
        List<NoteModel> nms = new ArrayList<>();

        for (CourseEntity entity : ces) {
            cms.add(coursesService.EntityToModel(entity));

            List<AssignmentEntity> assignmentResults =
                assignmentsService.findAllByCourseId(entity.getId());
            aes.addAll(assignmentResults);

            List<TaskEntity> taskResults =
                tasksService.findAllByCourseId(entity.getId());
            tes.addAll(taskResults);

            List<NoteEntity> noteResults =
                notesService.findAllByCourseId(entity.getId());
            nes.addAll(noteResults);
        }

        model.addAttribute("courses", cms);

        for (AssignmentEntity entity : aes) {
            ams.add(assignmentsService.EntityToModel(entity));
        }
        model.addAttribute("assignments", ams);

        for (TaskEntity entity : tes) {
            tms.add(tasksService.EntityToModel(entity));
        }
        model.addAttribute("tasks", tms);

        for (NoteEntity entity : nes) {
            nms.add(notesService.EntityToModel(entity));
        }
        model.addAttribute("notes", nms);

        return "index";
    }

    /**
     * Displays all courses belonging to the logged-in user
     * and prepares a form for adding a new course.
     *
     * @param model stores course information for the page
     * @return the courses template
     */
    @GetMapping("/courses")
    public String courses(Model model) {
        List<CourseEntity> ces = getAllCoursesByCurrentUser();
        List<CourseModel> cms = new ArrayList<>();

        for (CourseEntity entity : ces) {
            cms.add(coursesService.EntityToModel(entity));
        }

        model.addAttribute("courses", cms);

        CourseModel newCourse = new CourseModel();
        newCourse.setUserId(
            userService.getCurrentUserDetails().getId()
        );
        model.addAttribute("newCourse", newCourse);

        return "courses";
    }

    /**
     * Processes the form used to add a new course.
     * Converts the course model into an entity and saves it.
     *
     * @param newCourse course information submitted by the user
     * @param result contains validation results
     * @return redirects the user to the courses page
     */
    @PostMapping("/courses")
    public String addCourse(
            @Valid @ModelAttribute CourseModel newCourse,
            BindingResult result) {

        CourseEntity course =
            coursesService.ModelToEntity(newCourse);

        if (course != null) {
            coursesService.create(course);
        }

        return "redirect:/courses";
    }

    /**
     * Deletes a course using its database ID.
     *
     * @param id ID of the course to delete
     * @return redirects the user to the courses page
     */
    @PostMapping("/courses/delete")
    public String deleteCourse(@RequestParam("id") Long id) {
        CourseEntity course = coursesService.findById(id);

        if (course != null) {
            coursesService.delete(course);
        }

        return "redirect:/courses";
    }

    /**
     * Updates an existing course using information
     * submitted through the edit form.
     *
     * @param course updated course information
     * @param result contains validation results
     * @return redirects the user to the courses page
     */
    @PostMapping("/courses/update")
    public String updateCourse(
            @Valid @ModelAttribute CourseModel course,
            BindingResult result) {

        CourseEntity courseEntity =
            coursesService.ModelToEntity(course);

        if (course != null) {
            coursesService.update(courseEntity);
        }

        return "redirect:/courses";
    }

    /**
     * Displays assignments associated with the current
     * user's courses and prepares the assignment form.
     *
     * @param model stores assignments and course information
     * @return the assignments template
     */
    @GetMapping("/assignments")
    public String assignments(Model model) {
        List<CourseEntity> ces = getAllCoursesByCurrentUser();
        List<AssignmentEntity> aes = new ArrayList<>();
        List<AssignmentModel> ams = new ArrayList<>();

        for (CourseEntity entity : ces) {
            List<AssignmentEntity> assignmentResults =
                assignmentsService.findAllByCourseId(entity.getId());
            aes.addAll(assignmentResults);
        }

        for (AssignmentEntity entity : aes) {
            ams.add(assignmentsService.EntityToModel(entity));
        }

        model.addAttribute("assignments", ams);

        List<String> courseList = ces.stream()
            .map(CourseEntity::getName)
            .collect(Collectors.toList());

        model.addAttribute("courseList", courseList);
        model.addAttribute("newAssignment", new AssignmentModel());

        return "assignments";
    }

    /**
     * Creates a new assignment for the current user.
     *
     * @param newAssignment assignment information from the form
     * @param result contains validation results
     * @return redirects the user to the assignments page
     */
    @PostMapping("/assignments")
    public String addAssignment(
            @Valid @ModelAttribute AssignmentModel newAssignment,
            BindingResult result) {

        AssignmentEntity assignmentEntity =
            assignmentsService.ModelToEntity(
                newAssignment,
                userService.getCurrentUserDetails().getId()
            );

        if (assignmentEntity != null) {
            assignmentsService.create(assignmentEntity);
        }

        return "redirect:/assignments";
    }

    /**
     * Deletes an assignment using its database ID.
     *
     * @param id ID of the assignment to delete
     * @return redirects the user to the assignments page
     */
    @PostMapping("/assignments/delete")
    public String deleteAssignment(@RequestParam("id") Long id) {
        AssignmentEntity assignment =
            assignmentsService.findById(id);

        if (assignment != null) {
            assignmentsService.delete(assignment);
        }

        return "redirect:/assignments";
    }

    /**
     * Updates an existing assignment with the
     * information submitted through the edit form.
     *
     * @param assignment updated assignment information
     * @param result contains validation results
     * @return redirects the user to the assignments page
     */
    @PostMapping("/assignments/update")
    public String updateAssignment(
            @Valid @ModelAttribute AssignmentModel assignment,
            BindingResult result) {

        AssignmentEntity assignmentEntity =
            assignmentsService.ModelToEntity(
                assignment,
                userService.getCurrentUserDetails().getId()
            );

        if (assignmentEntity != null) {
            assignmentsService.update(assignmentEntity);
        }

        return "redirect:/assignments";
    }


    
    /**
     * Creates a new study task for the current user.
     *
     * @param newTask task information submitted by the user
     * @param result contains validation results
     * @return redirects the user to the study tasks page
     */
    @PostMapping("/tasks")
    public String addTask(
            @Valid @ModelAttribute TaskModel newTask,
            BindingResult result) {

        TaskEntity taskEntity =
            tasksService.ModelToEntity(
                newTask,
                userService.getCurrentUserDetails().getId()
            );

        if (taskEntity != null) {
            tasksService.create(taskEntity);
        }

        return "redirect:/tasks";
    }

    /**
     * Displays study tasks associated with the current
     * user's courses and prepares the task form.
     *
     * @param model stores task and course information
     * @return the tasks template
     */
    @GetMapping("/tasks")
    public String tasks(Model model) {
        List<CourseEntity> ces = getAllCoursesByCurrentUser();
        List<TaskEntity> tes = new ArrayList<>();
        List<TaskModel> tms = new ArrayList<>();

        for (CourseEntity entity : ces) {
            List<TaskEntity> assignmentResults =
                tasksService.findAllByCourseId(entity.getId());
            tes.addAll(assignmentResults);
        }

        for (TaskEntity entity : tes) {
            tms.add(tasksService.EntityToModel(entity));
        }

        model.addAttribute("tasks", tms);

        List<String> courseList = ces.stream()
            .map(CourseEntity::getName)
            .collect(Collectors.toList());

        model.addAttribute("courseList", courseList);
        model.addAttribute("newTask", new TaskModel());

        return "tasks";
    }

    /**
     * Updates an existing study task.
     *
     * @param task updated task information from the form
     * @param result contains validation results
     * @return redirects the user to the tasks page
     */
    @PostMapping("/tasks/update")
    public String updateTask(
            @Valid @ModelAttribute TaskModel task,
            BindingResult result) {

        TaskEntity taskEntity =
            tasksService.ModelToEntity(
                task,
                userService.getCurrentUserDetails().getId()
            );

        if (taskEntity != null) {
            tasksService.update(taskEntity);
        }

        return "redirect:/tasks";
    }

    /**
     * Deletes a study task using its database ID.
     *
     * @param id ID of the task to delete
     * @return redirects the user to the tasks page
     */
    @PostMapping("/tasks/delete")
    public String deleteTask(@RequestParam("id") Long id) {
        TaskEntity task = tasksService.findById(id);

        if (task != null) {
            tasksService.delete(task);
        }

        return "redirect:/tasks";
    }

    /**
     * Displays notes associated with the current user's
     * courses and prepares the form for adding notes.
     *
     * @param model stores notes and course information
     * @return the notes template
     */
    @GetMapping("/notes")
    public String notes(Model model) {
        List<CourseEntity> ces = getAllCoursesByCurrentUser();
        List<NoteEntity> nes = new ArrayList<>();
        List<NoteModel> nms = new ArrayList<>();

        for (CourseEntity entity : ces) {
            List<NoteEntity> assignmentResults =
                notesService.findAllByCourseId(entity.getId());
            nes.addAll(assignmentResults);
        }

        for (NoteEntity entity : nes) {
            nms.add(notesService.EntityToModel(entity));
        }

        model.addAttribute("notes", nms);

        List<String> courseList = ces.stream()
            .map(CourseEntity::getName)
            .collect(Collectors.toList());

        model.addAttribute("courseList", courseList);
        model.addAttribute("newNote", new NoteModel());

        return "notes";
    }

    /**
     * Creates a new note for the current user.
     *
     * @param newNote note information submitted by the user
     * @param result contains validation results
     * @return redirects the user to the notes page
     */
    @PostMapping("/notes")
    public String addNote(
            @Valid @ModelAttribute NoteModel newNote,
            BindingResult result) {

        NoteEntity noteEntity =
            notesService.ModelToEntity(
                newNote,
                userService.getCurrentUserDetails().getId()
            );

        if (noteEntity != null) {
            notesService.create(noteEntity);
        }

        return "redirect:/notes";
    }

    /**
     * Updates an existing note with new information.
     *
     * @param newNote updated note information
     * @param result contains validation results
     * @return redirects the user to the notes page
     */
    @PostMapping("/notes/update")
    public String updateNote(
            @Valid @ModelAttribute NoteModel newNote,
            BindingResult result) {

        NoteEntity noteEntity =
            notesService.ModelToEntity(
                newNote,
                userService.getCurrentUserDetails().getId()
            );

        if (noteEntity != null) {
            notesService.update(noteEntity);
        }

        return "redirect:/notes";
    }

    /**
     * Deletes a note using its database ID.
     *
     * @param id ID of the note to delete
     * @return redirects the user to the notes page
     */
    @PostMapping("/notes/delete")
    public String deleteNote(@RequestParam("id") Long id) {
        NoteEntity note = notesService.findById(id);

        if (note != null) {
            notesService.delete(note);
        }

        return "redirect:/notes";
    }

    /**
     * Displays the login form for existing users.
     *
     * @param model stores information for the login form
     * @return the login template
     */
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new UserModel());
        model.addAttribute("title", "Login");
        return "login";
    }

    /**
     * Invalidates the current HTTP session and
     * redirects the user to the login page.
     *
     * @param session the current HTTP session
     * @return redirects the user to the login page
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    /**
     * Displays the registration form for new users.
     *
     * @param model stores information for the registration form
     * @return the registration template
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("title", "Register New Account");
        model.addAttribute("user", new UserModel());
        return "register";
    }

    /**
     * Registers a new user after checking whether
     * the requested username already exists.
     *
     * @param user account information submitted by the user
     * @param model stores error messages if registration fails
     * @return the registration page if the username exists,
     *         or redirects to login after successful registration
     */
    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute UserModel user,
            Model model) {

        if (userService.usernameExists(user.getUsername())) {
            model.addAttribute("error", "User already exists!");
            model.addAttribute("user", user);
            return "register";
        }

        userService.save(user);
        return "redirect:/login";
    }

    /**
     * Retrieves all courses belonging to the
     * currently authenticated user.
     *
     * @return a list of course entities for the current user
     */
    private List<CourseEntity> getAllCoursesByCurrentUser() {
        CustomUserDetails ud =
            userService.getCurrentUserDetails();

        return coursesService.findAllByUserId(ud.getId());
    }
}
