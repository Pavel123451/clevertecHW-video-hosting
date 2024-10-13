package ru.clevertec.clevertechwvideohosting.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.clevertec.clevertechwvideohosting.dto.channel.ChannelCreateDto;
import ru.clevertec.clevertechwvideohosting.dto.channel.ChannelResponse;
import ru.clevertec.clevertechwvideohosting.dto.channel.ChannelShortSummaryDto;
import ru.clevertec.clevertechwvideohosting.dto.channel.ChannelSummaryDto;
import ru.clevertec.clevertechwvideohosting.model.Category;
import ru.clevertec.clevertechwvideohosting.model.Channel;
import ru.clevertec.clevertechwvideohosting.model.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ChannelMapper {
    Channel toChannel(ChannelCreateDto channelCreateDto);

    ChannelResponse toChannelResponse(Channel channel);

    ChannelShortSummaryDto toChannelShortSummaryDto(Channel channel);

    @Mapping(target = "authorName", source = "authorId.name")
    @Mapping(target = "categoryId", source = "categoryId.id")
    ChannelSummaryDto toChannelSummaryDto(Channel channel);

    default Category mapIdToCategory(Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        Category category = new Category();
        category.setId(categoryId);
        return category;
    }

    default User mapIdToAuthor(Long authorId) {
        if (authorId == null) {
            return null;
        }
        User user = new User();
        user.setId(authorId);
        return user;
    }

    default Long mapCategoryToId(Category category) {
        return category == null ? null : category.getId();
    }

    default Long mapCategoryToId(User author) {
        return author == null ? null : author.getId();
    }
}
