package ru.otus.spring.orchestrator.core.dto;

import ru.otus.spring.orchestrator.core.domain.DependencyType;

public class DependencyTypeDto {

    private Long id;

    private String name;

    public DependencyTypeDto(){};

    public DependencyTypeDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static DependencyTypeDto toDto(DependencyType dependencyType){
        return new DependencyTypeDto(dependencyType.getId(), dependencyType.getName());
    }

    public DependencyType toDomain(){
        return new DependencyType(this.id, this.name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
