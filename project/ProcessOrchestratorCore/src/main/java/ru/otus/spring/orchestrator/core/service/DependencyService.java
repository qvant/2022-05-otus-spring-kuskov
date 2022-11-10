package ru.otus.spring.orchestrator.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.orchestrator.core.domain.Dependency;
import ru.otus.spring.orchestrator.core.domain.Task;
import ru.otus.spring.orchestrator.core.domain.TaskInstance;
import ru.otus.spring.orchestrator.core.repository.DependencyRepository;
import ru.otus.spring.orchestrator.core.repository.TaskInstanceRepository;
import ru.otus.spring.orchestrator.core.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class DependencyService {
    private final DependencyRepository dependencyRepository;
    private final TaskInstanceRepository taskInstanceRepository;
    private final static  Long DEPENDENCY_SUCCESS = 1L;
    private final static  Long DEPENDENCY_FAILURE = 2L;
    private final static  Long DEPENDENCY_ALL_PARENTS_SUCCESS = 3L;
    private final static  Long TASK_STATUS_SUCCESS = 1L;
    private final static  Long TASK_STATUS_FAILURE = 2L;
    private final TaskRepository taskRepository;

    public List<Dependency> findAll(){
        return dependencyRepository.findAll();
    }
    public Dependency findById(Long id){
        return dependencyRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void createDependency(Long taskId, Long taskParentId, Long dependencyType){
        if (taskId.equals(taskParentId)){
            throw new RuntimeException("Task can't depend on themself");
        }
        Task task = taskRepository.findById(taskId).orElseThrow(RuntimeException::new);
        Task taskParent = taskRepository.findById(taskParentId).orElseThrow(RuntimeException::new);
        Dependency dependency = new Dependency(dependencyType, taskParent, task);
        dependencyRepository.save(dependency);
    }

    public void editDependency(Long id, Long taskId, Long taskParentId, Long dependencyType){
        if (taskId.equals(taskParentId)){
            throw new RuntimeException("Task can't depend on themself");
        }
        Task task = taskRepository.findById(taskId).orElseThrow(RuntimeException::new);
        Task taskParent = taskRepository.findById(taskParentId).orElseThrow(RuntimeException::new);
        Dependency dependency = dependencyRepository.findById(id).orElseThrow(RuntimeException::new);
        dependency.setTask(task);
        dependency.setTaskParent(taskParent);
        dependency.setType(dependencyType);
        dependencyRepository.save(dependency);
    }

    @Transactional
    public void deleteDependency(Long id){
        dependencyRepository.deleteById(id);
    }

    public List<Task> getReadyDependencies(Long taskId, Long taskStatus, Long rootTaskInstanceId)
    {
        Task initTask = taskRepository.getById(taskId);
        System.out.println("_______________________");
        System.out.println(initTask.getId());
        System.out.println("_______________________");
        Long id = initTask.getId();
        List<Dependency> dependencies = dependencyRepository.findByTaskParent(id);
        List <Task> tasksToRun = new ArrayList<>();
        // TODO: there would be very complex logic one day. Now just simple cases
        for (Dependency dependency: dependencies
             ) {
            // Just on parent's failure, other conditions doesn't matter
            if (dependency.getType().equals(DEPENDENCY_FAILURE)){
                if (Objects.equals(taskStatus, TASK_STATUS_FAILURE)){
                    tasksToRun.add(dependency.getTask());
                    continue;
                }
            }
            // Just on parent's success, other conditions doesn't matter
            if (dependency.getType().equals(DEPENDENCY_SUCCESS)){
                if (Objects.equals(taskStatus, TASK_STATUS_SUCCESS)){
                    tasksToRun.add(dependency.getTask());
                    continue;
                }
            }
            // All parents with such dependency in the DAG should succeed
            if (dependency.getType().equals(DEPENDENCY_ALL_PARENTS_SUCCESS)){
                if (Objects.equals(taskStatus, TASK_STATUS_SUCCESS)){
                    List<Dependency> allParentTasks = dependencyRepository.findByTaskAndType(dependency.getTask().getId(), DEPENDENCY_ALL_PARENTS_SUCCESS);
                    List<TaskInstance> tasksInRun = taskInstanceRepository.findByRootTaskInstanceId(rootTaskInstanceId);
                    boolean allSuccess = true;
                    for (Dependency parentTask: allParentTasks
                         ) {
                        boolean found = false;
                        for (TaskInstance task: tasksInRun
                             ) {
                            if (task.getStatus().equals(TASK_STATUS_SUCCESS) && task.getTaskTypeId().equals(parentTask.getType())){
                                found = true;
                                break;
                            }
                        }
                        if (!found){
                            allSuccess = false;
                            break;
                        }
                    }
                    if (allSuccess){
                        tasksToRun.add(dependency.getTask());
                        continue;
                    }
                }
            }
        }
        return tasksToRun;
    }
}
