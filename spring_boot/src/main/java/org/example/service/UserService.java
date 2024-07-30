package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.repository.CompanyRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
}
