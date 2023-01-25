package ru.otus.spring.orchestrator.core.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.orchestrator.core.domain.DependencyType;
import ru.otus.spring.orchestrator.core.dto.DependencyTypeDto;
import ru.otus.spring.orchestrator.core.dto.TaskTypeDto;
import ru.otus.spring.orchestrator.core.service.DependencyTypeService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class DependencyTypeController {
    private final DependencyTypeService dependencyTypeService;
    @GetMapping("/api/dependencyTypes")
    public List<DependencyTypeDto> getTasks() {
        return dependencyTypeService.findAll().stream().map(DependencyTypeDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/api/dependencyTypes/{id}")
    public DependencyTypeDto getDependencyType(@PathVariable("id") Long id) {
        return DependencyTypeDto.toDto(dependencyTypeService.findById(id));
    }
}
