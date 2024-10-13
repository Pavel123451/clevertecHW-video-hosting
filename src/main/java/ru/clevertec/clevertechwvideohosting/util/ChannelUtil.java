package ru.clevertec.clevertechwvideohosting.util;

import ru.clevertec.clevertechwvideohosting.dto.channel.ChannelCreateDto;
import ru.clevertec.clevertechwvideohosting.dto.channel.ChannelUpdateDto;

public class ChannelUtil {
    public static boolean channelCreateDtoIsBlank(ChannelCreateDto channelCreateDto) {
        String name = channelCreateDto.getName();
        String description = channelCreateDto.getDescription();
        String language = channelCreateDto.getLanguage();
        String avatar = channelCreateDto.getAvatar();
        Long categoryId = channelCreateDto.getCategoryId();
        Long authorId = channelCreateDto.getAuthorId();

        return name == null || description == null || language == null || avatar == null ||
                name.isBlank() || description.isBlank() || language.isBlank()
                || avatar.isBlank() || categoryId == null || authorId == null;
    }

    public static boolean channelUpdateDtoIsBlank(ChannelUpdateDto channelUpdateDto) {
        String name = channelUpdateDto.getName();
        String description = channelUpdateDto.getDescription();
        String language = channelUpdateDto.getLanguage();
        String avatar = channelUpdateDto.getAvatar();
        Long categoryId = channelUpdateDto.getCategoryId();

        return name == null || description == null || language == null || avatar == null ||
                name.isBlank() || description.isBlank() || language.isBlank()
                || avatar.isBlank() || categoryId == null;
    }
}