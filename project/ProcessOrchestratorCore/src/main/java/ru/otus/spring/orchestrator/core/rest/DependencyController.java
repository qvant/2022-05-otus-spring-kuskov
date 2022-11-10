package ru.otus.spring.orchestrator.core.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.orchestrator.core.dto.DependencyDto;
import ru.otus.spring.orchestrator.core.service.DependencyService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class DependencyController {
    private final DependencyService dependencyService;
    @GetMapping("/api/dependencies")
    public List<DependencyDto> getDependencys(){
        return dependencyService.findAll().stream().map(DependencyDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/api/dependencies/{id}")
    public DependencyDto getDependency(@PathVariable("id") Long id){
        return DependencyDto.toDto(dependencyService.findById(id));
    }

    @PostMapping("/api/dependencies/")
    public void createDependency(@RequestBody DependencyDto dependencyDto){
        dependencyService.createDependency(dependencyDto.getTaskId(), dependencyDto.getTaskParentId(), dependencyDto.getType());
    }

    @PutMapping({"/api/dependencies/", "/api/dependencies/{id}"})
    public void editDependency(@RequestBody DependencyDto dependencyDto){
        dependencyService.editDependency(dependencyDto.getId(), dependencyDto.getTaskId(), dependencyDto.getTaskParentId(), dependencyDto.getType());
    }

    @DeleteMapping({"/api/dependencies/{id}"})
    public void editDependency(@PathVariable Long id){
        dependencyService.deleteDependency(id);
    }
}
