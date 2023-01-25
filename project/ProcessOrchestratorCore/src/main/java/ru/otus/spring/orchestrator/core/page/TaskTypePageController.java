package ru.otus.spring.orchestrator.core.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TaskTypePageController {

    @GetMapping("/taskTypes")
    public String listPage(Model model) {
        return "taskTypesList";
    }

}
