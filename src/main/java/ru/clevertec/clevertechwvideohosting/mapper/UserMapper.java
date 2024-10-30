package ru.clevertec.clevertechwvideohosting.mapper;

import org.mapstruct.*;
import ru.clevertec.clevertechwvideohosting.dto.user.CreateUserDto;
import ru.clevertec.clevertechwvideohosting.dto.user.UserPartialUpdateDto;
import ru.clevertec.clevertechwvideohosting.dto.user.UserResponse;
import ru.clevertec.clevertechwvideohosting.model.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    User toUser(CreateUserDto createUserDto);

    UserResponse toUserResponse(User user);

    void updateUserFromDto(CreateUserDto channelUpdateDto, @MappingTarget User user);

    void partialUpdateUpdateFromDto(UserPartialUpdateDto dto, @MappingTarget User user);
}