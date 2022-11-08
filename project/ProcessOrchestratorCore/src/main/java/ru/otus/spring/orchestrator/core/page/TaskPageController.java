package ru.otus.spring.orchestrator.core.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TaskPageController {

    @GetMapping("/tasks")
    public String listPage(Model model) {
        return "taskList";
    }

    @GetMapping("/taskEdit")
    public String editPage() {
        return "taskEdit";
    }

    @PostMapping("/taskEdit")
    public String taskEdit() {
        return "redirect:/tasks";
    }

    @GetMapping("/taskCreate")
    public String createPage(Model model) {
        return "taskCreate";
    }


    @GetMapping("/taskDelete")
    public String deletePage() {
        return "taskDelete";
    }

    @PostMapping("/taskDelete")
    public String taskDelete() {
        return "redirect:/tasks";
    }
}
