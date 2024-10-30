package ru.clevertec.clevertechwvideohosting.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.clevertechwvideohosting.dto.user.CreateUserDto;
import ru.clevertec.clevertechwvideohosting.dto.user.UserPartialUpdateDto;
import ru.clevertec.clevertechwvideohosting.dto.user.UserResponse;
import ru.clevertec.clevertechwvideohosting.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        return userService.createUser(createUserDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserInformation(@PathVariable Long id) {
        return userService.getUserInformation(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(
            @PathVariable Long id,
            @Valid @RequestBody CreateUserDto updateUser) {
        return userService.updateUser(id, updateUser);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse partialUpdateUser(
            @PathVariable Long id,
            @RequestBody UserPartialUpdateDto userPartialUpdateDto) {
        return userService.partialUpdateUser(id, userPartialUpdateDto);
    }
}
