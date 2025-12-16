package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.database.entity.User;
import org.example.database.repository.UserRepository;
import org.example.dto.UserCreateEditDto;
import org.example.dto.UserReadDto;
import org.example.mapper.UserCreateEditMapper;
import org.example.mapper.UserReadMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
// для всех методов класса транзакции будут на чтение. Над методами, где изменяются данные в БД, нужно поставить readonly=false (по дефолту).
// readOnly = true позволяет оптимизировать запросы в БД.
public class UserService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;

    public List<UserReadDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userReadMapper::map)
                .toList();
    }

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    @Transactional(readOnly = false)
    // если над класом указали readOnly = true, то над методами с изменением данных в БД нужно поставить @Transactional. readOnly = false можно не указывать т.к. это по дефолту.
    public UserReadDto create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(userCreateEditMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto userCreateEditDto) {
        return userRepository.findById(id)
                .map(user -> userCreateEditMapper.map(userCreateEditDto, user))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                }).orElse(false);
    }
}
