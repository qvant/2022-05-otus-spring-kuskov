package ru.otus.spring.orchestrator.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.orchestrator.core.domain.DependencyType;
import ru.otus.spring.orchestrator.core.dto.DependencyTypeDto;
import ru.otus.spring.orchestrator.core.repository.DependencyTypeRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DependencyTypeService {
    private final DependencyTypeRepository dependencyTypeRepository;
    public List<DependencyType> findAll(){
        return dependencyTypeRepository.findAll();
    }

    public DependencyType findById(Long id){
        return dependencyTypeRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
