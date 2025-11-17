package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.CompanyReadDto;
import org.example.listener.entity.EntityEvent;
import org.example.database.repository.CompanyRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final UserService userService;
    private final CompanyRepository companyRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional  // параметр propagation определяет,что будет если внутри открытой транзакции вызывается метод, помеченный @Transactional (по дефолту propagation = required новая транзакция не открывается, а выполняется в родительской).
                    // параметр isolation определяет уровень изоляции транзакций (read_committed, repeatable_read и т.д. По дефолту у postgres - read_committed)
                    // параметр readonly используется для оптимизации (если данные только читаются из БД и никак не меняются)
                    // timeout - таймаут для транзакции
    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(1).map(entity -> {
            eventPublisher.publishEvent(new EntityEvent(entity, "READ"));
            return new CompanyReadDto(entity.getId());
        });
    }
}
