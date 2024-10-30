package ru.clevertec.clevertechwvideohosting.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.clevertechwvideohosting.dto.user.CreateUserDto;
import ru.clevertec.clevertechwvideohosting.dto.user.UserPartialUpdateDto;
import ru.clevertec.clevertechwvideohosting.dto.user.UserResponse;
import ru.clevertec.clevertechwvideohosting.mapper.UserMapper;
import ru.clevertec.clevertechwvideohosting.model.User;
import ru.clevertec.clevertechwvideohosting.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse createUser(CreateUserDto createUserDto) {

        User user = userMapper.toUser(createUserDto);

        if (userRepository.findByNickname(user.getNickname()).isPresent()) {
            throw new IllegalArgumentException("User with this nickname already exists");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    public UserResponse getUserInformation(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with this id not found"));
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(Long id, CreateUserDto updateUserDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with this id not found"));

        userMapper.updateUserFromDto(updateUserDto, user);
        User updatedUser = userRepository.save(user);
        return userMapper.toUserResponse(updatedUser);
    }

    public UserResponse partialUpdateUser(Long id, UserPartialUpdateDto userPartialUpdateDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with this id not found"));

        userMapper.partialUpdateUpdateFromDto(userPartialUpdateDto, user);

        User updatedUser = userRepository.save(user);
        return userMapper.toUserResponse(updatedUser);
    }
}
