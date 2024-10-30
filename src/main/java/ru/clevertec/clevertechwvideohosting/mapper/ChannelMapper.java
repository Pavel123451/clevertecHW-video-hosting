package ru.clevertec.clevertechwvideohosting.mapper;

import org.mapstruct.*;
import org.springframework.data.domain.Page;
import ru.clevertec.clevertechwvideohosting.dto.channel.*;
import ru.clevertec.clevertechwvideohosting.model.Channel;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ChannelMapper {
    @Mapping(target = "author.id", source = "authorId")
    @Mapping(target = "category.id", source = "categoryId")
    Channel toChannel(ChannelCreateDto channelCreateDto);

    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "categoryId", source = "category.id")
    ChannelResponse toChannelResponse(Channel channel);

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "subscribersCount", expression = "java(getSubscriberCount(channel))")
    ChannelShortSummaryDto toChannelShortSummaryDto(Channel channel);

    @Mapping(target = "authorName", source = "author.name")
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "subscribersCount", expression = "java(getSubscriberCount(channel))")
    ChannelSummaryDto toChannelSummaryDto(Channel channel);

    @Mapping(target = "totalElements", source = "channelPage.totalElements")
    @Mapping(target = "totalPages", source = "channelPage.totalPages")
    ChannelsPaginatedDto toChannelsPaginatedDto(List<ChannelShortSummaryDto> channelShortSummaries,
                                                Page<Channel> channelPage);

    @Mapping(target = "category.id", source = "categoryId")
    void updateChannelFromDto(ChannelUpdateDto channelUpdateDto, @MappingTarget Channel channel);

    @Mapping(target = "category.id", source = "categoryId")
    void partialUpdateChannelFromDto(ChannelPartialUpdateDto dto, @MappingTarget Channel channel);

    default Long getSubscriberCount(Channel channel) {
        return channel.getSubscribers() != null ?
                (long) channel.getSubscribers().size() : 0;
    }
}
