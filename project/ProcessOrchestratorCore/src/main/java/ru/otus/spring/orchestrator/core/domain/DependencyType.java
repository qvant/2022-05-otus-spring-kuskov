package ru.otus.spring.orchestrator.core.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dependency_types")
public class DependencyType {

    @Id
    private Long id;
    private String name;
    private final static  Long DEPENDENCY_SUCCESS = 1L;
    private final static  Long DEPENDENCY_FAILURE = 2L;
    private final static  Long DEPENDENCY_ALL_PARENTS_SUCCESS = 3L;

    public DependencyType() {};

    public DependencyType(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public static  String getNameById(long id){
        if (id == DEPENDENCY_SUCCESS) {
            return "At least one success";
        }
        if (id == DEPENDENCY_FAILURE) {
            return "At least one failure";
        }
        if (id == DEPENDENCY_ALL_PARENTS_SUCCESS) {
            return "All success";
        }
        return "";
    }
}
