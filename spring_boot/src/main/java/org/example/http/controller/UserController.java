package org.example.http.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserCreateEditDto;
import org.example.dto.UserReadDto;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserReadDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .map(user -> "user/user")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // возвращаем 201-й статус
    public String create(@RequestBody UserCreateEditDto userDto) {
        UserReadDto userReadDto = userService.create(userDto);
        return "redirect:/users/" + userReadDto.getId();
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody UserCreateEditDto userCreateEditDto) {
        return userService.update(id, userCreateEditDto)
                .map(user -> "redirect:/users/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        if(!userService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/users";
    }
}
