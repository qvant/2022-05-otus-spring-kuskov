package ru.otus.spring.orchestrator.core.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class DependenciesPageController {

    @GetMapping("/dependencies")
    public String listPage(Model model) {
        return "dependenciesList";
    }

    @GetMapping("/dependencyEdit")
    public String editPage() {
        return "dependencyEdit";
    }

    @PostMapping("/dependencyEdit")
    public String dependencyEdit() {
        return "redirect:/dependencies";
    }

    @GetMapping("/dependencyCreate")
    public String createPage(Model model) {
        return "dependencyCreate";
    }


    @GetMapping("/dependencyDelete")
    public String deletePage() {
        return "dependencyDelete";
    }

    @PostMapping("/dependencyDelete")
    public String dependencyDelete() {
        return "redirect:/dependencies";
    }
}
