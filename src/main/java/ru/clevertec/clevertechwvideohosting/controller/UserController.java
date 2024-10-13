package ru.clevertec.clevertechwvideohosting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.clevertechwvideohosting.dto.user.UserDto;
import ru.clevertec.clevertechwvideohosting.dto.user.UserResponse;
import ru.clevertec.clevertechwvideohosting.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserDto userDto) {
        UserResponse userResponse = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<UserResponse> userResponses = userService.getAllUsers();
        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserInformation(@PathVariable Long id) {
        UserResponse userResponse = userService.getUserInformation(id);
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UserDto updateUser) {
        UserResponse updatedUserResponse = userService.updateUser(id, updateUser);
        return ResponseEntity.ok(updatedUserResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> partialUpdateUser(
            @PathVariable Long id,
            @RequestBody UserDto updateUser) {
        UserResponse updatedUserResponse = userService.partialUpdateUser(id, updateUser);
        return ResponseEntity.ok(updatedUserResponse);
    }
}
