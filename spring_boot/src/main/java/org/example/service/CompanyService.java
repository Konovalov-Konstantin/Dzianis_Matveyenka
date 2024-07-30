package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.CompanyReadDto;
import org.example.listener.entity.EntityEvent;
import org.example.repository.CompanyRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final UserService userService;
    private final CompanyRepository companyRepository;
    private final ApplicationEventPublisher eventPublisher;

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(1).map(entity -> {
            eventPublisher.publishEvent(new EntityEvent(entity, "READ"));
            return new CompanyReadDto(entity.getId());
        });
    }
}
