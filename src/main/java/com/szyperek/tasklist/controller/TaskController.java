package com.szyperek.tasklist.controller;

import com.szyperek.tasklist.dto.request.TaskRequest;
import com.szyperek.tasklist.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public String getAllTasksOrderedByDueDateAsc(Model model) {
        model.addAttribute("tasks", taskService.getAllTasksOrderedByDueDateAsc());
        return "all_tasks";
    }

    @GetMapping("/new")
    public String addTaskForm(Model model) {
        model.addAttribute("task", new TaskRequest());
        return "add_task";
    }

    @PostMapping("/new")
    public RedirectView addTask(@ModelAttribute("task") @Valid TaskRequest taskRequest,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.error("Validation error: {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("validationError", true);
            return new RedirectView("/tasks/new");
        }

        taskService.addTask(taskRequest);
        return new RedirectView("/tasks");
    }

    @PostMapping("/{id}")
    public String deleteTaskById(@PathVariable Long id, HttpServletRequest request) {
        taskService.deleteTaskById(id);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @PostMapping("done/{id}")
    public String changeIsFinished(@PathVariable Long id, HttpServletRequest request) {
        taskService.changeIsFinished(id);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
