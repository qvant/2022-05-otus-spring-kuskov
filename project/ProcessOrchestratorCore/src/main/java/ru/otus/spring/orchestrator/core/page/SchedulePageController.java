package ru.otus.spring.orchestrator.core.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SchedulePageController {
    @GetMapping("/schedules")
    public String listPage(Model model) {
        return "scheduleList";
    }

    @GetMapping("/scheduleEdit")
    public String editPage() {
        return "scheduleEdit";
    }

    @PostMapping("/scheduleEdit")
    public String scheduleEdit() {
        return "redirect:/schedules";
    }

    @GetMapping("/scheduleCreate")
    public String createPage(Model model) {
        return "scheduleCreate";
    }


    @GetMapping("/scheduleDelete")
    public String deletePage() {
        return "scheduleDelete";
    }

    @PostMapping("/scheduleDelete")
    public String scheduleDelete() {
        return "redirect:/schedules";
    }
}
