package com.szyperek.tasklist.controller;

import com.szyperek.tasklist.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public String getAllTasksOrderedByDueDateAsc(Model model) {
        model.addAttribute("tasks", taskService.getAllTasksOrderedByDueDateAsc());
        return "allTasks";
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
