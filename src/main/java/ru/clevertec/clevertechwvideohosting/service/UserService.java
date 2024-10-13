package ru.clevertec.clevertechwvideohosting.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.clevertechwvideohosting.dto.user.UserDto;
import ru.clevertec.clevertechwvideohosting.dto.user.UserResponse;
import ru.clevertec.clevertechwvideohosting.handler.user.UserUpdateHandler;
import ru.clevertec.clevertechwvideohosting.handler.user.impl.EmailUpdateHandler;
import ru.clevertec.clevertechwvideohosting.handler.user.impl.UserNameUpdateHandler;
import ru.clevertec.clevertechwvideohosting.handler.user.impl.NicknameUpdateHandler;
import ru.clevertec.clevertechwvideohosting.mapper.UserMapper;
import ru.clevertec.clevertechwvideohosting.model.User;
import ru.clevertec.clevertechwvideohosting.repository.UserRepository;
import ru.clevertec.clevertechwvideohosting.util.UserUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse createUser(UserDto userDto) {
        if (UserUtil.userDtoIsBlank(userDto)) {
            throw new IllegalArgumentException("User fields are blank");
        }

        User user = userMapper.toUser(userDto);

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

    public UserResponse updateUser(Long id, UserDto updateUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with this id not found"));

        if (UserUtil.userDtoIsBlank(updateUser)) {
            throw new IllegalArgumentException("User fields are blank");
        }
        user.setNickname(updateUser.getNickname());
        user.setEmail(updateUser.getEmail());
        user.setName(updateUser.getName());
        User updatedUser = userRepository.save(user);
        return userMapper.toUserResponse(updatedUser);
    }

    public UserResponse partialUpdateUser(Long id, UserDto updateUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with this id not found"));

        List<UserUpdateHandler> handlers = List.of(
                new UserNameUpdateHandler(),
                new NicknameUpdateHandler(userRepository),
                new EmailUpdateHandler(userRepository)
        );

        handlers.forEach(handler -> handler.handle(user, updateUser));

        User updatedUser = userRepository.save(user);
        return userMapper.toUserResponse(updatedUser);
    }
}
